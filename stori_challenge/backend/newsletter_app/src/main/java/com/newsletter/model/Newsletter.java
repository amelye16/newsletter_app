package com.newsletter.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.newsletter.utils.MessageHandler;

@Entity
@Table(name = "newsletters")
public class Newsletter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer newsletterId;

	@NotNull(message = MessageHandler.NEWSLETTER_TYPE)
	private Integer newsletterTypeId;

	@NotNull(message = MessageHandler.NEWSLETTER_NAME)
	@NotBlank(message = MessageHandler.NEWSLETTER_NAME)
	@Size(min = 4, max = 160, message = MessageHandler.NEWSLETTER_LENGTH)
	@Column(nullable = false, length = 160)
	private String newsletterName;

	@NotNull(message = MessageHandler.NEWSLETTER_DESC)
	@NotBlank(message = MessageHandler.NEWSLETTER_DESC)
	@Size(min = 10, max = 250, message = MessageHandler.NEWSLETTER_DESC_LENGTH)
	@Column(nullable = false, length = 250)
	private String newsletterDescription;

	private String newsletterAttach;

	private LocalDateTime createdDate;
	private LocalDateTime sentDate;
	private Integer sentEstatus;

	public Newsletter() {
		super();
	}

	public Newsletter(Integer newsletterTypeId, String newsletterName, String newsletterDescription,
			String newsletterAttach, LocalDateTime createdDate, LocalDateTime sentDate, Integer sentEstatus) {
		super();
		this.newsletterTypeId = newsletterTypeId;
		this.newsletterName = newsletterName;
		this.newsletterDescription = newsletterDescription;
		this.newsletterAttach = newsletterAttach;
		this.createdDate = createdDate;
		this.sentDate = sentDate;
		this.sentEstatus = sentEstatus;
	}

	public Integer getNewsletterId() {
		return newsletterId;
	}

	public void setNewsletterId(Integer newsletterId) {
		this.newsletterId = newsletterId;
	}

	public Integer getNewsletterTypeId() {
		return newsletterTypeId;
	}

	public void setNewsletterTypeId(Integer newsletterTypeId) {
		this.newsletterTypeId = newsletterTypeId;
	}

	public String getNewsletterName() {
		return newsletterName;
	}

	public void setNewsletterName(String newsletterName) {
		this.newsletterName = newsletterName;
	}

	public String getNewsletterDescription() {
		return newsletterDescription;
	}

	public void setNewsletterDescription(String newsletterDescription) {
		this.newsletterDescription = newsletterDescription;
	}

	public String getNewsletterAttach() {
		return newsletterAttach;
	}

	public void setNewsletterAttach(String newsletterAttach) {
		this.newsletterAttach = newsletterAttach;
	}

	public LocalDateTime getCreationDate() {
		return createdDate;
	}

	public void setCreationDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getSentDate() {
		return sentDate;
	}

	public void setSentDate(LocalDateTime sentDate) {
		this.sentDate = sentDate;
	}

	public Integer getSentEstatus() {
		return sentEstatus;
	}

	public void setSentEstatus(Integer sentEstatus) {
		this.sentEstatus = sentEstatus;
	}
}
