package com.mj.guest.service;

import com.mj.exception.business.EntityNotFoundException;
import com.mj.guest.dto.GuestDto;
import com.mj.guest.model.Guest;
import com.mj.guest.repository.GuestRepository;
import com.mj.guest.util.DiscountUtil;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mj.exception.handler.GlobalErrorCode.GUEST_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final DiscountUtil discountUtil;
    private final ModelMapper modelMapper;

    public GuestDto findById(String id) {
        return guestRepository.findById(new ObjectId(id))
                .map(guest -> modelMapper.map(guest, GuestDto.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Guest with id %s not found", id), GUEST_NOT_FOUND));
    }

    @Transactional
    public GuestDto saveOrUpdate(GuestDto guestDto) {
//        Guest guest = guestRepository.findByPassport(guestDto.getPassport()).get();
//
//        if (!guest.getFullName().equals(guestDto.getFullName())) {
//            throw new EntityNotFoundException("123", GUEST_NOT_FOUND);
//        }

        Guest saved = guestRepository.save(
                guestRepository.findByPassport(guestDto.getPassport()).stream()
                        .peek(guest -> guest.setEmail(guestDto.getEmail()))
                        .findFirst()
                        .orElseGet(() -> modelMapper.map(guestDto, Guest.class)));

        return modelMapper.map(saved, GuestDto.class);
    }

    @Transactional
    public GuestDto save(GuestDto guestDto) {
        Guest guest = guestRepository.save(modelMapper.map(guestDto, Guest.class));
        return modelMapper.map(guest, GuestDto.class);
    }

    @Transactional
    public GuestDto updateDiscount(String guestId, double orderCost) {
        Guest guest = guestRepository.findById(new ObjectId(guestId)).orElseThrow(
                () -> new EntityNotFoundException(String.format("Guest with id %s not found", guestId), GUEST_NOT_FOUND));
        guest.setDiscountPercent(discountUtil.calculateDiscount(guest.getDiscountPercent(), orderCost));
        return modelMapper.map(guestRepository.save(guest), GuestDto.class);
    }
}
