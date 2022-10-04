package com.gks.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gks.entity.Patient;
import com.gks.repository.PatientRepository;
import com.gks.service.LogRegService;
import com.gks.util.ResponseTO;

@Service
public class LogRegServiceIMPL implements LogRegService{
	
	@Autowired
	private PatientRepository patientDAO;

	@Override
	public ResponseTO addPatients(Patient patient) {
		ResponseTO resTO = new ResponseTO();
		try {
			patientDAO.save(patient);
			resTO.setResponse("Patient Added Successfully...");
			resTO.setStatus(HttpServletResponse.SC_OK);
		}catch(Exception e) {
			resTO.setResponse("Error while adding patients...");
			resTO.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return resTO;
	}

	@Override
	public List<Patient> findAllPatientsOfDoctor(String doctorName) {
		List<Patient> patientList= patientDAO.findByDoctor(doctorName);
		return patientList;
	}
	
	@Override
	public List<Patient> findAllPatients() {
		List<Patient> patientList= patientDAO.findAll();
		return patientList;
	}
	

}
