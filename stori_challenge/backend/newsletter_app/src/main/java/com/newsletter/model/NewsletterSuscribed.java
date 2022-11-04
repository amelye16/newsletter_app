package com.newsletter.model;

public class NewsletterSuscribed {

	private Integer newsletterTypeId;
	private String id_userEmail;
	private String newsletterTypeName;

	public NewsletterSuscribed() {
		super();

	}

	public NewsletterSuscribed(Integer newsletterTypeId, String id_userEmail, String newsletterTypeName) {
		super();
		this.newsletterTypeId = newsletterTypeId;
		this.id_userEmail = id_userEmail;
		this.newsletterTypeName = newsletterTypeName;
	}

	public Integer getNewsletterTypeId() {
		return newsletterTypeId;
	}

	public void setNewsletterTypeId(Integer newsletterTypeId) {
		this.newsletterTypeId = newsletterTypeId;
	}

	public String getId_userEmail() {
		return id_userEmail;
	}

	public void setId_userEmail(String id_userEmail) {
		this.id_userEmail = id_userEmail;
	}

	public String getNewsletterTypeName() {
		return newsletterTypeName;
	}

	public void setNewsletterTypeName(String newsletterTypeName) {
		this.newsletterTypeName = newsletterTypeName;
	}
}
