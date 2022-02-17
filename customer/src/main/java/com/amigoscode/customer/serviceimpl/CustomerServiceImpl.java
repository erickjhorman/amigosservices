package com.amigoscode.customer.serviceimpl;

import com.amigoscode.clients.fraud.response.FraudCheckResponse;
import com.amigoscode.clients.fraud.response.FraudClient;
import com.amigoscode.clients.notification.NotificationClient;
import com.amigoscode.clients.notification.NotificationRequest;
import com.amigoscode.customer.domain.Customer;
import com.amigoscode.customer.mapper.customer.CustomerMapper;
import com.amigoscode.customer.repository.CustomerRepository;
import com.amigoscode.customer.request.customer.CustomerRegistrationRequest;
import com.amigoscode.customer.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public record CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository,
                                  RestTemplate restTemplate, FraudClient fraudClient, NotificationClient notificationClient) implements CustomerService {

    @Override
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = customerMapper.carDtoToCustomer(request);
        customerRepository.saveAndFlush(customer);
        /*
        Optional<FraudCheckResponse> fraudCheckResponse = Optional.ofNullable(restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        ));
        */
        Optional<FraudCheckResponse> fraudCheckResponse = Optional.ofNullable(fraudClient.isFraudster(customer.getId()));
        if (fraudCheckResponse.isPresent() && fraudCheckResponse.get().isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        notificationClient.sendNotification(new NotificationRequest(
                                customer.getId(),
                customer.getEmail(), String.format("Hi %s, welcome to Amigoscode..",
                customer.getFirstName())
        ));
    }
}
