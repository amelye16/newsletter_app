package com.newsletter.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.newsletter.model.NewsletterType;

public interface INewsletterTypeRepository extends JpaRepository<NewsletterType, Integer> {

	@Query(value = "SELECT newsletterTypeDesc,newsletterTypeName FROM newslettertypes nt WHERE nt.newsletterTypeId = ?1", nativeQuery = true)
	List<String> DataNewsletterTypes(Integer id);
}
