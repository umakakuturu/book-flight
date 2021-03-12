/**
 * 
 */
package com.star.bookflight.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

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


@RunWith(MockitoJUnitRunner.Silent.class)
public class PassengerDetailsServiceImplTest {

	@InjectMocks
	PassengerDetailsServiceImpl passengerDetailsServiceImpl;

	@Mock
	PassengerDetailsRepository passengerDetailsRepository;

	@Mock
	FlightRepository flightRepository;

	@Mock
	UserRepository userRepository;

	@Test
	public void bookFlightTest() {
		PassengerReqDto passengerReqDto = new PassengerReqDto();

		passengerReqDto.setAge(12);
		passengerReqDto.setMealPreference("Yes");
		passengerReqDto.setName("Baiu");
		passengerReqDto.setSex("gender");

		User user = new User();

		user.setEmail("abc@gmail.com");
		user.setPassword("asdf");
		user.setUserId(1L);

		Flight flight = new Flight();
		flight.setCost(39654);
		flight.setDestination("Bangalore");
		flight.setFlightId(1233L);
		flight.setFlightName("AirIndia");
		flight.setSource("Chennai");

		List<PassengerReqDto> passengerReqDto1 = new ArrayList<PassengerReqDto>();
		passengerReqDto1.add(passengerReqDto);

		PassengerDetails passengerDetails = new PassengerDetails();
		passengerDetails.setFlightId(1233L);
		passengerDetails.setUserId(1L);
		passengerDetails.setAge(13);
		passengerDetails.setName("Baiu");
		passengerDetails.setMealPreference("Yes");
		passengerDetails.setSex("Female");

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.OK.value());
		responseDto.setStatusMessage("Flight booked Successfully");

		Mockito.when(passengerDetailsRepository.save(passengerDetails)).thenReturn(passengerDetails);

		Mockito.when(flightRepository.findByFlightId(1233L)).thenReturn(flight);

		Mockito.when(userRepository.findByUserId(1L)).thenReturn(user);

		ResponseDto result = passengerDetailsServiceImpl.bookFlight(1233L, 1L, passengerReqDto1);

		assertEquals("Flight booked Successfully", result.getStatusMessage());

	}

	
}
