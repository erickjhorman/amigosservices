package com.amigoscode.fraud.service;

public interface FraudCheckService {
    boolean isFraudulentCustomer(Integer customerId);
}
