package com.enoca.BackendChallenge.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Min(value = 0, message = "Total price must be greater than or equal to 0")
    private double totalPrice;


    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "cart")
    private Order order;



    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name="cart_product",schema = "challenge",joinColumns = @JoinColumn(name="cart_id"),
            inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> products=new ArrayList<>();


    public void addProduct(Product product){
        if(products == null){
            products = new ArrayList<>();
        }
        products.add(product);
    }

    public double calculateTotalAmount() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
    public void updateTotalAmount() {
        this.totalPrice = calculateTotalAmount();
    }

}
