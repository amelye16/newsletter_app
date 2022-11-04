package com.newsletter.controller;

import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newsletter.service.NewsletterUserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/newsletterUser")
public class NewsletterUserController {

	@Autowired
	private NewsletterUserService newsletterUserService;

	@GetMapping("/{email}")
	public ResponseEntity<ArrayList> findUserNewsletters(@Valid @PathVariable("email") String email) {
		return newsletterUserService.findNewsletterActiveByEmail(email).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping
	public void updateSusscription(@Valid @RequestParam String email, @RequestParam String unsuscribeList) {

		List<Integer> array = Stream.of(unsuscribeList.replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
				.collect(toList());

		for (Integer newsletterType : array) {			
			newsletterUserService.updateSusscription(newsletterType, email);
		}
	}

}
