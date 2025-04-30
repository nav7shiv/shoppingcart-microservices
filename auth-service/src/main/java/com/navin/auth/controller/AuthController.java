package com.navin.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.navin.auth.dto.AuthRequest;
import com.navin.auth.dto.AuthResponse;
import com.navin.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		String token = authService.authenticate(request);
		if (token != null) {
			return ResponseEntity.ok(new AuthResponse(token));
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

}
