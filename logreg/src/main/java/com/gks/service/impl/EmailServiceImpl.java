package com.gks.service.impl;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.gks.service.EmailService;
import com.gks.to.EmailDetails;
import com.gks.to.EmailTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public boolean sendEmail(EmailTO emailTO) throws MessagingException, IOException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);
		if (null != emailTO.getSender())
			mailMessage.setFrom(emailTO.getSender());
		mailMessage.setTo(emailTO.getReceipients().stream().toArray(String[]::new));
		if (null != emailTO.getCcList() && emailTO.getCcList().size() > 0)
			mailMessage.setCc(emailTO.getCcList().stream().toArray(String[]::new));
		mailMessage.setSubject(emailTO.getMailSubject());
		mailMessage.setText(emailTO.getMailBody(), true);
		Boolean isSent = false;
		try {
			javaMailSender.send(message);
			isSent = true;
		} catch (Exception e) {
			log.error("Error while sending email   ", e.getMessage());
			e.printStackTrace();
		}
		return isSent;
	}

	public String sendSimpleMail(EmailDetails details) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully...";
		} catch (Exception e) {
			log.error("Error occured while sending email....");
			return "Error while Sending Mail";
		}
	}

	public String sendMailWithAttachment(EmailDetails details) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getRecipient());
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(details.getSubject());
			FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
			mimeMessageHelper.addAttachment(file.getFilename(), file);
			javaMailSender.send(mimeMessage);
			return "Mail sent Successfully";
		} catch (MessagingException e) {
			return "Error while sending mail!!!";
		}
	}

}
