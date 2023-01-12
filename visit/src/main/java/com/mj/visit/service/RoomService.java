package com.mj.visit.service;

import com.mj.exception.business.ServiceRequestException;
import com.mj.exception.dto.ErrorResponse;
import com.mj.visit.dto.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final WebClient.Builder webClientBuilder;

    public RoomDto getRoom(Integer roomId) {
        return webClientBuilder.build()
                .get()
                .uri("http://ROOM/api/v1/rooms/" + roomId)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> clientResponse
                        .bodyToMono(ErrorResponse.class)
                        .map(errorResponse -> new ServiceRequestException(
                                errorResponse.getMessage(), errorResponse.getCode(), clientResponse.statusCode())))
                .bodyToMono(RoomDto.class)
                .block();
    }

    public void setRoomOccupation(Integer roomId, boolean occupied) {
        RoomDto roomDto = RoomDto.builder()
                .occupied(occupied)
                .build();

        webClientBuilder.build()
                .patch()
                .uri("http://ROOM/api/v1/rooms/" + roomId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(roomDto), RoomDto.class)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> clientResponse
                        .bodyToMono(ErrorResponse.class)
                        .map(errorResponse -> new ServiceRequestException(
                                errorResponse.getMessage(), errorResponse.getCode(), clientResponse.statusCode())))
                .toBodilessEntity()
                .block();
    }
}
