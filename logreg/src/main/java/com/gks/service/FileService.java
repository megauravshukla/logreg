package com.gks.service;

import java.util.List;

import com.gks.entity.Hotel;
import com.gks.entity.PatientData;

public interface FileService {
	
	public void generatePPt();
	
	public List<PatientData> readPatientExcelAndInsertInDatabase(String fileLocation);
	public List<Hotel> readHotelExcelAndInsertInDatabase(String fileLocation);

}
