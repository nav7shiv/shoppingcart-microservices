package com.navin.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.navin.user.model.User;
import com.navin.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	
	public List<User> getAllUsers() {
		return repository.findAll();
	}
	
	public Optional<User> getUserById(Long id) {
		return repository.findById(id);
	}
	
	public User createUser(User user) {
		return repository.save(user);
	}
	
	public Optional<User> updateUser(Long id, User user) {
		return repository.findById(id).map((existingUser) -> {
			existingUser.setName(user.getName());
			existingUser.setEmail(user.getEmail());
			existingUser.setPhone(user.getPhone());
			existingUser.setAddress(user.getAddress());
			return repository.save(existingUser);
		});
	}
	public void deleteUser(Long id) {
		if (repository.existsById(id))
			repository.deleteById(id);
	}
}
