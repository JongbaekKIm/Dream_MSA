package com.dream.menuservice.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
	@GetMapping("/menu")
	public String menu() {
		
		return "menu";
	}
}