package com.gks.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="patient")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
	
	@Id
    //@GenericGenerator(name = "patient_id", strategy = "com.gks.generate.customkey.PatientIdGenerator")
    //@GeneratedValue(generator = "patient_id")  
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="patient_id")
	private int pid;
	@Column(name ="patient_name")
	private String patientName;
	@Column(name ="patient_phone")
	private String patientPhone;
	@Column(name ="patient_disease")
	private String patientDiesease;
	@Column(name ="attending_doctor")
	private String doctor;

}
