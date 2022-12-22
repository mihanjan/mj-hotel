package com.mj.visit.service;

import com.mj.exception.business.ServiceRequestException;
import com.mj.exception.dto.ErrorResponse;
import com.mj.visit.dto.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final WebClient.Builder webClientBuilder;

    public RoomDto getRoom(Short roomNumber) {
        return webClientBuilder.build()
                .get()
                .uri("http://ROOM/api/v1/rooms/" + roomNumber)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> clientResponse
                        .bodyToMono(ErrorResponse.class)
                        .map(errorResponse -> new ServiceRequestException(
                                errorResponse.getMessage(), errorResponse.getCode(), clientResponse.statusCode())))
                .bodyToMono(RoomDto.class)
                .block();
    }

    public void setRoomOccupation(Short roomNumber, boolean occupied) {
        webClientBuilder.build()
                .put()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("ROOM")
                        .path("/api/v1/rooms/" + roomNumber)
                        .queryParam("occupied", occupied)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> clientResponse
                        .bodyToMono(ErrorResponse.class)
                        .map(errorResponse -> new ServiceRequestException(
                                errorResponse.getMessage(), errorResponse.getCode(), clientResponse.statusCode())))
                .toBodilessEntity()
                .block();
    }
}
