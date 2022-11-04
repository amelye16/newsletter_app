package com.newsletter.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.newsletter.utils.MessageHandler;

@Entity
@Table(name = "users")
public class User {

	@Id
	@NotNull(message = MessageHandler.USER_EMAIL)
	@NotBlank(message = MessageHandler.USER_EMAIL)
	@Email(message = MessageHandler.USER_EMAIL_VALID)
	@Size(min = 6, max = 160, message = MessageHandler.USER_EMAIL_LENGTH)
	@Column(nullable = false, length = 160)
	private String email;

	@NotNull(message = MessageHandler.USER_NAME)
	@NotBlank(message = MessageHandler.USER_NAME)
	@Size(min = 4, max = 160, message = MessageHandler.USER_NAME_LENGTH)
	@Column(nullable = false, length = 150)
	private String name;

	public User() {
		super();
	}

	public User(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
