package com.mj.room.controller;

import com.mj.room.dto.RoomDto;
import com.mj.room.model.RoomType;
import com.mj.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/free")
    public List<RoomDto> getFreeRooms(@RequestParam Byte personsNumber,
                                      @RequestParam List<RoomType> roomTypes) {
        log.info("get free {} rooms for {} persons", roomTypes, personsNumber);
        return roomService.getFreeRooms(personsNumber, roomTypes);
    }

    @GetMapping("{roomNumber}")
    public ResponseEntity<RoomDto> getRoomByNumber(@PathVariable Short roomNumber) {
        log.info("get room number {}", roomNumber);
        return ResponseEntity.ok(roomService.getRoomByNumber(roomNumber));
    }

    @PutMapping("{roomNumber}")
    public ResponseEntity setRoomOccupied(@PathVariable Short roomNumber,
                                          @RequestParam Boolean occupied) {
        log.info("set room {} occupation {}", roomNumber, occupied);
        roomService.setOccupied(roomNumber, occupied);
        return ResponseEntity.ok().build();
    }
}
