package com.gks.to;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude
public class HotelTO {
	private String Booking_ID;
	private String hotel;	
	private byte is_canceled;	
	private int lead_time;	
	private int arrival_date_year;	
	private String arrival_date_month;	
	private int arrival_date_week_number;	
	private int arrival_date_day_of_month;	
	private int stays_in_weekend_nights;	
	private int stays_in_week_nights;	
	private int adults;	
	private int children;	
	private int babies;	
	private String meal;	
	private String country;	
	private String market_segment;	
	private String distribution_channel;	
	private byte is_repeated_guest;	
	private int previous_cancellations;	
	private int previous_bookings_not_canceled;	
	private char reserved_room_type;	
	private char assigned_room_type;	
	private int booking_changes;
	private String deposit_type;	
	private String agent;	
	private String company;	
	private int days_in_waiting_list;	
	private String customer_type;	
	private float adr;	
	private int required_car_parking_spaces;	
	private int total_of_special_requests;	
	private String reservation_status;	
	private Date reservation_status_date;

}
