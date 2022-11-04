package com.newsletter.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.newsletter.utils.MessageHandler;

@Entity
@Table(name = "newslettertypes")
public class NewsletterType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer newsletterTypeId;

	@NotNull(message = MessageHandler.NEWSLETTER_TYPE)
	@NotBlank(message = MessageHandler.NEWSLETTER_TYPE)
	@Size(min = 4, max = 150, message = MessageHandler.NEWSLETTER_TYPE_NAME_LENGTH)
	@Column(nullable = false, length = 150)
	private String newsletterTypeName;

	@NotNull(message = MessageHandler.NEWSLETTER_TYPE_DESC)
	@NotBlank(message = MessageHandler.NEWSLETTER_TYPE_DESC)
	@Size(min = 8, max = 250, message = MessageHandler.NEWSLETTER_TYPE_DESC_LENGTH)
	@Column(nullable = false, length = 150)
	private String newsletterTypeDesc;

	public NewsletterType() {
		super();
	}

	public NewsletterType(String newsletterTypeName, String newsletterTypeDesc) {
		super();
		this.newsletterTypeName = newsletterTypeName;
		this.newsletterTypeDesc = newsletterTypeDesc;
	}

	public Integer getNewsletterTypeId() {
		return newsletterTypeId;
	}

	public void setNewsletterTypeId(Integer newsletterTypeId) {
		this.newsletterTypeId = newsletterTypeId;
	}

	public String getNewsletterTypeName() {
		return newsletterTypeName;
	}

	public void setNewsletterTypeName(String newsletterTypeName) {
		this.newsletterTypeName = newsletterTypeName;
	}

	public String getNewsletterTypeDesc() {
		return newsletterTypeDesc;
	}

	public void setNewsletterTypeDesc(String newsletterTypeDesc) {
		this.newsletterTypeDesc = newsletterTypeDesc;
	}
}
