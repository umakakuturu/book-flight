package com.star.bookflight.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.star.bookflight.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight , Long> {
	
	public Flight findByFlightId(Long flightId);
	
	public List<Flight> findBySourceAndDestinationAndDateTime
	(String source , String destination ,Date dateTime);

}
