/**
 * 
 */
package com.star.bookflight.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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


@RunWith(MockitoJUnitRunner.class)
public class FlightBookControllerTest {

	/**
	 * @InjectMocks - injects the flightBookController
	 */
	@InjectMocks
	FlightBookController flightBookController;

	/**
	 * @Mock - Mocks the flightService
	 */
	@Mock
	FlightService flightService;

	/**
	 * @Mock - Mocks the userService
	 */
	@Mock
	UserService userService;

	/**
	 * @Mock - Mocks the passengerDetailsService
	 */
	@Mock
	PassengerDetailsService passengerDetailsService;

	/**
	 * @Test - is used to test the particular method
	 * @Mockito.when -
	 */
	@Test
	public void searchFlightTest() throws FlightExceptions {
		SearchFlighDto searchFlighDto = new SearchFlighDto();
		searchFlighDto.setDestination("Bangalore");
		searchFlighDto.setSource("Covai");
		searchFlighDto.setJourneyDate("2020-12-13");

		FlightSearchRespDto flightSearchRespDto = new FlightSearchRespDto();

		flightSearchRespDto.setCost(2000);
		flightSearchRespDto.setFlightName("Air India");

		List<FlightSearchRespDto> flightSearchRespDto1 = new ArrayList<FlightSearchRespDto>();
		flightSearchRespDto1.add(flightSearchRespDto);

		Mockito.when(flightService.searchFlight(searchFlighDto)).thenReturn(flightSearchRespDto1);
		ResponseEntity<List<FlightSearchRespDto>> result = flightBookController.searchFlight(searchFlighDto);

		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void userLoginTest() {
		LoginDto loginDto = new LoginDto();
		loginDto.setPassword("baiu");
		loginDto.setUserName("baiu@gmail.com");

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseDto.setStatusMessage("Please provide the required  data");

		Mockito.when(userService.userLogin(loginDto)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> result = flightBookController.userLogin(loginDto);

		assertEquals(HttpStatus.OK, result.getStatusCode());

	}

	@Test
	public void bookFlightTest() {
		List<PassengerReqDto> passengerReqDto1 = new ArrayList<PassengerReqDto>();
		PassengerReqDto passengerReqDto = new PassengerReqDto();
		passengerReqDto.setAge(12);
		passengerReqDto.setMealPreference("Yes");
		passengerReqDto.setName("Baiu");
		passengerReqDto.setSex("gender");

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseDto.setStatusMessage("Please provide the required  data");

		Mockito.when(passengerDetailsService.bookFlight(1233L, 1L, passengerReqDto1)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> result = flightBookController.bookFlight(1233L, 1L, passengerReqDto1);

		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void historyTest() throws FlightExceptions {

		List<HistoryResponseDto> historyResponseDto1 = new ArrayList<HistoryResponseDto>();

		HistoryResponseDto historyResponseDto = new HistoryResponseDto();

		historyResponseDto.setFlightName("Air India");
		historyResponseDto.setCost(39654.0);
		historyResponseDto.setDestination("Bangalore");
		historyResponseDto.setSource("Chennai");

		Mockito.when(passengerDetailsService.history(1L)).thenReturn(historyResponseDto1);
		ResponseEntity<List<HistoryResponseDto>> result = flightBookController.history(1L);

		assertEquals(HttpStatus.OK, result.getStatusCode());

	}

}
