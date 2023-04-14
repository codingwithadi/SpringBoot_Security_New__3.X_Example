package com.codingwithadi.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingwithadi.security.model.UserInfo;
import com.codingwithadi.security.service.UserService;

@RestController
@RequestMapping("/v1/api")
public class Controller {
	
	@Autowired
	private UserService service;
	
	//PreAutorize() is used for to tell springboot we use role based authorization
	
	@GetMapping("/home")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String home() {
		return "Welcome to home";
	}
	
	@GetMapping("/public")
	public String publicPosts() {
		return "Welcome to public Page";
	}
	
	@PostMapping("/new")
	public String addUser(@RequestBody UserInfo userInfo) {
		service.addNewUser(userInfo);
		return "User Added";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adminPage() {
		return "Welcome to Admin Page";
	}

}
