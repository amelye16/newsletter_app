package com.newsletter.repository;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.newsletter.model.NewsletterUser;

public interface NewsletterUserRepository extends JpaRepository<NewsletterUser, Integer> {

	@Query("FROM NewsletterUser nu WHERE  nu.userEmail = ?1 AND newsletterId = ?2")
	Optional<NewsletterUser> findByEmailNewsletter(String email, int newsletterId);

	@Query("FROM NewsletterUser nu WHERE nu.userEmail = ?1")
	Optional<ArrayList<NewsletterUser>> findByEmail(String email);

	@Query("FROM NewsletterUser nu WHERE newsletterId = ?1 AND suscribed = 1")
	Optional<ArrayList<NewsletterUser>> findNewsletterActive(int newsletterId);

	@Query(value = "SELECT DISTINCT(nu.newsletterTypeId),nu.id_userEmail, nt.newsletterTypeName "
			+ "FROM newsletter.newslettersuser nu INNER JOIN newsletter.newslettertypes nt "
			+ "ON nt.newsletterTypeId = nu.newsletterTypeId WHERE nu.id_userEmail = ?1 "
			+ "AND suscribed = 1", nativeQuery = true)
	Optional<ArrayList> findNewsletterActiveByEmail(String email);

	@Transactional
	@Modifying
	@Query(value = "UPDATE newslettersuser SET  suscribed =0 WHERE newsletterTypeId =?1 and id_userEmail = ?2", nativeQuery = true)
	void updateSusscription(Integer newsletterTypes, String email);

}
