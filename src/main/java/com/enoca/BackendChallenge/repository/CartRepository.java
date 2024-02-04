package com.enoca.BackendChallenge.repository;

import com.enoca.BackendChallenge.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("SELECT c FROM Cart c WHERE c.customer.id = :customerId")
    Optional<Cart> getCartByCustomer(@Param("customerId") long customerId);


}
