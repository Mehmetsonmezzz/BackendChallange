package com.enoca.BackendChallenge.dto;

import java.util.Date;
import java.util.List;

public record OrderWithOrderDetailResponse(double totalAmount, String orderCode, Date orderDate, List<OrderDetailResponse> orderDetail) {
}
