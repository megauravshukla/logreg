package com.gks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gks.entity.Patient;

@Repository("patientDAO")
public interface PatientRepository extends JpaRepository<Patient, Integer>{
	
	public List<Patient> findByDoctor(String doctor);

}
