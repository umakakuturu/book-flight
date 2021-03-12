package com.star.bookflight.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.bookflight.dto.FlightSearchRespDto;
import com.star.bookflight.dto.SearchFlighDto;
import com.star.bookflight.entity.Flight;
import com.star.bookflight.exception.FlightExceptions;
import com.star.bookflight.repository.FlightRepository;
import com.star.bookflight.utility.FlightUtil;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightRepository flightRepository;

	@Override
	public List<FlightSearchRespDto> searchFlight(SearchFlighDto searchFlighDto) throws FlightExceptions {

		List<FlightSearchRespDto> flightSearchRespDto = new ArrayList<>();

		if (searchFlighDto.getSource().isEmpty() || searchFlighDto.getDestination().isEmpty()
				|| searchFlighDto.getJourneyDate().isEmpty()) {
			throw new FlightExceptions("Please Provide the required data!!");

		} else {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date RequiredDate;
			try {
				RequiredDate = format.parse(searchFlighDto.getJourneyDate());

				String pattern = "MM/dd/yyyy HH:mm:ss";
				Date today = new Date();
				today = Calendar.getInstance().getTime();

				if (RequiredDate.before(today)) {
					throw new FlightExceptions("Please Enter Future Date!!");
				} else {
					List<Flight> flightList = flightRepository.findBySourceAndDestinationAndDateTime(
							searchFlighDto.getSource(), searchFlighDto.getDestination(), RequiredDate);
					for (Flight flight : flightList) {
						FlightSearchRespDto flightSearch = new FlightSearchRespDto();
						flightSearch.setCost(flight.getCost());
						flightSearch.setFlightName(flight.getFlightName());
						flightSearchRespDto.add(flightSearch);
					}
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return flightSearchRespDto;

	}

}
