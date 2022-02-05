package com.amigoscode.customer.serviceimpl;

import com.amigoscode.customer.domain.Customer;
import com.amigoscode.customer.mapper.customer.CustomerMapper;
import com.amigoscode.customer.repository.CustomerRepository;
import com.amigoscode.customer.request.customer.CustomerRegistrationRequest;
import com.amigoscode.customer.response.fraud.FraudCheckResponse;
import com.amigoscode.customer.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public record CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository,
                                  RestTemplate restTemplate) implements CustomerService {

    @Override
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = customerMapper.carDtoToCustomer(request);
        customerRepository.saveAndFlush(customer);

        Optional<FraudCheckResponse> fraudCheckResponse = Optional.ofNullable(restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        ));

        if (fraudCheckResponse.isPresent() && fraudCheckResponse.get().isFraudster()){
            throw new IllegalStateException("fraudster");
        }
    }
}
