package com.enoca.BackendChallenge.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "order",schema = "challenge")
public class Order extends BaseEntity{


    @Column(name = "total_amount")
    @Min(value = 0, message = "Total amount must be greater than or equal to 0")
    private double totalAmount;

    @Column(name = "quantity")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="customer_id")
    @NotNull(message = "Customer must be specified")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "cart_id")
    @NotNull(message = "Cart must be specified")
    private Cart cart;


    @Column(name = "order_code")
    @NotBlank(message = "Order code must not be blank")
    private String orderCode;


    @Column(name = "order_date")
    private Date orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderDetail> orderDetails;
}
