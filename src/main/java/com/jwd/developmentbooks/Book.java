package com.jwd.developmentbooks;

import java.math.BigDecimal;

public record Book(String isbn, String title, String author, int year, BigDecimal price) {}
