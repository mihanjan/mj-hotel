package com.mj.email.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mj.email.dto.EmailDto;
import com.mj.email.service.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final EmailSender emailSender;

    @KafkaListener(topics = {"${topic.email}"})
    public void consumeMessage(String message) throws JsonProcessingException {
        log.info("Message consumed {}", message);
        EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);
        log.info("Sending email {}", emailDto);
        emailSender.send(emailDto.getAddress(), emailDto.getSubject(), emailDto.getText());
    }
}
