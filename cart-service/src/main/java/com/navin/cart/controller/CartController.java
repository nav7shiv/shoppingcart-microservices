package com.navin.cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.navin.cart.model.Cart;
import com.navin.cart.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
	
	private final CartService cartService;
	
	@PostMapping("/{userId}/add")
	public ResponseEntity<Cart> addItemToCart(@PathVariable String userId, 
			@RequestParam Long productId, @RequestParam int quantity) {
		Cart cart = cartService.addItemToCart(userId, productId, quantity);
		if (cart == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(cart);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Cart> getCartByUser(@PathVariable String userId) {
		Cart cart = cartService.getCartByUser(userId);
		if (cart == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(cart);
	}
	
	@PutMapping("/{userId}/update")
	public ResponseEntity<Cart> updateItemQuantity(@PathVariable String userId, 
			@RequestParam Long productId, @RequestParam int quantity) {
		Cart cart = cartService.updateItemQuantity(userId, productId, quantity);
		if (cart == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(cart);
	}
	
	@DeleteMapping("/{userId}/delete")
	public ResponseEntity<Cart> deleteItemFromCart(@PathVariable String userId, 
			@RequestParam Long productId) {
		Cart cart = cartService.deleteItemFromCart(userId, productId);
		if (cart == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(cart);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> clearCart(@PathVariable String userId) {
		cartService.clearCart(userId);
		return ResponseEntity.ok().build();
	}

}
