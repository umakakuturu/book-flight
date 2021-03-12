package com.star.bookflight.dto;

public class FlightSearchRespDto {

	private String flightName;
	private double cost;
	//for these all generate setter nd getter
	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
