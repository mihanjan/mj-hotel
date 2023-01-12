package com.mj.room.controller;

import com.mj.room.dto.NewRoomDto;
import com.mj.room.dto.RoomDto;
import com.mj.room.model.RoomType;
import com.mj.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.mj.room.util.RoomUtils.toStringCheckOnNull;

@RestController
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomService roomService;

    @GetMapping("{id}")
    public ResponseEntity<RoomDto> getById(@PathVariable Integer id) {
        log.info("get room (id = {})", id);
        return ResponseEntity.ok(roomService.getById(id));
    }

    @GetMapping("/by-number")
    public ResponseEntity<RoomDto> getByNumber(@RequestParam Short number) {
        log.info("get room number {}", number);
        return ResponseEntity.ok(roomService.getByNumber(number));
    }

    @GetMapping
    public List<RoomDto> getAll(@RequestParam(required = false) Boolean occupied,
                                @RequestParam(required = false) Byte personsNumber,
                                @RequestParam(required = false) List<RoomType> roomTypes) {
        log.info("get rooms (occupied = {}, personsNumber = {}, roomTypes = {})",
                toStringCheckOnNull(occupied, "all"),
                toStringCheckOnNull(personsNumber, "all"),
                toStringCheckOnNull(roomTypes, "all"));
        return roomService.getRooms(occupied, personsNumber, roomTypes);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomDto> save(@Valid @RequestBody NewRoomDto newRoomDto) {
        log.info("save room {}", newRoomDto);
        return ResponseEntity.ok(roomService.save(newRoomDto));
    }

    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomDto> update(@PathVariable Integer id,
                                          @Valid @RequestBody RoomDto roomDto) {
        log.info("update room (id {}) with {}", id, roomDto);
        return ResponseEntity.ok(roomService.update(id, roomDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        log.info("delete room (id = {})", id);
        roomService.delete(id);
        return ResponseEntity.ok().build();
    }
}
