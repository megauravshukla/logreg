package com.gks.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gks.service.EmailService;
import com.gks.to.EmailDetails;
import com.gks.to.EmailTO;

@RestController
@RequestMapping("mail")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestBody EmailTO emailTO) {
		String res = null;
		Boolean sent;
		try {
			sent = emailService.sendEmail(emailTO);

			if (sent) {
				res = "Email Sent Successfully...";
			} else {
				res = "Error while sending email....";
			}
		} catch (MessagingException e) {
		} catch (IOException e) {
		}
		return ResponseEntity.ok(res);
	}

	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails details) {
		
//		{"recipient":"kumargaurav03dec@gmail.com",
//		"msgBody":"Hi... This is simple mail for Gaurav...",
//		"subject":"check email"}
//		http://localhost:8080/logreg/mail/sendMail
		
		String status = emailService.sendSimpleMail(details);
		return status;
	}

	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(@RequestBody EmailDetails details) {
		
//		{"recipient":"kumargaurav03dec@gmail.com",
//		"msgBody":"Hi... This is simple mail with attachment...",
//		"subject":"check email",
//		"attachment":"C:/Users/Gaurav/Downloads/PDFContent.pdf"}
//		http://localhost:8080/logreg/mail/sendMailWithAttachment
		
		String status = emailService.sendMailWithAttachment(details);
		return status;
	}

}
