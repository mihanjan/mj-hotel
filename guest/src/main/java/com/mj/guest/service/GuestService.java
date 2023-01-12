package com.mj.guest.service;

import com.mj.exception.business.EntityNotFoundException;
import com.mj.guest.dto.GuestDto;
import com.mj.guest.dto.NewGuestDto;
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

    public GuestDto findByPassport(String passport) {
        return guestRepository.findByPassport(passport)
                .map(guest -> modelMapper.map(guest, GuestDto.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Guest with passport %s not found", passport), GUEST_NOT_FOUND));
    }

    @Transactional
    public GuestDto save(NewGuestDto newGuestDto) {
        Guest guest = guestRepository.save(modelMapper.map(newGuestDto, Guest.class));
        return modelMapper.map(guest, GuestDto.class);
    }

    @Transactional
    public GuestDto update(String id, GuestDto guestDto) {
        Guest guest = guestRepository.findById(new ObjectId(id)).orElseThrow(
                () -> new EntityNotFoundException(String.format("Guest with id %s not found", id), GUEST_NOT_FOUND));

        if (guestDto.getPassport() != null) {
            guest.setPassport(guestDto.getPassport());
        }
        if (guestDto.getFullName() != null) {
            guest.setFullName(guestDto.getFullName());
        }
        if (guestDto.getEmail() != null) {
            guest.setEmail(guestDto.getEmail());
        }
        if (guestDto.getDiscountPercent() != null) {
            guest.setDiscountPercent(guestDto.getDiscountPercent());
        }

        return modelMapper.map(guestRepository.save(guest), GuestDto.class);
    }

    @Transactional
    public GuestDto discountRecount(String id, double orderCost) {
        Guest guest = guestRepository.findById(new ObjectId(id)).orElseThrow(
                () -> new EntityNotFoundException(String.format("Guest with id %s not found", id), GUEST_NOT_FOUND));
        guest.setDiscountPercent(discountUtil.calculateDiscount(guest.getDiscountPercent(), orderCost));
        return modelMapper.map(guestRepository.save(guest), GuestDto.class);
    }

    @Transactional
    public void delete(String id) {
        guestRepository.deleteById(new ObjectId(id));
    }
}
