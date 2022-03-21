package com.dream.listservice.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dream.listservice.service.ListService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Controller
@AllArgsConstructor
@Slf4j
public class ListController {
	private ListService service;
	
	@RolesAllowed({ "ADMIN" })
	@GetMapping("/all")
	public String selectAll(Model model) {
		model.addAttribute("list", service.selectAll());
		//@RequestHeader("first-request") String header ,
		//System.out.print("test test : "+header);
		return "list";
	}
}
