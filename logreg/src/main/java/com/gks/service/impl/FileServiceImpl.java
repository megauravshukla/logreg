package com.gks.service.impl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gks.entity.Hotel;
import com.gks.entity.PatientData;
import com.gks.repository.HotelRepository;
import com.gks.repository.PatientDataRepository;
import com.gks.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

	@Autowired
	private HotelRepository hotelDAO;

	@Autowired
	private PatientDataRepository patientDataDAO;

	@Override
	public void generatePPt() {

	}

	@Override
	public List<PatientData> readPatientExcelAndInsertInDatabase(String fileLocation) {
		List<PatientData> patDataList = new ArrayList<PatientData>();
		try {
			FileInputStream fis = new FileInputStream(fileLocation);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0); // Hotel Bookings
			int rownum = sheet.getLastRowNum();
			int colnum = sheet.getRow(1).getLastCellNum();
			for (int row = 1; row < rownum; row++) {
				XSSFRow rowObj = sheet.getRow(row);
				PatientData patData = new PatientData();
				// XSSFCell cellObj = rowObj.getCell(0);
				int patid = (int) rowObj.getCell(0).getNumericCellValue();
				patData.setPatient_id(patid);
				patData.setAdmit_date(rowObj.getCell(1).getDateCellValue());
				patData.setDischarge_date(rowObj.getCell(2).getDateCellValue());
				patData.setPriority(rowObj.getCell(3).getStringCellValue());
				patData.setHospital(rowObj.getCell(4).getStringCellValue());
				patData.setProfit(rowObj.getCell(5).getNumericCellValue());
				patData.setPrice(rowObj.getCell(6).getNumericCellValue());
				patData.setCost(rowObj.getCell(7).getNumericCellValue());
				patData.setRevenue(rowObj.getCell(8).getNumericCellValue());
				patData.setDiscount(rowObj.getCell(9).getNumericCellValue());
				patData.setPayorSegment(rowObj.getCell(10).getStringCellValue());
				patData.setCategory(rowObj.getCell(11).getStringCellValue());
				patData.setSub_category(rowObj.getCell(12).getStringCellValue());
				patData.setDept(rowObj.getCell(13).getStringCellValue());
				patData.setService(rowObj.getCell(14).getStringCellValue());
				patData.setState(rowObj.getCell(15).getStringCellValue());
				int zipCode = (int) rowObj.getCell(16).getNumericCellValue();
				patData.setZip_code(zipCode);
				patData.setPhysician(rowObj.getCell(17).getStringCellValue());
				patData.setRegion(rowObj.getCell(18).getStringCellValue());
				patientDataDAO.save(patData);
				patDataList.add(patData);
			}
		} catch (Exception e) {
			log.error("Error While Reading Excel File...", e.getMessage());
			e.printStackTrace();
		}

		return patDataList;
	}

	@Override
	public List<Hotel> readHotelExcelAndInsertInDatabase(String fileLocation) {
		List<Hotel> hotelDataList = new ArrayList<Hotel>();
		try (FileInputStream fis = new FileInputStream(fileLocation); XSSFWorkbook workbook = new XSSFWorkbook(fis);) {

			XSSFSheet sheet = workbook.getSheetAt(0); // Hotel Bookings
			int rownum = sheet.getLastRowNum();
			int colnum = sheet.getRow(1).getLastCellNum();
			for (int row = 1; row < rownum; row++) {
				XSSFRow rowObj = sheet.getRow(row);
				Hotel hotel = new Hotel();
				hotel.setBooking_ID(rowObj.getCell(0).getStringCellValue());
				hotel.setHotel(rowObj.getCell(1).getStringCellValue());
				hotel.setIs_canceled((byte) rowObj.getCell(2).getNumericCellValue());
				hotel.setLead_time((int) rowObj.getCell(3).getNumericCellValue());
				hotel.setArrival_date_year((int) rowObj.getCell(4).getNumericCellValue());
				hotel.setArrival_date_month(rowObj.getCell(5).getStringCellValue());
				hotel.setArrival_date_week_number((int) rowObj.getCell(6).getNumericCellValue());
				hotel.setArrival_date_day_of_month((int) rowObj.getCell(7).getNumericCellValue());
				hotel.setStays_in_weekend_nights((int) rowObj.getCell(8).getNumericCellValue());
				hotel.setStays_in_week_nights((int) rowObj.getCell(9).getNumericCellValue());
				hotel.setAdults((int) rowObj.getCell(10).getNumericCellValue());
				hotel.setChildren((int) rowObj.getCell(11).getNumericCellValue());
				hotel.setBabies((int) rowObj.getCell(12).getNumericCellValue());
				hotel.setMeal(rowObj.getCell(13).getStringCellValue());
				hotel.setCountry(rowObj.getCell(14).getStringCellValue());
				hotel.setMarket_segment(rowObj.getCell(15).getStringCellValue());
				hotel.setDistribution_channel(rowObj.getCell(16).getStringCellValue());
				hotel.setIs_repeated_guest((byte) rowObj.getCell(17).getNumericCellValue());
				hotel.setPrevious_cancellations((int) rowObj.getCell(18).getNumericCellValue());
				hotel.setPrevious_bookings_not_canceled((int) rowObj.getCell(19).getNumericCellValue());
				hotel.setReserved_room_type(rowObj.getCell(20).getStringCellValue());
				hotel.setAssigned_room_type(rowObj.getCell(21).getStringCellValue());
				hotel.setBooking_changes((int) rowObj.getCell(22).getNumericCellValue());
				hotel.setDeposit_type(rowObj.getCell(23).getStringCellValue());
				hotel.setAgent((int) rowObj.getCell(24).getNumericCellValue());
				hotel.setCompany(String.valueOf(rowObj.getCell(25).getNumericCellValue()));
				hotel.setDays_in_waiting_list((int) rowObj.getCell(26).getNumericCellValue());
				hotel.setCustomer_type(rowObj.getCell(27).getStringCellValue());
				hotel.setAdr((float) rowObj.getCell(28).getNumericCellValue());
				hotel.setRequired_car_parking_spaces((int) rowObj.getCell(29).getNumericCellValue());
				hotel.setTotal_of_special_requests((int) rowObj.getCell(30).getNumericCellValue());
				hotel.setReservation_status(rowObj.getCell(31).getStringCellValue());
				hotel.setReservation_status_date(rowObj.getCell(32).getDateCellValue());
				hotelDAO.save(hotel);
			}
		} catch (Exception e) {
			log.error("Error While Reading Excel File...", e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

}
