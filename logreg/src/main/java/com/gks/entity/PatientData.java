package com.gks.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="patient_data")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;	
	private int patient_id;
	private Date admit_date;	
	private Date discharge_date;	
	private String priority;	
	private String hospital;	
	private double profit;	
	private double price;	
	private double cost;
	private double revenue;	
	private double discount;
	@Column(name="payor_segment")
	private String payorSegment;	
	private String category;	
	private String sub_category;	
	private String dept;	
	private String service;	
	private String state;	
	private int zip_code;	
	private String physician;	
	private String region;
}
