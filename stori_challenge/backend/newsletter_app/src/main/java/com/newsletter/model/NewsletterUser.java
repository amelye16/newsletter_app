package com.newsletter.model;

import javax.persistence.*;

@Entity
@IdClass(value = NewsletterUserPK.class)
@Table(name = "newslettersuser")
public class NewsletterUser {

	@Id
	private Integer newsletterId;

	@Id
	@Column(nullable = false, length = 160)
	private String userEmail;

	private Integer newsletterTypeId;

	private Integer suscribed;

	public Integer getNewsletterTypeId() {
		return newsletterTypeId;
	}

	public void setNewsletterTypeId(Integer newsletterTypeId) {
		this.newsletterTypeId = newsletterTypeId;
	}

	public NewsletterUser() {
		super();
	}

	public NewsletterUser(String userEmail, Integer newsletterId, Integer suscribed, Integer newsletterTypeId) {
		super();
		this.userEmail = userEmail;
		this.newsletterId = newsletterId;
		this.suscribed = suscribed;
		this.newsletterTypeId = newsletterTypeId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getNewsletterId() {
		return newsletterId;
	}

	public void setNewsletterId(Integer newsletterId) {
		this.newsletterId = newsletterId;
	}

	public Integer getSuscribed() {
		return suscribed;
	}

	public void setSuscribed(Integer suscribed) {
		this.suscribed = suscribed;
	}
}
