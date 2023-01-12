package com.mj.room.service;

import com.mj.exception.business.EntityNotFoundException;
import com.mj.room.dto.NewRoomDto;
import com.mj.room.dto.RoomDto;
import com.mj.room.model.Room;
import com.mj.room.model.RoomType;
import com.mj.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.mj.exception.handler.GlobalErrorCode.ROOM_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public List<RoomDto> getRooms(Boolean occupied, Byte personsNumber, List<RoomType> roomTypes) {
        return roomRepository.findAllByOrderByNumber().stream()
                .filter(room -> occupied == null || room.isOccupied() == occupied)
                .filter(room -> personsNumber == null || room.getMaxPersons() >= personsNumber)
                .filter(room -> roomTypes == null || roomTypes.isEmpty() || roomTypes.contains(room.getRoomType()))
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    public RoomDto getById(Integer id) {
        return roomRepository.findById(id)
                .map(room -> modelMapper.map(room, RoomDto.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Room (id = %d) not found", id), ROOM_NOT_FOUND));
    }

    public RoomDto getByNumber(Short number) {
        return roomRepository.findByNumber(number)
                .map(room -> modelMapper.map(room, RoomDto.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Room number %d not found", number), ROOM_NOT_FOUND));
    }

    @Transactional
    public RoomDto save(NewRoomDto newRoomDto) {
        Room saved = roomRepository.save(modelMapper.map(newRoomDto, Room.class));
        return modelMapper.map(saved, RoomDto.class);
    }

    @Transactional
    public RoomDto update(Integer id, RoomDto roomDto) {
        Room room = roomRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Room (id = %d) not found", id), ROOM_NOT_FOUND));

        if (roomDto.getNumber() != null) {
            room.setNumber(roomDto.getNumber());
        }
        if (roomDto.getRoomType() != null) {
            room.setRoomType(roomDto.getRoomType());
        }
        if (roomDto.getMaxPersons() != null) {
            room.setMaxPersons(roomDto.getMaxPersons());
        }
        if (roomDto.getPricePerHour() != null) {
            room.setPricePerHour(roomDto.getPricePerHour());
        }
        if (roomDto.getOccupied() != null) {
            room.setOccupied(roomDto.getOccupied());
        }

        return modelMapper.map(roomRepository.save(room), RoomDto.class);
    }

    @Transactional
    public void delete(Integer id) {
        roomRepository.deleteById(id);
    }
}
