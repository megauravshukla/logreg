package com.gks.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.stereotype.Component;

import com.gks.to.EmailDetails;
import com.gks.to.EmailTO;


@Component
public interface EmailService {
	
	public boolean sendEmail(EmailTO emailTO) throws MessagingException, IOException;
	
    String sendSimpleMail(EmailDetails details);
 
    String sendMailWithAttachment(EmailDetails details);

}
