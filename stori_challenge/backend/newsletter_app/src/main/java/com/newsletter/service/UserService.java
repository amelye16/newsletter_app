package com.newsletter.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.List;
import com.newsletter.model.User;
import com.newsletter.repository.IUserRepository;

@Service
public class UserService {

	@Autowired
	private IUserRepository userRepository;

	public User create(User user) {
		return userRepository.save(user);
	}

	public List<User> createUsers(List<User> users) {
		return userRepository.saveAll(users);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public String getName(String email) {
		return userRepository.getName(email);
	}

}
