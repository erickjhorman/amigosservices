package com.amigoscode.fraud.controller;

import com.amigoscode.fraud.response.FraudCheckResponse;
import com.amigoscode.fraud.service.FraudCheckService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") @NonNull Integer customerId) {
        return new FraudCheckResponse(fraudCheckService.isFraudulentCustomer(customerId));
    }
}
