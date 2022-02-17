package com.amigoscode.notification;

import com.amigoscode.clients.notification.NotificationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(imports = LocalDateTime.class)
public interface NotificationMapper {

    @Mapping(target = "toCustomerId", source = "toCustomerId")
    @Mapping(target = "toCustomerEmail", source = "toCustomerName")
    @Mapping(target = "sender", constant = "AmigosCode")
    @Mapping(target = "message",source = "message")
    @Mapping(target = "sentAt", dateFormat = "dd-MM-yyyy", expression = "java(LocalDateTime.now())")
    Notification notificationRequestToNotification(NotificationRequest request);
}
