package com.mj.guest.config;

import com.mj.exception.handler.GlobalHotelExceptionHandler;
import com.mj.exception.handler.ServiceType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionHandlerConfig {

    @Bean
    public GlobalHotelExceptionHandler exceptionHandler() {
        return new GlobalHotelExceptionHandler(ServiceType.GUEST);
    }
}
