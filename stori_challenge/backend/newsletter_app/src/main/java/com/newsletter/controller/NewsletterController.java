package com.newsletter.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.newsletter.model.Newsletter;
import com.newsletter.service.NewsletterService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/newsletter")
public class NewsletterController {

	@Autowired
	private NewsletterService newsletterService;

	@PostMapping
	public ResponseEntity<Newsletter> uploadNewsletter(@RequestParam String newsletterName,
			@RequestParam Integer newsletterType, @RequestParam String newsletterDescription,
			@RequestParam("file") MultipartFile file) throws Exception {

		LocalDateTime localDate = LocalDateTime.now();

		Newsletter response = newsletterService.save(new Newsletter(newsletterType, newsletterName,
				newsletterDescription, file.getOriginalFilename(), localDate, null, 0));
		newsletterService.saveFile(file);

		return new ResponseEntity<>(newsletterService.save(response), HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<List<Newsletter>> getAllNewsletter() {
		return ResponseEntity.ok(newsletterService.findAll());
	}
}
