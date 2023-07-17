package it.engineering.spring.mvc.ds.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CityDto implements Serializable, Dto{
	private static final long serialVersionUID = -8838870410328204377L;
	@NotNull(message = "Number is required...")
	private Long number;
	@Size(min = 3,max = 10,message = "Betweeen 3 and 10...")
	@NotEmpty(message = "Name is required...")
	private String name;

	public CityDto() {
		System.out.println("====================================================");
		System.out.println("Kreiran je objekat klase CityDto.");
		System.out.println("====================================================");
			
	}

	public CityDto(Long number, String name) {
		this.number = number;
		this.name = name;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CityDto [number=" + number + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityDto other = (CityDto) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	
	
	
}
