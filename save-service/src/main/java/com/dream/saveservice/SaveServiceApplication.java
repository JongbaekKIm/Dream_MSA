package com.dream.saveservice;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dream.saveservice.dto.MessageVo;
import com.dream.saveservice.dto.SaveDto;
import com.dream.saveservice.service.SaveService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableEurekaClient
@SpringBootApplication
@AllArgsConstructor
@Controller
@Slf4j
public class SaveServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(SaveServiceApplication.class);
	private SaveService service;

	public static void main(String[] args) {
		SpringApplication.run(SaveServiceApplication.class, args);
	}

	@KafkaListener(topics = "${kafka.topic_name}", groupId = "${kafka.group_id}")
	public void listener(String message) {
		logger.info("Received message = {}", message);

		ObjectMapper mapper = new ObjectMapper();
		try {
			SaveDto dto = new SaveDto();
			MessageVo vo = mapper.readValue(message, MessageVo.class);
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			dto.setProNo(Integer.parseInt(vo.getProNo()));
			dto.setUserId(vo.getUserId());
			dto.setOrderPrice(Double.parseDouble(vo.getOrderPrice()));
			dto.setOrderDate(df.format(cal.getTime()));
			cal.add(Calendar.YEAR, Integer.parseInt(vo.getTerm()));
			dto.setEndDate(df.format(cal.getTime()));
//			dto.setTerm(vo.getTerm());
//			logger.info("dto 상품기간 : "+dto.getTerm());
			logger.info("dto 상품이름 : " + dto.getProNo());
			logger.info("dto 상품가격 : " + dto.getOrderPrice());
			logger.info("dto 이름 : " + dto.getUserId());

			service.save(dto);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// keycloak 세션 정보 받고 나서 본인 아이디 검색
	
	@GetMapping("/orderCheck")
	public String orderCheck(Principal principal, Model model) {
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		String userId=(String) (token).getTokenAttributes().get( "preferred_username");
		log.info("userId : "+userId);
		service.select(userId);
		model.addAttribute("list", service.select(userId));
		return "orderCheck";
	}
}
