package it.engineering.spring.mvc.ds.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import it.engineering.spring.mvc.ds.dto.CityDto;
import it.engineering.spring.mvc.ds.service.CityService;

public class CityDtoFormatter implements Formatter<CityDto> {
	private final CityService cityService;
	
	@Autowired
	public CityDtoFormatter(CityService cityService){
		System.out.println("Kreiran je: CityDtoFormatter");
		this.cityService = cityService;
	}
	
	@Override
	public String print(CityDto cityDto, Locale locale) {
		return cityDto.getNumber()+" - " + cityDto.getName();
	}

	@Override
	public CityDto parse(String city, Locale locale) throws ParseException {
		System.out.println("========    CityDtoFormatter::parse   ======================");
		System.out.println("String city: " + city);
		Long number = Long.parseLong(city);
		try {
			CityDto cityDto = cityService.findById(number);
			return cityDto;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParseException("Greska u formatteru...",0);
		}
		
	}

}
