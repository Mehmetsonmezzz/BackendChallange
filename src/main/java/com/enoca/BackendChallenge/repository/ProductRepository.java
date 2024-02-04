package com.enoca.BackendChallenge.repository;

import com.enoca.BackendChallenge.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
