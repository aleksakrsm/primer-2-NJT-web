package it.engineering.spring.mvc.ds.dto;

import java.io.Serializable;
public class UserEntityDto implements Serializable, Dto{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String password;
	
	public UserEntityDto() {
	}

	public UserEntityDto(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
