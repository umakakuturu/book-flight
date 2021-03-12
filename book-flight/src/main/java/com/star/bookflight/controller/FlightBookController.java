package com.star.bookflight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.star.bookflight.dto.FlightSearchRespDto;
import com.star.bookflight.dto.HistoryResponseDto;
import com.star.bookflight.dto.LoginDto;
import com.star.bookflight.dto.PassengerReqDto;
import com.star.bookflight.dto.ResponseDto;
import com.star.bookflight.dto.SearchFlighDto;
import com.star.bookflight.exception.FlightExceptions;
import com.star.bookflight.service.FlightService;
import com.star.bookflight.service.PassengerDetailsService;
import com.star.bookflight.service.UserService;

@RestController
public class FlightBookController {

	@Autowired
	FlightService flightService;

	@Autowired
	PassengerDetailsService passengerDetailsService;

	@Autowired
	UserService userService;

	@PostMapping("flights/availability")
	public ResponseEntity<List<FlightSearchRespDto>> searchFlight(@RequestBody SearchFlighDto searchFlighDto)
			throws FlightExceptions {
		List<FlightSearchRespDto> flightSearchRespDto = flightService.searchFlight(searchFlighDto);
		return new ResponseEntity<>(flightSearchRespDto, HttpStatus.OK);
	}

	@PostMapping("users/login")
	public ResponseEntity<ResponseDto> userLogin(@RequestBody LoginDto loginDto) {
		ResponseDto responseDto = userService.userLogin(loginDto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@PostMapping("flights/book/{flightId}/{userId}")
	public ResponseEntity<ResponseDto> bookFlight(@PathVariable(name = "flightId") long flightId,
			@PathVariable(name = "userId") long userId, @RequestBody List<PassengerReqDto> passengerReqDto) {

		ResponseDto responseDto = passengerDetailsService.bookFlight(flightId, userId, passengerReqDto);

		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@GetMapping("flights/history/{userId}")
	public ResponseEntity<List<HistoryResponseDto>> history(@PathVariable(name = "userId") long userId)
			throws FlightExceptions {

		List<HistoryResponseDto> historyResponseDto = passengerDetailsService.history(userId);

		return new ResponseEntity<>(historyResponseDto, HttpStatus.OK);

	}

}
