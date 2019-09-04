package com.easy.tailoring.controller;

import com.easy.tailoring.exceptions.ResourceNotFoundException;
import com.easy.tailoring.model.Customer;
import com.easy.tailoring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")

    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/customers/{customersId}")
    public Customer updateCustomer(@PathVariable Long customersId,
                                   @Valid @RequestBody Customer customerRequest) {
        return customerRepository.findById(customersId)
                .map(customer -> {
                    return customerRepository.save(customer);
                }).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + customersId));
    }


    @DeleteMapping("/customers/{customersId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customersId) {
        return customerRepository.findById(customersId)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Customers not found with id " + customersId));
    }
}