package com.newsletter.repository;

import com.newsletter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, String> {

	// JBQL
	@Query("FROM User u WHERE u.email = ?1")
	Optional<User> findByEmail(String email);

	@Query(value = "SELECT name FROM users WHERE email = ?1", nativeQuery = true)
	String getName(String email);
}
