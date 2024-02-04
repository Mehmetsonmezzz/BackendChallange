package com.enoca.BackendChallenge.dto;

import com.enoca.BackendChallenge.entity.Cart;
import com.enoca.BackendChallenge.entity.Customer;
import com.enoca.BackendChallenge.entity.OrderDetail;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public record OrderResponse(double totalAmount,String orderCode,Date orderDate) {

}
