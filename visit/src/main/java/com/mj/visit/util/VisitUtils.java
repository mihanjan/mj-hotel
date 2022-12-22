package com.mj.visit.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class VisitUtils {

    public double calculateCost(LocalDateTime arrival,
                                LocalDateTime departure,
                                double pricePerHour,
                                double discountPercent) {
        arrival = arrival.truncatedTo(ChronoUnit.HOURS);
        if (departure.getMinute() > 0) {
            departure = departure.plusHours(1);
        }
        departure = departure.truncatedTo(ChronoUnit.HOURS);
        double cost = pricePerHour * ChronoUnit.HOURS.between(arrival, departure);
        return (cost - (cost * (discountPercent / 100)));
    }
}
