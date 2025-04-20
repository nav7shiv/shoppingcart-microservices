package com.navin.cart.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.navin.cart.model.Cart;
import com.navin.cart.model.CartItem;
import com.navin.cart.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	
	private final CartRepository cartRepository;
	
	public Cart addItemToCart(String userId, Long productId, int quantity) {
		Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
			Cart newCart = new Cart();
			newCart.setUserId(userId);
			return cartRepository.save(newCart);
		});
		
		Optional<CartItem> existingItem = cart.getItems().stream()
				.filter(item -> item.getProductId().equals(productId)).findFirst();
		
		if (existingItem.isPresent()) {
			CartItem cartItem = existingItem.get();
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
		} else {
			CartItem item = new CartItem();
			item.setProductId(productId);
			item.setQuantity(quantity);
			item.setCart(cart);
			cart.getItems().add(item);
		}
		
		return cartRepository.save(cart);
	}
	
	public Cart getCartByUser(String userId) {
		return cartRepository.findByUserId(userId).orElse(null);
	}
	
	public Cart updateItemQuantity(String userId, Long productId, int quantity) {
		Cart cart = getCartByUser(userId);
		if (cart == null)
			return cart;
		for (CartItem item : cart.getItems()) {
			if (item.getProductId().equals(productId)) {
				item.setQuantity(quantity);
				break;
			}
		}
		return cartRepository.save(cart);
	}

	
	public Cart deleteItemFromCart(String userId, Long productId) {
		Cart cart = getCartByUser(userId);
		if (cart == null)
			return cart;
		cart.getItems().removeIf(item -> item.getProductId().equals(productId));
		return cartRepository.save(cart);
	}
	
	public void clearCart(String userId) {
		Cart cart = getCartByUser(userId);
		if (cart != null) {
			cart.getItems().clear();
			cartRepository.save(cart);
		}
	}
}
