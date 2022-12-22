package com.mj.guest.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiscountUtil {

    @Value("${discount.onePercentAmount}")
    private double onePercentAmount;

    @Value("${discount.maxPercent}")
    private double maxPercent;

    public double calculateDiscount(double currentDiscount, double orderCost) {
        double newDiscount = currentDiscount + (orderCost / onePercentAmount);
        return Math.min(newDiscount, maxPercent);
    }
}
