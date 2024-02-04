package com.enoca.BackendChallenge.dto;

import java.time.LocalDateTime;

public record ProductResponse(Long id, String name, String category, Double price, Integer stockQuantity,
                              LocalDateTime createdDate) {
}
