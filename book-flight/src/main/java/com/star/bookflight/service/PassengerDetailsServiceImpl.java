package com.star.bookflight.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.star.bookflight.dto.HistoryResponseDto;
import com.star.bookflight.dto.PassengerReqDto;
import com.star.bookflight.dto.ResponseDto;
import com.star.bookflight.entity.Flight;
import com.star.bookflight.entity.PassengerDetails;
import com.star.bookflight.entity.User;
import com.star.bookflight.exception.FlightExceptions;
import com.star.bookflight.repository.FlightRepository;
import com.star.bookflight.repository.PassengerDetailsRepository;
import com.star.bookflight.repository.UserRepository;

@Service
public class PassengerDetailsServiceImpl implements PassengerDetailsService {

	@Autowired
	PassengerDetailsRepository passengerDetailsRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	FlightRepository flightRepository;

	@Override
	public ResponseDto bookFlight(Long flightId, Long userId, List<PassengerReqDto> passengerReqDto) {

		ResponseDto responseDto = new ResponseDto();
		// List<PassengerReqDto> passengerReqDtos = new ArrayList<>();

		Flight flight = flightRepository.findByFlightId(flightId);
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseDto.setStatusMessage("User not exist");
		} else if (flight == null) {
			responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseDto.setStatusMessage("Flight not exist");
		} else {

			for (PassengerReqDto passengerReq : passengerReqDto) {
				PassengerDetails passengerDetails = new PassengerDetails();
				passengerDetails.setFlightId(flightId);
				passengerDetails.setUserId(userId);
				passengerDetails.setAge(passengerReq.getAge());
				passengerDetails.setName(passengerReq.getName());
				passengerDetails.setMealPreference(passengerReq.getMealPreference());
				passengerDetails.setSex(passengerReq.getSex());
				passengerDetailsRepository.save(passengerDetails);
				responseDto.setStatusCode(HttpStatus.OK.value());
				responseDto.setStatusMessage("Flight booked Successfully");

			}

		}

		return responseDto;

	}

	@Override
	public List<HistoryResponseDto> history(long userId) throws FlightExceptions {
		List<HistoryResponseDto> historyResponseDto = new ArrayList<>();
		List<Long> flightIds = passengerDetailsRepository.findDistinctFlightIdByUserId(userId);
		if (flightIds.isEmpty()) {
			throw new FlightExceptions("No flights booked for this user");
		} else {
			for (Long flightId : flightIds) {

				Flight flight = flightRepository.findByFlightId(flightId);
				if (flight != null) {

					HistoryResponseDto responseDto = new HistoryResponseDto();
					responseDto.setFlightName(flight.getFlightName());
					responseDto.setDateTime(flight.getDateTime());
					responseDto.setCost(flight.getCost());
					responseDto.setDestination(flight.getDestination());
					responseDto.setSource(flight.getSource());

					List<PassengerDetails> passengerDetails = passengerDetailsRepository
							.findPassengerDetailsByUsrIdAndFlgId(userId, flightId);

					List<PassengerReqDto> psgDetLst = new ArrayList<>();

					for (PassengerDetails details : passengerDetails) {
						PassengerReqDto reqDto = new PassengerReqDto();

						reqDto.setAge(details.getAge());
						reqDto.setMealPreference(details.getMealPreference());
						reqDto.setName(details.getName());
						reqDto.setSex(details.getSex());
						psgDetLst.add(reqDto);
					}
					responseDto.setPassengerDetails(psgDetLst);

					historyResponseDto.add(responseDto);
				}

			}
		}

		return historyResponseDto;
	}
}
