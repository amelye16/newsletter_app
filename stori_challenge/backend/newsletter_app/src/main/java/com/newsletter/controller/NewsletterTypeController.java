package com.newsletter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newsletter.model.NewsletterType;
import com.newsletter.service.NewsletterTypeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/newsletterType")
public class NewsletterTypeController {

	@Autowired
	private NewsletterTypeService newsletterTypeService;

	@PostMapping
	public ResponseEntity<NewsletterType> create(@Valid @RequestBody NewsletterType newsletterType) {
		return new ResponseEntity<>(newsletterTypeService.create(newsletterType), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<NewsletterType> update(@Valid @RequestBody NewsletterType newsletterType) {
		return newsletterTypeService.findById(newsletterType.getNewsletterTypeId())
				.map(n -> ResponseEntity.ok(newsletterTypeService.update(newsletterType)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<List<NewsletterType>> getAllNewsletterType() {
		return ResponseEntity.ok(newsletterTypeService.findAll());
	}
}
