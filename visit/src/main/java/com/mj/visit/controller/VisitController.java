package com.mj.visit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mj.visit.dto.CheckInDto;
import com.mj.visit.dto.VisitDto;
import com.mj.visit.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mj.visit.util.VisitUtils.toStringCheckOnNull;

@RestController
@RequestMapping("api/v1/visits")
@RequiredArgsConstructor
@Slf4j
public class VisitController {

    private final VisitService visitService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDto> checkIn(@RequestBody CheckInDto checkInDto) throws JsonProcessingException {
        log.info("save visit {}", checkInDto);
        return ResponseEntity.ok(visitService.checkIn(checkInDto));
    }

    @GetMapping("/departure")
    public ResponseEntity departure(@RequestParam Integer roomId) throws JsonProcessingException {
        log.info("departure from room (id = {})", roomId);
        visitService.departure(roomId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<VisitDto> getVisits(@RequestParam(required = false) Boolean active,
                                    @RequestParam(required = false) String guestId,
                                    @RequestParam(required = false) Integer roomId) {
        log.info("get visits (active = {}, guestId = {}, roomId = {})",
                toStringCheckOnNull(active, "all"),
                toStringCheckOnNull(guestId, "all"),
                toStringCheckOnNull(roomId, "all"));

        return visitService.getVisits(active, guestId, roomId);
    }
}
