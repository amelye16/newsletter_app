package com.newsletter.exception;

import java.util.Date;
import com.newsletter.model.Newsletter;

public class ResponseDetails {
	private Date timestamp;
	private String message;
	private String details;
	private Newsletter objectInsert;

	public ResponseDetails() {
		super();
	}

	public ResponseDetails(Date timestamp, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ResponseDetails(Date timestamp, String message, String details, Newsletter objectInsert) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.objectInsert = objectInsert;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Newsletter getObjectInsert() {
		return objectInsert;
	}

	public void setObjectInsert(Newsletter objectInsert) {
		this.objectInsert = objectInsert;
	}
}
