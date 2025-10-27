package com.jwd.developmentbooks.service;

import com.jwd.developmentbooks.model.BasketItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PriceCalculatorService {

    private static final BigDecimal UNIT = new BigDecimal("50.00");
    private static final Map<Integer, BigDecimal> DISCOUNT = Map.of(
            2, new BigDecimal("0.05"),
            3, new BigDecimal("0.10"),
            4, new BigDecimal("0.20"),
            5, new BigDecimal("0.25")
    );

    public BigDecimal total(List<BasketItem> items) {
        if (items == null) throw new IllegalArgumentException("Basket cannot be null");
        if (items.isEmpty()) return BigDecimal.ZERO;

        int quantity = items.stream().mapToInt(BasketItem::quantity).sum();
        Set<String> distinct = items.stream().map(BasketItem::isbn).collect(Collectors.toSet());

        BigDecimal total = UNIT.multiply(BigDecimal.valueOf(quantity));
        if (quantity == distinct.size() && DISCOUNT.containsKey(distinct.size())) {
            BigDecimal rate = DISCOUNT.get(distinct.size());
            total = total.subtract(total.multiply(rate));
        }

        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
