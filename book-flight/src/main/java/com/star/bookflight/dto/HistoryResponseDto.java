package com.star.bookflight.dto;

import java.util.Date;
import java.util.List;

public class HistoryResponseDto {

	private String flightName;
	private Double cost;
	private String source;
	private String destination;
	private Date dateTime;

	private List<PassengerReqDto> passengerDetails;
	//for these all generate setter nd getter
	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public List<PassengerReqDto> getPassengerDetails() {
		return passengerDetails;
	}

	public void setPassengerDetails(List<PassengerReqDto> passengerDetails) {
		this.passengerDetails = passengerDetails;
	}

}
