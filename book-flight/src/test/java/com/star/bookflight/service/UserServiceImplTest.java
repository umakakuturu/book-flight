/**
 * 
 */
package com.star.bookflight.service;



import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.star.bookflight.dto.LoginDto;
import com.star.bookflight.dto.ResponseDto;
import com.star.bookflight.entity.User;
import com.star.bookflight.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Test
	public void userLoginTest() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseDto.setStatusMessage("Please provide the required  data");

		User user = new User();
		user.setEmail("abc@gmail.com");
		user.setPassword("asdf");
		user.setUserId(1L);

		LoginDto loginDto = new LoginDto();
		loginDto.setPassword("");
		loginDto.setUserName("");

		Mockito.when(userRepository.findByEmailAndPassword("abc@gmail.com", "asdf")).thenReturn(user);
		ResponseDto result = userServiceImpl.userLogin(loginDto);

		assertEquals("Please provide the required  data", result.getStatusMessage());
	}

	@Test
	public void userLoginTEstForNegative() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseDto.setStatusMessage("User not registered with the given username, Please register");

		User user = null;

		LoginDto loginDto = new LoginDto();
		loginDto.setPassword("sd");
		loginDto.setUserName("fdfdg");

		Mockito.when(userRepository.findByEmailAndPassword("abc@gmail.com", "asdf")).thenReturn(user);
		ResponseDto result = userServiceImpl.userLogin(loginDto);

		assertEquals("User not registered with the given username, Please register", result.getStatusMessage());
	}

}
