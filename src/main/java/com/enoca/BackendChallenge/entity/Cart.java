package com.enoca.BackendChallenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cart",schema = "challenge")
public class Cart extends BaseEntity{

    @Column(name = "total_price")
    private double totalPrice;


    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cart")
    private List<Product> products;

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
    }
}
