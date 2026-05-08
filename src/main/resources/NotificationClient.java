package com.lms.course.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "notification-service", url = "${notification.service.url:http://localhost:8084}")
public interface NotificationClient {

    @PostMapping("/api/notifications")
    void sendNotification(@RequestBody Map<String, Object> payload);
}