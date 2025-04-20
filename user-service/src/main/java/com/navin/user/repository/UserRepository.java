package com.navin.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.navin.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
