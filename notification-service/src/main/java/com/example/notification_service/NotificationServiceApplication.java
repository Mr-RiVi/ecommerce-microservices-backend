package com.example.notification_service;

import com.example.notification_service.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@KafkaListener(topics = "notification-topic")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent){
		// Logic for send out a notification through email or something
		log.info("Received Notification for Order - {}", orderPlacedEvent.getOrderNumber());
	}
}
