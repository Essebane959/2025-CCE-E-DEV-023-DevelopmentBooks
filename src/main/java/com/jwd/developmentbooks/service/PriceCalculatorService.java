package com.jwd.developmentbooks.service;

import com.jwd.developmentbooks.model.BasketItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Set<String> distinct = items.stream().map(BasketItem::isbn).collect(Collectors.toSet());

        BigDecimal total = UNIT.multiply(BigDecimal.valueOf(quantity));

        if (distinct.size() == 2 && quantity == 2) {
            total = total.subtract(total.multiply(new BigDecimal("0.05")));
        } else if (distinct.size() == 3 && quantity == 3) {
            total = total.subtract(total.multiply(new BigDecimal("0.10")));
        }

        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
