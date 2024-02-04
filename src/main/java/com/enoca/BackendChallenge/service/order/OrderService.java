package com.enoca.BackendChallenge.service.order;

import com.enoca.BackendChallenge.dto.OrderResponse;
import com.enoca.BackendChallenge.dto.OrderWithOrderDetailResponse;
import com.enoca.BackendChallenge.entity.Customer;
import com.enoca.BackendChallenge.entity.Order;

import java.util.List;

public interface OrderService {


     OrderResponse placeOrder(long customerId);

     OrderWithOrderDetailResponse findByOrderCode(String orderCode);

     List<OrderResponse> getAllOrdersForCustomer(Long customerId);

}
