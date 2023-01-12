package com.mj.visit.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class VisitUtils {

    public static double calculateCost(LocalDateTime arrival,
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

    public static String toStringCheckOnNull(Object obj, String alt) {
        return obj != null ? obj.toString() : alt;
    }

    public static boolean equalsCheckOnNull(Object checkObject, Object compareObject, boolean alt) {
        return checkObject != null ? checkObject.equals(compareObject) : alt;
    }
}
