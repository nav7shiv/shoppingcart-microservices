package com.navin.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.navin.apigateway.util.JwtUtil;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

	private final JwtUtil jwtUtil;

	public AuthenticationFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();

		// Skip auth endpoints
		if (request.getURI().getPath().contains("/auth/login")) {
			return chain.filter(exchange);
		}

		// Check for Authorization header
		if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
			return onError(exchange, "Missing Authorization header", HttpStatus.UNAUTHORIZED);
		}

		// Extract and validate token
		String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return onError(exchange, "Invalid Authorization header", HttpStatus.UNAUTHORIZED);
		}

		String token = authHeader.substring(7);
		try {
			jwtUtil.validateToken(token);
		} catch (Exception e) {
			return onError(exchange, "JWT validation failed: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		// Optionally forward claims (e.g., username, roles)
		Claims claims = jwtUtil.getClaims(token);
		ServerHttpRequest mutatedRequest = exchange.getRequest().mutate().header("X-User-Id", claims.getSubject())
				.header("X-Roles", claims.get("roles", String.class)) // assuming roles are a string
				.build();

		return chain.filter(exchange.mutate().request(mutatedRequest).build());
	}

	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status) {
		exchange.getResponse().setStatusCode(status);
		return exchange.getResponse().setComplete();
	}

	@Override
	public int getOrder() {
		return -1;
	}
}
