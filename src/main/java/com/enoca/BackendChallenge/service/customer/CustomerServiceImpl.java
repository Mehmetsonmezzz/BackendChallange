package com.enoca.BackendChallenge.service.customer;

import com.enoca.BackendChallenge.converter.CustomerConverter;
import com.enoca.BackendChallenge.dto.CustomerResponse;
import com.enoca.BackendChallenge.entity.Customer;
import com.enoca.BackendChallenge.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse Add(Customer customer) {
      Customer createCustomer=  customerRepository.save(customer);

        return CustomerConverter.convertToResponse(createCustomer);
    }
}
