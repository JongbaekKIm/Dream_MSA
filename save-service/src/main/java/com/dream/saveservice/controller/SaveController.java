package com.dream.saveservice.controller;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dream.saveservice.service.SaveService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@AllArgsConstructor
public class SaveController {
	private SaveService service;

	// keycloak 세션 정보 받고 나서 본인 아이디 검색
	@RolesAllowed({ "USER" })
	@GetMapping("/check")
	public String orderCheck(Principal principal, Model model) {
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		String userId = (String) (token).getTokenAttributes().get("preferred_username");
		log.info("userId : " + userId);
		service.select(userId);
		log.info("서비스 실행함?");
		model.addAttribute("list", service.select(userId));
		log.info("모델에 담김?");
		log.info(service.select(userId).toString());
		return "orderCheck";
	}
}
