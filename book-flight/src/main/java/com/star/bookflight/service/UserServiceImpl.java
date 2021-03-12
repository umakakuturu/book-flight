package com.star.bookflight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.star.bookflight.dto.LoginDto;
import com.star.bookflight.dto.ResponseDto;
import com.star.bookflight.entity.User;
import com.star.bookflight.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseDto userLogin(LoginDto loginDto) {
		ResponseDto responseDto = new ResponseDto();

		if (loginDto.getUserName().isEmpty() || loginDto.getPassword().isEmpty()) {
			responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseDto.setStatusMessage("Please provide the required  data");
		} else {
			User user = userRepository.findByEmailAndPassword(loginDto.getUserName(), loginDto.getPassword());

			if (user != null) {
				responseDto.setStatusCode(HttpStatus.OK.value());
				responseDto.setStatusMessage("Logged in successfully!!..");
			} else {
				responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
				responseDto.setStatusMessage("Please Register");
			}
		}
		return responseDto;
	}
}
