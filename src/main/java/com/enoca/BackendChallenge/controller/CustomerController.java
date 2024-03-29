package com.enoca.BackendChallenge.controller;

import com.enoca.BackendChallenge.dto.CustomerResponse;
import com.enoca.BackendChallenge.entity.Customer;
import com.enoca.BackendChallenge.service.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }



    @PostMapping("/")
   public ResponseEntity<CustomerResponse> AddCustomer
           (@RequestBody Customer customer){
       CustomerResponse customerResponse= customerService.Add(customer);


        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
   }

}
