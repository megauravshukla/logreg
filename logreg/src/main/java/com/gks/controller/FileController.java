package com.gks.controller;

import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gks.entity.Patient;
import com.gks.service.EmailService;
import com.gks.service.FileService;
import com.gks.service.LogRegService;

import jdk.internal.org.jline.utils.Log;

@RestController
@RequestMapping("files")
public class FileController {
	@Autowired
	private LogRegService logRegService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private FileService fileService;

	@GetMapping(path = "/createreport/ppt")
	public ResponseEntity<Resource> getPPTReports() {
		String fileName = "patient.pptx";
		FileInputStream fileInputStream = null;
		List<Patient> patList = logRegService.findAllPatients();
		try {
			XMLSlideShow ppt = new XMLSlideShow();
			List<XSLFSlideMaster> slideMasters = ppt.getSlideMasters();
			for (Patient pat : patList) {
				for (XSLFSlideMaster slideMaster : slideMasters) {
					XSLFSlideLayout titleAndContentLayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
					XSLFSlide slide1 = ppt.createSlide(titleAndContentLayout);
					XSLFTextShape title1 = slide1.getPlaceholder(0);
					title1.setText("Patient Name : " + pat.getPatientName());
					title1.setFillColor(java.awt.Color.decode("#ADD8E6"));
					XSLFTextShape body = slide1.getPlaceholder(1);
					body.clearText();
					body.addNewTextParagraph().addNewTextRun()
							.setText("Patient Id : " + pat.getPid() + "\n" + "Diesease : " + pat.getPatientDiesease()
									+ "\n" + "Attending Doctor : " + pat.getDoctor());
					body.setFillColor(java.awt.Color.decode("#F5F5F5"));
				}
			}
			File file = new File(fileName);
			FileOutputStream out = new FileOutputStream(file);
			ppt.write(out);
			fileInputStream = new FileInputStream(file);
			System.out.println("Presentation created successfully");
			out.close();
		} catch (Exception e) {
			Log.info("Error while creating ppt file " + e.getMessage());
		}
		InputStreamResource fileInputStream1 = new InputStreamResource(fileInputStream);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;  filename=" + fileName);
		headers.set(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-powerpoint");
		return new ResponseEntity<>(fileInputStream1, headers, HttpStatus.OK);
	}

	@GetMapping(path = "/createreport/table/ppt")
	public ResponseEntity<Resource> getPPTReport() {
		String fileName = "patient_new.pptx";
		FileInputStream fileInputStream = null;
		List<Patient> patList = logRegService.findAllPatients();
		try {
			XMLSlideShow ppt = new XMLSlideShow();
			List<XSLFSlideMaster> slideMasters = ppt.getSlideMasters();
			for (Patient pat : patList) {
				for (XSLFSlideMaster slideMaster : slideMasters) {
					XSLFSlideLayout titleAndContentLayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
					XSLFSlide slide1 = ppt.createSlide(titleAndContentLayout);
					XSLFTextShape title1 = slide1.getPlaceholder(0);
					title1.setText("Patient Name : " + pat.getPatientName());
					title1.setFillColor(java.awt.Color.decode("#ADD8E6"));
					XSLFTable bodytable = slide1.createTable();
					bodytable.setAnchor(new Rectangle(50, 50, 450, 300));
					int numColumns = 3;
					XSLFTableRow headerRow = bodytable.addRow();
					headerRow.setHeight(50);
					for (int i = 0; i < numColumns; i++) {
						XSLFTableCell th = headerRow.addCell();
						XSLFTextParagraph p = th.addNewTextParagraph();
						p.setTextAlign(p.getTextAlign().CENTER);
						XSLFTextRun r = p.addNewTextRun();
						r.setText("Header " + (i + 1));
						bodytable.setColumnWidth(i, 150);
					}
					int numRows = patList.size();
					for (int rownum = 1; rownum < numRows; rownum++) {
						XSLFTableRow tr = bodytable.addRow();
						tr.setHeight(50);

						for (int i = 0; i < numColumns; i++) {
							XSLFTableCell cell = tr.addCell();
							XSLFTextParagraph p = cell.addNewTextParagraph();
							XSLFTextRun r = p.addNewTextRun();
							r.setText("Cell " + (i * rownum + 1));
						}
					}
//					body.addNewTextParagraph().addNewTextRun().setText("Patient Id : " + pat.getPid() + "\n" + "Diesease : "
//							+ pat.getPatientDiesease() + "\n" + "Attending Doctor : " + pat.getDoctor());
//					body.setFillColor(java.awt.Color.decode("#F5F5F5"));
				}
			}
			File file = new File(fileName);
			FileOutputStream out = new FileOutputStream(file);
			ppt.write(out);
			fileInputStream = new FileInputStream(file);
			System.out.println("Presentation created successfully");
			out.close();
		} catch (Exception e) {
			Log.info("Error while creating ppt file " + e.getMessage());
		}
		InputStreamResource fileInputStream1 = new InputStreamResource(fileInputStream);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;  filename=" + fileName);
		headers.set(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-powerpoint");
		return new ResponseEntity<>(fileInputStream1, headers, HttpStatus.OK);
	}

	@GetMapping("/createreport/csv")
	public ResponseEntity<Resource> exportTableInCSV() {
		List<Patient> patList = logRegService.findAllPatients();
		String[] csvHeaders = { "patient_name", "patient_phone", "patient_disease", "attending_doctor" };
		ByteArrayInputStream byteArrayInputStream = null;
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out),
						CSVFormat.DEFAULT.withHeader(csvHeaders));) {
			for (Patient pat : patList) {
				csvPrinter.printRecord(new String[] { pat.getPatientName(), pat.getPatientPhone(),
						pat.getPatientDiesease(), pat.getDoctor() });
			}
			csvPrinter.flush();
			byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			Log.info("Error while creating ppt file " + e.getMessage());
		}
		InputStreamResource fileInputStream = new InputStreamResource(byteArrayInputStream);
		String fileName = "patients.csv";
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;  filename=" + fileName);
		headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");
		return new ResponseEntity<>(fileInputStream, headers, HttpStatus.OK);
	}

	@GetMapping("/save/patent/data")
	public ResponseEntity savePatentDataToDatabase() {

		String fileLocation = "D:\\dataScience\\files\\Patient-Info.xlsx";
		return new ResponseEntity(fileService.readPatientExcelAndInsertInDatabase(fileLocation), HttpStatus.OK);

	}
	@GetMapping("/save/hotel/data")
	public ResponseEntity saveHotelDataToDatabase() {

		String fileLocation = "D:\\dataScience\\files\\HotelData.xlsx";
		return new ResponseEntity(fileService.readHotelExcelAndInsertInDatabase(fileLocation), HttpStatus.OK);

	}

}
