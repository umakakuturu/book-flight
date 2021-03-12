package com.star.bookflight.service;

import java.util.List;

import com.star.bookflight.dto.FlightSearchRespDto;
import com.star.bookflight.dto.SearchFlighDto;
import com.star.bookflight.exception.FlightExceptions;

public interface FlightService {

	public List<FlightSearchRespDto> searchFlight(SearchFlighDto searchFlighDto)
			throws FlightExceptions;

}
