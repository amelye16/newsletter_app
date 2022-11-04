package com.newsletter.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.newsletter.repository.StadisticsRepository;

@Service
public class StadisticsService {

	@Autowired
	private StadisticsRepository stadisticsRepository;

	public Optional<List> getStadistics() {

		return stadisticsRepository.getStadistics();
	}
}
