package com.amigoscode.customer.serviceimpl;

import com.amigoscode.customer.mapper.customer.CustomerMapper;
import com.amigoscode.customer.repository.CustomerRepository;
import com.amigoscode.customer.request.customer.CustomerRegistrationRequest;
import com.amigoscode.customer.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public record CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) implements CustomerService {

    @Override
    public void registerCustomer(CustomerRegistrationRequest request) {
        customerRepository.save(customerMapper.carDtoToCustomer(request));
    }
}
