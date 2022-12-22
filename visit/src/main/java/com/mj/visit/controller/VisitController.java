package com.mj.visit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mj.visit.dto.CheckInDto;
import com.mj.visit.dto.VisitDto;
import com.mj.visit.model.Visit;
import com.mj.visit.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/visits")
@RequiredArgsConstructor
@Slf4j
public class VisitController {

    private final VisitService visitService;

    @PostMapping
    public VisitDto checkIn(@RequestBody CheckInDto checkInDto) throws JsonProcessingException {
        log.info("save visit {}", checkInDto);
        return visitService.checkIn(checkInDto);
    }

    @GetMapping("/departure")
    public ResponseEntity departure(@RequestParam Short roomNumber) throws JsonProcessingException {
        log.info("departure from room number {}", roomNumber);
        visitService.departure(roomNumber);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<VisitDto> getVisits(@RequestParam(required = false) Boolean active) {
        log.info("get visits {}", active != null ? String.format("(active = %b)", active) : "(all)");
        return visitService.getVisits(active);
    }
}
