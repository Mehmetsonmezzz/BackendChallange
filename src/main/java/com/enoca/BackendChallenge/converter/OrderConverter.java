package com.enoca.BackendChallenge.converter;

import com.enoca.BackendChallenge.dto.CustomerResponse;
import com.enoca.BackendChallenge.dto.OrderResponse;

import com.enoca.BackendChallenge.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {

    public static OrderResponse convertToResponse(Order order) {
        return new OrderResponse(order.getTotalAmount(),order.getOrderCode(),order.getOrderDate());
    }

    public static List<OrderResponse> convertListToResponse(List<Order> orderList) {
        return orderList.stream()
                .map(OrderConverter::convertToResponse)
                .collect(Collectors.toList());
    }
}
