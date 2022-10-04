package com.gks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gks.entity.PatientData;

@Repository("patientDataDAO")
public interface PatientDataRepository  extends JpaRepository<PatientData, Integer>{

}
