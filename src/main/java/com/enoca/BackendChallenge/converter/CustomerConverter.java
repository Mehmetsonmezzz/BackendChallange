package com.enoca.BackendChallenge.converter;

import com.enoca.BackendChallenge.dto.CustomerResponse;
import com.enoca.BackendChallenge.dto.ProductResponse;
import com.enoca.BackendChallenge.entity.Customer;
import com.enoca.BackendChallenge.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerConverter {
    public static CustomerResponse convertToResponse(Customer customer) {
        return new CustomerResponse(customer.getFirstName(),customer.getLastName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber());
    }

    public static List<CustomerResponse> convertListToResponse(List<Customer> customerList) {
        return customerList.stream()
                .map(CustomerConverter::convertToResponse)
                .collect(Collectors.toList());
    }



}
