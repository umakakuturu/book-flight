package com.star.bookflight.service;

import com.star.bookflight.dto.LoginDto;
import com.star.bookflight.dto.ResponseDto;

public interface UserService {

	public ResponseDto userLogin(LoginDto loginDto);

}
