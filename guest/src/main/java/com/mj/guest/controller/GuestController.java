package com.mj.guest.controller;

import com.mj.guest.dto.GuestDto;
import com.mj.guest.dto.NewGuestDto;
import com.mj.guest.service.GuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/guests")
@RequiredArgsConstructor
@Slf4j
public class GuestController {

    private final GuestService guestService;

    @GetMapping("{id}")
    public ResponseEntity<GuestDto> findById(@PathVariable String id) {
        log.info("find guest with id {}", id);
        return ResponseEntity.ok(guestService.findById(id));
    }

    @GetMapping("/by-passport")
    public ResponseEntity<GuestDto> findByPassport(@RequestParam String passport) {
        log.info("find guest by passport {}", passport);
        return ResponseEntity.ok(guestService.findByPassport(passport));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GuestDto> save(@Valid @RequestBody NewGuestDto newGuestDto) {
        log.info("save guest {}", newGuestDto);
        return ResponseEntity.ok(guestService.save(newGuestDto));
    }

    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GuestDto> update(@PathVariable String id,
                                           @Valid @RequestBody GuestDto guestDto) {
        log.info("update guest {} with {}", id, guestDto);
        return ResponseEntity.ok(guestService.update(id, guestDto));
    }

    @PatchMapping("/{id}/discount-recount")
    public ResponseEntity<GuestDto> updateDiscount(@PathVariable String id,
                                                   @RequestParam double orderCost) {
        log.info("update discount for guest {} (order cost = {})", id, orderCost);
        return ResponseEntity.ok(guestService.discountRecount(id, orderCost));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id) {
        log.info("delete guest with id {}", id);
        guestService.delete(id);
        return ResponseEntity.ok().build();
    }
}
