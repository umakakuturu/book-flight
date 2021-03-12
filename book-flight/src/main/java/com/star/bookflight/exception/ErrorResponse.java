
package com.star.bookflight.exception;

import java.util.List;

public class ErrorResponse {

	private String message;

	private int status;

	private List<String> details;

	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;

		this.details = details;
	}

	public ErrorResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}
