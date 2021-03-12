package com.star.bookflight.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.star.bookflight.entity.PassengerDetails;

public interface PassengerDetailsRepository extends JpaRepository<PassengerDetails, Long> {

	public List<PassengerDetails> findByUserId(long userId);

	@Query("Select distinct o.flightId from PassengerDetails o where o.userId= :userId")
	List<Long> findDistinctFlightIdByUserId(@RequestParam("userId") long userId);

	@Query("from PassengerDetails o where o.userId= :userId and o.flightId= :flightId")
	public List<PassengerDetails> findPassengerDetailsByUsrIdAndFlgId(long userId, Long flightId );

}
