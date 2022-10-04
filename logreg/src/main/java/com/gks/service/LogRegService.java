package com.gks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gks.entity.Patient;
import com.gks.util.ResponseTO;

@Service
public interface LogRegService {
	
	public ResponseTO addPatients(Patient pstient);
	public List<Patient> findAllPatientsOfDoctor(String doctorName);
	List<Patient> findAllPatients();

}
