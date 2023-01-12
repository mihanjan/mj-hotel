package com.mj.visit.service;

import com.mj.exception.business.ServiceRequestException;
import com.mj.exception.dto.ErrorResponse;
import com.mj.visit.dto.GuestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final WebClient.Builder webClientBuilder;

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
                        .path(String.format("/api/v1/guests/%s/discount-recount", guestId))
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
