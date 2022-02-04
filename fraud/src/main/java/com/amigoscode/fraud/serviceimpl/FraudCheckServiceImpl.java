package com.amigoscode.fraud.serviceimpl;

import com.amigoscode.fraud.domain.FraudCheckHistory;
import com.amigoscode.fraud.repository.FraudCheckHistoryRepository;
import com.amigoscode.fraud.service.FraudCheckService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckServiceImpl(FraudCheckHistoryRepository fraudCheckHistoryRepository) implements FraudCheckService {

    @Override
    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
