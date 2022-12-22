package com.mj.visit.service;

import com.mj.exception.business.ServiceRequestException;
import com.mj.exception.dto.ErrorResponse;
import com.mj.visit.dto.GuestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final WebClient.Builder webClientBuilder;

//    public GuestDto findOrSaveGuest(CheckInDto checkInDto) {
//        GuestDto guestDto = GuestDto.builder()
//                .fullName(checkInDto.getFullName())
//                .passport(checkInDto.getPassport())
//                .email(checkInDto.getEmail())
//                .build();
//
//        return webClientBuilder.build()
//                .post()
//                .uri("http://GUEST/api/v1/guests")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .body(Mono.just(guestDto), GuestDto.class)
//                .retrieve()
//                .onStatus(HttpStatus::isError, clientResponse -> clientResponse
//                        .bodyToMono(ErrorResponse.class)
//                        .map(errorResponse -> new ServiceRequestException(
//                                errorResponse.getMessage(), errorResponse.getCode(), clientResponse.statusCode())))
//                .bodyToMono(GuestDto.class)
//                .block();
//    }

    public GuestDto findById(String guestId) {
        return webClientBuilder.build()
                .get()
                .uri(String.format("http://GUEST/api/v1/guests/%s", guestId))
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> clientResponse
                        .bodyToMono(ErrorResponse.class)
                        .map(errorResponse -> new ServiceRequestException(
                                errorResponse.getMessage(), errorResponse.getCode(), clientResponse.statusCode())))
                .bodyToMono(GuestDto.class)
                .block();
    }

    public GuestDto updateDiscount(String guestId, double orderCost) {
        return webClientBuilder.build()
                .patch()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("ROOM")
                        .path(String.format("/api/v1/guests/%s/discount", guestId))
                        .queryParam("orderCost", orderCost)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> clientResponse
                        .bodyToMono(ErrorResponse.class)
                        .map(errorResponse -> new ServiceRequestException(
                                errorResponse.getMessage(), errorResponse.getCode(), clientResponse.statusCode())))
                .bodyToMono(GuestDto.class)
                .block();
    }
}
