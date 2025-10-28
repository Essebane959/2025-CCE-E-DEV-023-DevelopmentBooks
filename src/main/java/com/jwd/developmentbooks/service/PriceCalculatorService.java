package com.jwd.developmentbooks.service;

import com.jwd.developmentbooks.model.BasketItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceCalculatorService {

    private static final BigDecimal UNIT = new BigDecimal("50.00");
    private static final Map<Integer, BigDecimal> DISCOUNT = Map.of(
            2, new BigDecimal("0.05"),
            3, new BigDecimal("0.10"),
            4, new BigDecimal("0.20"),
            5, new BigDecimal("0.25")
    );

    public BigDecimal total(List<BasketItem> items) {
        if (items == null) {
            throw new IllegalArgumentException("basket cannot be null");
        }
        if (items.isEmpty()) {
            return BigDecimal.ZERO;
        }

        Map<String, Integer> countMap = new HashMap<>();
        for (BasketItem basketItem : items) {
            countMap.merge(basketItem.isbn(), basketItem.quantity(), Integer::sum);
        }

        List<Integer> groupSizes = new ArrayList<>();
        while (!countMap.isEmpty()) {
            int distinct = countMap.size();
            groupSizes.add(distinct);
            countMap.replaceAll((isbn, remainingCopies) -> remainingCopies - 1);
            countMap.entrySet().removeIf(entry -> entry.getValue() <= 0);
        }

        BigDecimal total = BigDecimal.ZERO;
        for (int size : groupSizes) {
            BigDecimal group = UNIT.multiply(BigDecimal.valueOf(size));
            BigDecimal rate = DISCOUNT.getOrDefault(size, BigDecimal.ZERO);
            group = group.subtract(group.multiply(rate));
            total = total.add(group);
        }

        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
