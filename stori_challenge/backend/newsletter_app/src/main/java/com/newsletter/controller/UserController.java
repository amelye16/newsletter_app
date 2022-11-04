
package com.newsletter.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.newsletter.model.Newsletter;
import com.newsletter.model.NewsletterUser;
import com.newsletter.model.User;
import com.newsletter.service.NewsletterService;
import com.newsletter.service.NewsletterUserService;
import com.newsletter.service.SendMailService;
import com.newsletter.service.UserService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@CrossOrigin(origins = "*")
@RestController

public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private NewsletterUserService newsletterUserService;
	@Autowired
	private SendMailService sendMailService;
	@Autowired
	private NewsletterService newsletterService;

	@PostMapping(value = "/users/uploadFile")
	public ResponseEntity<Newsletter> createUserList(@RequestParam("configEmail") String userDetails,
			@RequestParam("newsletterDetails") String newsletterDetails, @RequestPart("file") MultipartFile file)
			throws Exception {

		JSONArray jsonArray = new JSONArray("[" + userDetails + "]");
		JSONObject jsonObject = jsonArray.getJSONObject(0);

		int newsletterId = jsonObject.getInt("newsletterId");

		InputStream inputFile = file.getInputStream();
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
		List<Record> allRecords = parser.parseAllRecords(inputFile);

		for (Record record : allRecords) {
			insertUser(new User(record.getString("Email"), record.getString("Name")));
			insertNewsletterUser(record.getString("Email"), newsletterId);
		}

		sendMailService.sendEmailHandler(newsletterDetails);
		Newsletter newsletterEdited = newsletterService.findById(newsletterId).get();
		
		return new ResponseEntity<Newsletter>(newsletterEdited, HttpStatus.CREATED);
	}

	@PostMapping(value = "/users/user")
	public ResponseEntity<Newsletter> createSimpleUser(@RequestParam("configEmail") String userDetails,
			@RequestParam("newsletterDetails") String newsletterDetails) throws Exception {

		JSONArray jsonArray = new JSONArray("[" + userDetails + "]");
		JSONObject jsonObject = jsonArray.getJSONObject(0);

		int newsletterId = jsonObject.getInt("newsletterId");

		String name = jsonObject.getString("name");
		String email = jsonObject.getString("email");

		User user = new User();
		user.setName(name);
		user.setEmail(email);

		insertUser(user);

		insertNewsletterUser(email, newsletterId);

		sendMailService.sendEmailHandler(newsletterDetails);

		Newsletter newsletterEdited = newsletterService.findById(newsletterId).get();

		return new ResponseEntity<Newsletter>(newsletterEdited, HttpStatus.CREATED);
	}

	@GetMapping("/users/{email}")
	public ResponseEntity<User> getUser(@Valid @PathVariable("email") String email) {
		return userService.findByEmail(email).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	public ResponseEntity<User> insertUser(User user) {
		return userService.findByEmail(user.getEmail()).map(ResponseEntity::ok)
				.orElseGet(() -> new ResponseEntity<>(userService.create(user), HttpStatus.CREATED));

	}

	public ResponseEntity<NewsletterUser> insertNewsletterUser(@Valid String email, int newsletterId) {

		Optional<Newsletter> newsletter = newsletterService.findById(newsletterId);

		return newsletterUserService.findByEmailNewsletter(email, newsletterId).map(ResponseEntity::ok)
				.orElseGet(() -> new ResponseEntity<>(
						newsletterUserService.create(
								new NewsletterUser(email, newsletterId, 1, newsletter.get().getNewsletterTypeId())),
						HttpStatus.CREATED));

	}

}
