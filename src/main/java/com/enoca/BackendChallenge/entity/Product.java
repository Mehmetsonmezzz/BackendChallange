package com.enoca.BackendChallenge.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product",schema = "challenge")
public class Product extends BaseEntity{

    @Column(name = "name")
    @NotBlank(message = "Name must not be blank")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @Column(name = "category")
    @NotBlank(message = "Category must not be blank")
    @Size(max = 50, message = "Category must be at most 50 characters")
    private String category;

    @Column(name = "price")
    @NotNull(message = "Stock quantity must be specified")
    @Min(value = 0, message = "Stock quantity must be greater than or equal to 0")
    private Double price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;





    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name="cart_product",schema = "challenge",joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="cart_id"))
    private List<Cart> carts=new ArrayList<>();


    public void addCart(Cart cart){
        if(carts == null){
            carts = new ArrayList<>();
        }
        carts.add(cart);
    }
}
