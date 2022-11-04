package com.newsletter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.newsletter.model.NewsletterUser;
import com.newsletter.repository.NewsletterUserRepository;

@Service
public class NewsletterUserService {

	@Autowired
	private NewsletterUserRepository newsletterUserRepo;

	public NewsletterUser create(NewsletterUser newsletterUser) {
		return newsletterUserRepo.save(newsletterUser);
	}

	public List<NewsletterUser> createNewsletterUsers(List<NewsletterUser> newsletterUsers) {
		return newsletterUserRepo.saveAll(newsletterUsers);
	}

	public NewsletterUser update(NewsletterUser newsletterUser) {
		return newsletterUserRepo.save(newsletterUser);
	}

	public Optional<NewsletterUser> findByEmailNewsletter(String userEmail, int newsletterId) {
		return newsletterUserRepo.findByEmailNewsletter(userEmail, newsletterId);
	}

	public Optional<ArrayList<NewsletterUser>> findNewsletterActive(int newsletterId) {

		return newsletterUserRepo.findNewsletterActive(newsletterId);
	}

	public Optional<ArrayList<NewsletterUser>> findByEmail(String email) {
		return newsletterUserRepo.findByEmail(email);
	}

	public Optional<ArrayList> findNewsletterActiveByEmail(String email) {
		return newsletterUserRepo.findNewsletterActiveByEmail(email);
	}

	public void updateSusscription(Integer newsletterType, String email) {
		newsletterUserRepo.updateSusscription(newsletterType, email);
	}

}
