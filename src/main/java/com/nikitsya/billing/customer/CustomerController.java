package com.nikitsya.billing.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/v1/customers")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CreateCustomerRequest request) {
        Customer saved = customerRepository.save(
                new Customer(request.name(), request.email().toLowerCase(Locale.ROOT))
        );
        CustomerResponse response = new CustomerResponse(saved.getId(), saved.getName(), saved.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/v1/customers/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            CustomerResponse response = new CustomerResponse(customer.get().getId(), customer.get().getName(), customer.get().getEmail());
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/v1/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) return ResponseEntity.notFound().build();
        customerRepository.delete(customer.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/v1/customers")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> response = new ArrayList<>();
        for (Customer customer : customers) {
            response.add(new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail()));
        }
        return ResponseEntity.ok().body(response);
    }
}

