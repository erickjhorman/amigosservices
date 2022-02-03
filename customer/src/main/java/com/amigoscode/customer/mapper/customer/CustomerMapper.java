package com.amigoscode.customer.mapper.customer;

import com.amigoscode.customer.domain.Customer;
import com.amigoscode.customer.request.customer.CustomerRegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer carDtoToCustomer(CustomerRegistrationRequest request);
}
