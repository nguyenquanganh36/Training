package com.anhnq.storedemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class HelloWorldController {

	@GetMapping("/hello")
//	@PreAuthorize("hasRole('ROLE_ROLE_ADMIN')")
	public String hello() {
		return "Hello World";
	}

}
