package com.newsletter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newsletter.service.StadisticsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/statistics")
public class StadisticsController {

	@Autowired
	private StadisticsService StadisticsService;

	@GetMapping()
	public ResponseEntity<List> findUserNewsletters() {
		return StadisticsService.getStadistics().map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
