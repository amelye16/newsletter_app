package com.newsletter.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.newsletter.model.Newsletter;

public interface StadisticsRepository extends JpaRepository<Newsletter, Integer> {

	@Query(value = "SELECT CONCAT(\'1. Total of registered users: \',count(distinct (email))) AS sumary FROM users "
			+ "UNION "
			+ "SELECT CONCAT(\'2. Total of registered newsletters: \',count(distinct (newsletterId))) AS sumary FROM newsletter.newsletters "
			+ "UNION "
			+ "SELECT CONCAT(\'3. Total of newsletters sent: \',count(distinct (newsletterId))) AS sumary FROM newsletters where sentEstatus = \'1\' "
			+ "UNION "
			+ "SELECT CONCAT(\'4. Total of newsletters wait: \',count(distinct (newsletterId))) AS sumary FROM newsletters where sentEstatus = \'0\' "
			+ "UNION "
			+ "SELECT CONCAT(\'5. Total of total subscribed users: \',count(distinct (id_userEmail))) AS sumary FROM newslettersuser where suscribed = \'1\' "
			+ "UNION "
			+ "SELECT CONCAT('6. Total of total unsubscribed users: \',count(distinct (id_userEmail))) AS sumary FROM newslettersuser where suscribed = \'0\' ", nativeQuery = true)
	Optional<List> getStadistics();

}
