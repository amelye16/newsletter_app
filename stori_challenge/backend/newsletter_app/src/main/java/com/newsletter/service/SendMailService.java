package com.newsletter.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import com.newsletter.exception.ResponseDetails;
import com.newsletter.model.Newsletter;
import com.newsletter.model.NewsletterType;
import com.newsletter.model.NewsletterUser;
import com.newsletter.utils.MessageHandler;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Configuration configuration;

	@Autowired
	private NewsletterUserService newsletterUserService;

	@Autowired
	private NewsletterService newsletterService;
	@Autowired
	private UserService UserService;
	@Autowired
	private NewsletterTypeService newsletterTypeService;

	public void sendEmail(String toEmail, String subject, Map<String, Object> model, String newsletterAttach)
			throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException,
			IOException, TemplateException {

		MimeMessage message = javaMailSender.createMimeMessage();
		message.addHeaderLine("Content-Type: text/html; charset=UTF-8");
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
		Template template = configuration.getTemplate("email-template.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

		helper.setTo(toEmail);
		helper.setSubject(subject);
		helper.addAttachment(newsletterAttach, new File(newsletterService.newsletterFile(newsletterAttach)));
		helper.setText(html, true);

		// send email
		javaMailSender.send(message);

	}

	public ResponseEntity<ResponseDetails> sendEmailHandler(String newsletterDetails)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, MessagingException,
			IOException, TemplateException, JSONException, java.text.ParseException {

		JSONArray jsonArray = new JSONArray("[" + newsletterDetails + "]");

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		int newsletterId = jsonObject.getInt("newsletterId");

		String newsletterAttach = jsonObject.getString("newsletterAttach");
		String newsletterName = jsonObject.getString("newsletterName");
		String newsletterDescription = jsonObject.getString("newsletterDescription");
		int newsletterTypeId = jsonObject.getInt("newsletterTypeId");

		String userName = "";

		Optional<NewsletterType> newsletterType = newsletterTypeService.findById(newsletterTypeId);
		String newsletterTypeName = newsletterType.get().getNewsletterTypeName();
		String newsletterTypeDesc = newsletterType.get().getNewsletterTypeDesc();

		Optional<ArrayList<NewsletterUser>> mails = newsletterUserService.findNewsletterActive(newsletterId);

		Map<String, Object> dataMail = new HashMap<String, Object>();

		dataMail.put("newsletterTypeName", newsletterTypeName);
		dataMail.put("newsletterTypeDesc", newsletterTypeDesc);
		dataMail.put("newsletterName", newsletterName);
		dataMail.put("newsletterDesc", newsletterDescription);

		for (NewsletterUser mail : mails.get()) {

			userName = UserService.getName(mail.getUserEmail());
			dataMail.put("userName", userName);
			dataMail.put("userEmail", mail.getUserEmail());			

			sendEmail(mail.getUserEmail(), newsletterTypeName + " - " + MessageHandler.MESSAGE_SUBJECT, dataMail,
					newsletterAttach);

			LocalDateTime localDate1 = LocalDateTime.now();

			updateNewsletter(localDate1, newsletterId);

		}

		ResponseDetails responseOk = new ResponseDetails(new Date(), "Success", MessageHandler.MESSAGE_SUCCESS);

		return new ResponseEntity<>(responseOk, HttpStatus.CREATED);
	}

	public ResponseEntity<Optional<Newsletter>> updateNewsletter(LocalDateTime dateTime, Integer newsletterId) {
		return newsletterService.findById(newsletterId)
				.map(n -> ResponseEntity.ok(newsletterService.updateFields(dateTime, newsletterId)))
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

}
