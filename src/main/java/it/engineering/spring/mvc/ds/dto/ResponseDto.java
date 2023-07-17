package it.engineering.spring.mvc.ds.dto;
public class ResponseDto {

	private String message;
	private boolean success;
	
	public ResponseDto() { }

	public ResponseDto(String message, boolean success) {
		this.message = message;
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}