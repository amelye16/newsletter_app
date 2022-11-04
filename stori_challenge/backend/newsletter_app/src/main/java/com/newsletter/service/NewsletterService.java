package com.newsletter.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.newsletter.model.Newsletter;
import com.newsletter.repository.NewsletterRepository;

@Service
public class NewsletterService {

	@Autowired
	private NewsletterRepository newsletterRepo;
	private final Path rootFolder = Paths.get("uploads");

	public void saveFile(MultipartFile file) throws Exception {
		Files.copy(file.getInputStream(), this.rootFolder.toAbsolutePath().resolve(file.getOriginalFilename()));
	}

	public void deleteFile(String name) throws Exception {
		Path file = rootFolder.toAbsolutePath().resolve(name);
		Files.deleteIfExists(file);
	}

	public String newsletterFile(String name) {
		Path file = rootFolder.toAbsolutePath().resolve(name);
		return file.toString();
	}

	public Newsletter save(Newsletter newsletter) {
		return newsletterRepo.save(newsletter);
	}

	public Newsletter update(Newsletter newsletter) {
		return newsletterRepo.save(newsletter);
	}

	public Optional<Newsletter> updateFields(LocalDateTime dateTime, Integer newsletterId) {
		newsletterRepo.updateFields(dateTime, newsletterId);
		return newsletterRepo.findById(newsletterId);
	}

	public Optional<Newsletter> findById(Integer id) {
		return newsletterRepo.findById(id);
	}

	public List<Newsletter> findAll() {
		return newsletterRepo.findAll();
	}

	public List<Newsletter> findAllPending() {
		return newsletterRepo.findAllPending();
	}
}
