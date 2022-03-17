package com.dream.gatewayservice.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
	
	@GetMapping("/")
	public String index(Principal principal) {
		return principal.getName()+principal.toString();
	}
}
