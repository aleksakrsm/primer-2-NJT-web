package it.engineering.spring.mvc.ds.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDto implements Serializable, Dto {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private CityDto cityDto;
	private List<ContactPersonDto> contactPersonsDto;
	
	public ManufacturerDto() {
		contactPersonsDto = new ArrayList<ContactPersonDto>();
	}

	public ManufacturerDto(Long id, String name, CityDto cityDto) {
		super();
		this.id = id;
		this.name = name;
		this.cityDto = cityDto;
		contactPersonsDto = new ArrayList<ContactPersonDto>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CityDto getCityDto() {
		return cityDto;
	}

	public void setCityDto(CityDto cityDto) {
		this.cityDto = cityDto;
	}

	@Override
	public String toString() {
		return "ManufacturerDto [id=" + id + ", name=" + name + ", cityDto=" + cityDto + "]";
	}

	public List<ContactPersonDto> getContactPersonsDto() {
		return contactPersonsDto;
	}

	public void setContactPersonsDto(List<ContactPersonDto> contactPersonsDto) {
		this.contactPersonsDto = contactPersonsDto;
	}
	
	
	
}