package com.star.bookflight.dto;

public class ResponseDto {

	private int statusCode;
	private String statusMessage;
	//for these all generate setter nd getter
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
