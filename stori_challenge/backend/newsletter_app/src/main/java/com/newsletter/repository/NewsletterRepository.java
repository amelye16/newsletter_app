package com.newsletter.repository;

import java.time.LocalDateTime;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.newsletter.model.Newsletter;

public interface NewsletterRepository extends JpaRepository <Newsletter, Integer> {
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "UPDATE newsletters SET  sentDate =?1, sentEstatus = 1 WHERE newsletterId = ?2", nativeQuery = true)
	void updateFields (LocalDateTime dateTime, Integer newsletterId);
	
	@Query("FROM Newsletter nu WHERE sentEstatus = 0")
	List<Newsletter> findAllPending ();

}
