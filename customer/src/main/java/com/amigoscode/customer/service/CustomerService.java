package com.amigoscode.customer.service;

import com.amigoscode.customer.request.customer.CustomerRegistrationRequest;

public interface CustomerService {
    void registerCustomer(CustomerRegistrationRequest request);
}
