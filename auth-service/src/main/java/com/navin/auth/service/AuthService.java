package com.navin.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navin.auth.dto.AuthRequest;
import com.navin.auth.util.JwtUtil;

@Service
public class AuthService {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public String authenticate(AuthRequest request) {
		if ("admin".equals(request.getUsername()) && "password".equals(request.getPassword())) {
			return jwtUtil.generateToken(request.getUsername());
		}
		return null;
	}

}
