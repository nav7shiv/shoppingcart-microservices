package com.navin.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.navin.cart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
