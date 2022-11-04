package com.newsletter.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.newsletter.model.NewsletterType;
import com.newsletter.repository.INewsletterTypeRepository;

@Service
public class NewsletterTypeService {

	@Autowired
	private INewsletterTypeRepository newsletterTypeRepo;

	public NewsletterType create(NewsletterType newsletterType) {
		return newsletterTypeRepo.save(newsletterType);
	}

	public NewsletterType update(NewsletterType newsletterType) {
		return newsletterTypeRepo.save(newsletterType);
	}

	public Optional<NewsletterType> findById(Integer newsletterTypeId) {
		return newsletterTypeRepo.findById(newsletterTypeId);
	}

	public List<NewsletterType> findAll() {
		return newsletterTypeRepo.findAll();
	}

}
