package com.jwd.developmentbooks.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwd.developmentbooks.model.BasketItem;
import com.jwd.developmentbooks.service.PriceCalculatorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CheckoutControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private AutoCloseable mocks;

    @Mock
    private PriceCalculatorService priceCalculatorService;

    @BeforeEach
    void setup() {
        mocks = MockitoAnnotations.openMocks(this);
        CheckoutController controller = new CheckoutController(priceCalculatorService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void givenBasket_whenPostCheckout_thenReturnTotal() throws Exception {
        List<BasketItem> basket = List.of(
                new BasketItem("9780132350884", 2),
                new BasketItem("9780137081073", 2),
                new BasketItem("9780134494166", 2),
                new BasketItem("9780321146533", 1),
                new BasketItem("9780131177055", 1)
        );

        when(priceCalculatorService.total(basket)).thenReturn(new BigDecimal("322.50"));

        mockMvc.perform(post("/api/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(basket)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value("322.50"));
    }
}