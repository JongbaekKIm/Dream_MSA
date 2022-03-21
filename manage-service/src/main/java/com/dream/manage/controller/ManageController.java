package com.dream.manage.controller;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dream.manage.dto.ManageDto;
import com.dream.manage.service.ManageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ManageController {
	private ManageService service;

	@RolesAllowed({ "ADMIN" })
	@GetMapping("/register")
	public String register(Principal principal, Model model) {
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		log.info("toString : " + token.getTokenAttributes().toString());
		model.addAttribute("list", token.getTokenAttributes());
		return "register";
	}

	@RolesAllowed({ "ADMIN" })
	@PostMapping("/register")
	public String register(@ModelAttribute("dto") ManageDto dto) {
		log.info("컨트롤러 시작" + dto.getDescription());
		service.register(dto);
		log.info("컨트롤러 끝");
		return "/regist_success";
	}

	@PostMapping("/modify")
	public String modify() {
		return "asd";
	}

	@GetMapping("/delete")
	public String delete() {
		return "asd";
	}

//	@RequestMapping("/")
//	public String main(Authentication authentication) {
//
//		if (authentication != null) {
//			System.out.println("타입정보 : " + authentication.getClass());
//			
//			// 세션 정보 객체 반환
//			WebAuthenticationDetails web = (WebAuthenticationDetails)authentication.getDetails();
//			System.out.println("세션ID : " + web.getSessionId());
//			System.out.println("접속IP : " + web.getRemoteAddress());
//
//			// UsernamePasswordAuthenticationToken에 넣었던 UserDetails 객체 반환
//			UserDetails userVO = (UserDetails) authentication.getPrincipal();
//			System.out.println("ID정보 : " + userVO.getUsername());
//			return userVO.getUsername();
//		}
//		
//		return "fail";
//	}
}
