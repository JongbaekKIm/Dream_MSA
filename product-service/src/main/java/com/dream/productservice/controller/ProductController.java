package com.dream.productservice.controller;

import java.util.List;

<<<<<<< HEAD
import javax.annotation.security.RolesAllowed;

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
>>>>>>> ec6a23e06640487a52c2dfa0c7a6dce6f633945e
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.productservice.dto.Message;
import com.dream.productservice.dto.ProductDto;
import com.dream.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
<<<<<<< HEAD
	
	@RolesAllowed({ "USER" })
=======

>>>>>>> ec6a23e06640487a52c2dfa0c7a6dce6f633945e
	@GetMapping("/dream")
	public String dream(Model model, int proNo) throws Exception {
		List<ProductDto> product = productService.getProductList();
//		Message kafkaMessage = productService.selectOne(proNo);
		model.addAttribute("dream", product);
//		model.addAttribute("kafka", kafkaMessage);
		return "productList";
	}
	
	

	// kafka producer
	@Autowired
	private final KafkaTemplate<String, Message> kafkaTemplate;

	@Value(value = "${kafka.topic_name}")
	private String kafkaTopicName;

	String status = "";

	@RequestMapping(value="/kafka", method = RequestMethod.POST)
	public ResponseEntity<String> sendMessage(@RequestBody Message message) {
		log.info("proNo 전송된다~~ {}", message);

		ListenableFuture<SendResult<String, Message>> future = this.kafkaTemplate.send(kafkaTopicName, message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
			@Override
			public void onSuccess(SendResult<String, Message> result) {
				status = "Message send successfully, 메시지가 성공적으로 전송 됨.";
				log.info("메시지가 성공적으로 전송됨. successfully sent message = {}, with offset = {}", message,
						result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.info("Failed to send message = {}, error = {}", message, ex.getMessage());
				status = "Message sending failed = 메시지 전송 실패...";
			}
		});

		return ResponseEntity.ok(status);
	}
}
