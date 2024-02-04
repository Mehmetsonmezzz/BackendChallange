package com.enoca.BackendChallenge.dto;

import com.enoca.BackendChallenge.entity.Product;

import java.util.List;

public record CartResponse(long id, double totalPrice ,List<ProductResponse>products) {
}
