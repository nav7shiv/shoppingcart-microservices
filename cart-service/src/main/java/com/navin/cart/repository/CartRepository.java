package com.navin.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.navin.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Optional<Cart> findByUserId(String userId);

}
