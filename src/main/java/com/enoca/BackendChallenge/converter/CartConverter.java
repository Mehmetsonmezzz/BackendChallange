package com.enoca.BackendChallenge.converter;

import com.enoca.BackendChallenge.dto.CartResponse;
import com.enoca.BackendChallenge.dto.CustomerResponse;
import com.enoca.BackendChallenge.entity.Cart;
import com.enoca.BackendChallenge.entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CartConverter {
    public static CartResponse convertToResponse(Cart cart) {
        return new CartResponse(cart.getId(),cart.getTotalPrice(),ProductConverter.convertListToResponse(cart.getProducts()));
    }

    public static List<CartResponse> convertListToResponse(List<Cart> cartList) {
        return cartList.stream()
                .map(CartConverter::convertToResponse)
                .collect(Collectors.toList());
    }
}
