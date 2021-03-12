package com.star.bookflight.service;

import java.util.List;

import com.star.bookflight.dto.HistoryResponseDto;
import com.star.bookflight.dto.PassengerReqDto;
import com.star.bookflight.dto.ResponseDto;
import com.star.bookflight.exception.FlightExceptions;

public interface PassengerDetailsService {

	public ResponseDto bookFlight(Long flightId, Long userId, List<PassengerReqDto> passengerReqDto);

	public List<HistoryResponseDto> history(long userId) throws FlightExceptions;

}
