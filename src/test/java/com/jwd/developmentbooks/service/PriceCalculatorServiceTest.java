package com.jwd.developmentbooks.service;

import com.jwd.developmentbooks.model.BasketItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceCalculatorServiceTest {

    private final PriceCalculatorService calculator = new PriceCalculatorService();

    @Test
    void givenOneBook_whenCalculateTotal_thenReturn50() {
        List<BasketItem> basket = List.of(new BasketItem("9780132350884", 1));
        BigDecimal total = calculator.total(basket);
        assertEquals(new BigDecimal("50.00"), total);
    }

    @Test
    void givenOneBook_whenCalculateTotal_thenNotReturn100() {
        List<BasketItem> basket = List.of(new BasketItem("9780132350884", 1));
        BigDecimal total = calculator.total(basket);
        assertNotEquals(new BigDecimal("100.00"), total);
    }

    @Test
    void givenNullBasket_whenCalculateTotal_thenThrowException() {
        List<BasketItem> basket = null;
        assertThrows(IllegalArgumentException.class, () -> calculator.total(basket));
    }

    @Test
    void givenTwoDifferentBooks_whenCalculateTotal_thenApplyFivePercentDiscount() {
        List<BasketItem> basket = List.of(
                new BasketItem("9780132350884", 1),
                new BasketItem("9780137081073", 1)
        );
        BigDecimal total = calculator.total(basket);
        assertEquals(new BigDecimal("95.00"), total);
    }

    @Test
    void givenThreeDifferentBooks_whenCalculateTotal_thenApplyTenPercentDiscount() {
        List<BasketItem> basket = List.of(
                new BasketItem("9780132350884", 1),
                new BasketItem("9780137081073", 1),
                new BasketItem("9780134494166", 1)
        );

        BigDecimal total = calculator.total(basket);

        assertEquals(new BigDecimal("135.00"), total);
    }

    @Test
    void givenFourDifferentBooks_whenCalculateTotal_thenApplyTwentyPercentDiscount() {
        List<BasketItem> basket = List.of(
                new BasketItem("9780132350884", 1),
                new BasketItem("9780137081073", 1),
                new BasketItem("9780134494166", 1),
                new BasketItem("9780321146533", 1)
        );
        BigDecimal total = calculator.total(basket);
        assertEquals(new BigDecimal("160.00"), total);
    }

    @Test
    void givenFiveDifferentBooks_whenCalculateTotal_thenApplyTwentyFivePercentDiscount() {
        List<BasketItem> basket = List.of(
                new BasketItem("9780132350884", 1),
                new BasketItem("9780137081073", 1),
                new BasketItem("9780134494166", 1),
                new BasketItem("9780321146533", 1),
                new BasketItem("9780131177055", 1)
        );
        BigDecimal total = calculator.total(basket);
        assertEquals(new BigDecimal("187.50"), total);
    }

    @Test
    void givenBasketWithDuplicates_whenCalculateTotal_thenApplyOptimalDiscounts() {
        List<BasketItem> basket = List.of(
                new BasketItem("9780132350884", 2),
                new BasketItem("9780137081073", 2),
                new BasketItem("9780134494166", 2),
                new BasketItem("9780321146533", 1),
                new BasketItem("9780131177055", 1)
        );

        BigDecimal total = calculator.total(basket);

        assertEquals(new BigDecimal("322.50"), total);
    }

}