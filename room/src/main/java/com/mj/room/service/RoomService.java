package com.mj.room.service;

import com.mj.exception.business.EntityNotFoundException;
import com.mj.exception.business.EntityUpdateException;
import com.mj.room.dto.RoomDto;
import com.mj.room.model.RoomType;
import com.mj.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.mj.exception.handler.GlobalErrorCode.ROOM_NOT_FOUND;
import static com.mj.exception.handler.GlobalErrorCode.ROOM_UPDATE_OCCUPATION;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public List<RoomDto> getFreeRooms(Byte maxPersons, List<RoomType> roomTypes) {
        return roomRepository
                .findRoomsByOccupiedIsFalseAndMaxPersonsGreaterThanEqualAndRoomTypeInOrderByNumber(maxPersons, roomTypes)
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    public RoomDto getRoomByNumber(Short roomNumber) {
        return roomRepository.findByNumber(roomNumber)
                .map(room -> modelMapper.map(room, RoomDto.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Room number %d not found", roomNumber), ROOM_NOT_FOUND));
    }

    public void setOccupied(Short roomNumber, Boolean occupied) {
        if (roomRepository.updateOccupied(roomNumber, occupied) <= 0) {
            throw new EntityUpdateException(
                    String.format("Room number %d occupation (to %b) update error", roomNumber, occupied), ROOM_UPDATE_OCCUPATION);
        }
    }
}
