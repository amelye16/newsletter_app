package com.newsletter.model;

import java.io.Serializable;

public class NewsletterUserPK implements Serializable {
	private Integer newsletterId;
	private String userEmail;

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
