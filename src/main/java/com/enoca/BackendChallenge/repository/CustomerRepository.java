package com.enoca.BackendChallenge.repository;

import com.enoca.BackendChallenge.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
