package com.jwd.developmentbooks.model;

import java.math.BigDecimal;

public record Book(String isbn, String title, String author, int year, BigDecimal price) {}
