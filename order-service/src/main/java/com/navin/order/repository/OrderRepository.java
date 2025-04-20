package com.navin.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.navin.order.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

	List<Orders> findByUserId(String userId);
}
