package com.enoca.BackendChallenge.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer",schema = "challenge")
public class Customer extends BaseEntity{

    @Column(name = "first_name")
    @NotBlank(message = "First name must not be blank")
    @Size(max = 50, message = "First name must be at most 50 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name must not be blank")
    @Size(max = 50, message = "Last name must be at most 255 characters")
    private String lastName;

    @Column(name = "email")
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must be at most 255 characters")
    private String email;

    @Column(name = "phone_number")
    @NotBlank(message = "Phone number must not be blank")
    private String phoneNumber;

    @Column(name = "address")
    @NotBlank(message = "Address must not be blank")
    @Size(max = 1000, message = "Address must be at most 1000 characters")
    private String address;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Order> orders;

    public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
    }


}
