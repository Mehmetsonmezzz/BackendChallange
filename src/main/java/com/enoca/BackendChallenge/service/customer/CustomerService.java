package com.enoca.BackendChallenge.service.customer;

import com.enoca.BackendChallenge.dto.CustomerResponse;
import com.enoca.BackendChallenge.entity.Customer;

public interface CustomerService {

    CustomerResponse Add(Customer customer);

}
