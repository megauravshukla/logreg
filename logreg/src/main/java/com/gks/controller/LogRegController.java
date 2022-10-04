package com.gks.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gks.entity.Patient;
import com.gks.service.EmailService;
import com.gks.service.LogRegService;
import com.gks.to.EmailDetails;
import com.gks.to.EmailTO;
import com.gks.util.ResponseTO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("main")
public class LogRegController {

	@Autowired
	private LogRegService logRegService;

	@Autowired
	private EmailService emailService;

	@Value("${check.value}")
	public String value;

	@GetMapping("/welcome")
	public String showwelcome() {
		return "Welcome to Springboot";
	}

	@PostMapping(path = "/addpatient", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseTO> addPatients(@RequestBody Patient patient) {
//		{"patientName":"Simon",	
//			"patientPhone":"12345678",
//			"patientDiesease":"abc",
//			"doctor":"DR. Shobhit"}
//		http://localhost:8080/logreg/main/addpatient
		ResponseTO res = logRegService.addPatients(patient);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/getpatients")
	@ApiOperation(value = "Find Patients based on Doctor", 
	notes = "Provide Doctor Name to get the mapped Patients Details ", response = Patient.class)
	public ResponseEntity<List<Patient>> getPatientsByDoctor(@RequestParam(value = "doctorName") String doctorName) {
		
//		http://localhost:8080/logreg/main/getpatients?doctorName=DR. Shobhit
		
		List<Patient> res = logRegService.findAllPatientsOfDoctor(doctorName);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/checkValue")
	public ResponseEntity<String> checkValue() {
		//http://localhost:8080/logreg/main/checkValue
		
		return ResponseEntity.ok(value);
	}

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
//		http://localhost:8080/logreg/main/sendMail
		
		String status = emailService.sendSimpleMail(details);
		return status;
	}

	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(@RequestBody EmailDetails details) {
		
//		{"recipient":"kumargaurav03dec@gmail.com",
//		"msgBody":"Hi... This is simple mail with attachment...",
//		"subject":"check email",
//		"attachment":"C:/Users/Gaurav/Downloads/PDFContent.pdf"}
//		http://localhost:8080/logreg/main/sendMailWithAttachment
		
		String status = emailService.sendMailWithAttachment(details);
		return status;
	}
	
//	@GetMapping("/prob")
//	public void prob() {
//		List<String> list1= new ArrayList();
//		List<String> list= Arrays.asList("gauarav/bkkbh","ajaJ'/\';HBAhjb","bkks\nja","babh;BSAH");
//		list.forEach(str->{
//			char[] charr=str.toCharArray();
//			for(char ch:charr) {
//				if(ch!=){}
//			}
//			list1.addd
//		});
//		
//	}
	
	

}
