package com.enoca.BackendChallenge.controller;

import com.enoca.BackendChallenge.dto.OrderResponse;
import com.enoca.BackendChallenge.dto.OrderWithOrderDetailResponse;
import com.enoca.BackendChallenge.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add/{customerId}")
    public ResponseEntity<OrderResponse> placeOrder(@PathVariable Long customerId) {

        OrderResponse order = orderService.placeOrder(customerId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<OrderWithOrderDetailResponse> getOrderForCode(@PathVariable String orderCode) {
        OrderWithOrderDetailResponse order= orderService.findByOrderCode(orderCode);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @GetMapping("/customer/{customerId}")
    public List<OrderResponse> getAllOrdersForCustomer(@PathVariable Long customerId) {

        return orderService.getAllOrdersForCustomer(customerId);
    }

}