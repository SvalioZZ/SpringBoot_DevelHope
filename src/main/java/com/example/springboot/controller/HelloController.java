package com.example.springboot.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping(value = "/good-morning")
	public ResponseEntity<String> index() {
		return ResponseEntity.ok("Good morning!");
	}
	
	@GetMapping(value = "/greeting")
	public ResponseEntity<String> greeting() {
		return new ResponseEntity<>("Good Afternoon!", HttpStatusCode.valueOf(200));
	}
	
	
}
