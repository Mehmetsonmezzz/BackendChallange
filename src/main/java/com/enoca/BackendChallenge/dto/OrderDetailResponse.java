package com.enoca.BackendChallenge.dto;

import com.enoca.BackendChallenge.entity.Order;
import com.enoca.BackendChallenge.entity.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record OrderDetailResponse(String productName, double productPrice, int productQuantity) {

}
