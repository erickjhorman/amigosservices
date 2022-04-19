package com.amigoscode.notification;

import com.amigoscode.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

   public void send(NotificationRequest notificationRequest){
        notificationRepository.save(notificationMapper.notificationRequestToNotification(notificationRequest));
    }
}
