package com.dream.loanService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableDiscoveryClient
@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class LoanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanServiceApplication.class, args);
	}

	//product-service에서 전송받은 내용
	@KafkaListener(topics = "${kafka.topic_name}", groupId = "${kafka.group_id}")
	public void listener(String message) {
		log.info("Received message = {}", message);
	}
}
