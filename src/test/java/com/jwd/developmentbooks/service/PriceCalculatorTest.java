package com.jwd.developmentbooks.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceCalculatorTest {

    private final PriceCalculator calculator = new PriceCalculator();

    @Test
    void givenOneBook_whenCalculateTotal_thenReturn50() {
        List<BasketItem> basket = List.of(new BasketItem("1234567891452", 1));
        BigDecimal total = calculator.total(basket);
        assertEquals(new BigDecimal("50.00"), total);
    }

    @Test
    void givenOneBook_whenCalculateTotal_thenNotReturn100() {
        List<BasketItem> basket = List.of(new BasketItem("1234567891452", 1));
        BigDecimal total = calculator.total(basket);
        assertNotEquals(new BigDecimal("100.00"), total);
    }

    @Test
    void givenNullBasket_whenCalculateTotal_thenThrowException() {
        List<BasketItem> basket = null;
        assertThrows(IllegalArgumentException.class, () -> calculator.total(basket));
    }
}