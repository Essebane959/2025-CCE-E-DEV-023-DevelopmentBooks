package com.jwd.developmentbooks;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PriceCalculatorService {
    private static final BigDecimal UNIT = new BigDecimal("50.00");

    public BigDecimal total(List<BasketItem> items) {
        if (items == null) {
            throw new IllegalArgumentException("basket cannot be null");
        }
        if (items.isEmpty()) {
            return BigDecimal.ZERO;
        }
        int quantity = items.stream().mapToInt(BasketItem::quantity).sum();
        return UNIT
                .multiply(BigDecimal.valueOf(quantity))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
