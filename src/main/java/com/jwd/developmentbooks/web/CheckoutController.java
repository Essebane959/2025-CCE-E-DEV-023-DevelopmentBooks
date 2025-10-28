package com.jwd.developmentbooks.web;

import com.jwd.developmentbooks.model.BasketItem;
import com.jwd.developmentbooks.service.PriceCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CheckoutController {

    private final PriceCalculatorService calculator;

    public CheckoutController(PriceCalculatorService calculator) {
        this.calculator = calculator;
    }

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, String>> checkout(@RequestBody List<BasketItem> basket) {
        BigDecimal total = calculator.total(basket);
        return ResponseEntity.ok(Map.of("total", total.toPlainString()));
    }
}