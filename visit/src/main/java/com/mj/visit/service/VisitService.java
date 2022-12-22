package com.mj.visit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mj.exception.business.EntityNotFoundException;
import com.mj.exception.business.RoomUnavailableException;
import com.mj.exception.business.VisitingIntervalException;
import com.mj.visit.dto.*;
import com.mj.visit.model.Visit;
import com.mj.visit.producer.KafkaProducer;
import com.mj.visit.repository.VisitRepository;
import com.mj.visit.util.VisitUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static com.mj.exception.handler.GlobalErrorCode.*;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final KafkaProducer kafkaProducer;
    private final RoomService roomService;
    private final GuestService guestService;
    private final ModelMapper modelMapper;

    @Transactional
    public VisitDto checkIn(CheckInDto checkInDto) throws JsonProcessingException {
        RoomDto roomDto = roomService.getRoom(checkInDto.getRoomNumber());
        if (roomDto.isOccupied()) {
            throw new RoomUnavailableException(String.format("Room number %d is already occupied", roomDto.getNumber()), VISIT_ROOM_OCCUPIED);
        }

        LocalDateTime arrival = LocalDateTime.now();
        LocalDateTime departure = checkInDto.getDeparture();
        if (ChronoUnit.MINUTES.between(arrival, departure) < 1) {
            throw new VisitingIntervalException("Departure cannot be earlier than arrival", VISIT_INTERVAL);
        }

        GuestDto guestDto = guestService.findById(checkInDto.getGuestId());

        Visit visit = visitRepository.save(Visit.builder()
                .guestId(guestDto.getId())
                .roomNumber(roomDto.getNumber())
                .arrival(arrival)
                .departure(departure)
                .cost(VisitUtils.calculateCost(arrival, departure, roomDto.getPricePerHour(), guestDto.getDiscountPercent()))
                .active(true)
                .build());

        roomService.setRoomOccupation(checkInDto.getRoomNumber(), true);

        kafkaProducer.sendMessage(EmailDto.builder()
                .address(guestDto.getEmail())
                .subject("Check in")
                .text(visit.toString())
                .build());

        return modelMapper.map(visit, VisitDto.class);
    }

    @Transactional
    public void departure(Short roomNumber) throws JsonProcessingException {
        Visit visit = visitRepository.findByActiveIsTrueAndRoomNumber(roomNumber).orElseThrow(
                () -> new EntityNotFoundException(String.format("Active visit at room number %d not found", roomNumber), VISIT_NOT_FOUND));
        visit.setActive(false);
        visit = visitRepository.save(visit);

        roomService.setRoomOccupation(visit.getRoomNumber(), false);
        GuestDto guestDto = guestService.updateDiscount(visit.getGuestId(), visit.getCost());

        kafkaProducer.sendMessage(EmailDto.builder()
                .address(guestDto.getEmail())
                .subject("Departure")
                .text(visit.toString())
                .build());
    }

    public List<VisitDto> getVisits(Boolean active) {
        return (active != null ? visitRepository.findAllByActive(active) : visitRepository.findAll()).stream()
                .map(visit -> modelMapper.map(visit, VisitDto.class))
                .collect(Collectors.toList());
    }
}
