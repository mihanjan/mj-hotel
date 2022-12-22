package com.mj.guest.controller;

import com.mj.guest.dto.GuestDto;
import com.mj.guest.service.GuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/guests")
@RequiredArgsConstructor
@Slf4j
public class GuestController {

    private final GuestService guestService;

    @GetMapping("{id}")
    public GuestDto findById(@PathVariable String id) {
        log.info("find guest with id {}", id);
        return guestService.findById(id);
    }

    @PostMapping
    public GuestDto saveOrUpdate(@RequestBody GuestDto guestDto) {
        log.info("find or save guest {}", guestDto);
        return guestService.saveOrUpdate(guestDto);
    }

    @PatchMapping("/{guestId}/discount")
    public GuestDto updateDiscount(@PathVariable String guestId,
                                   @RequestParam double orderCost) {
        log.info("update discount for guest {} (order cost = {})", guestId, orderCost);
        return guestService.updateDiscount(guestId, orderCost);
    }
}
