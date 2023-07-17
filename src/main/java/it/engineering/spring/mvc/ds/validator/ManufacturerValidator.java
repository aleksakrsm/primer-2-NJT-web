package it.engineering.spring.mvc.ds.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.engineering.spring.mvc.ds.dto.ManufacturerDto;

public class ManufacturerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ManufacturerDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("Pozvana metoda validate u ManufacturerValidator klaasi...");
		ManufacturerDto manufacturerDto = (ManufacturerDto) target;
		if (manufacturerDto!=null) {
			if (manufacturerDto.getName().isEmpty()) {
				errors.rejectValue("name", "manufacturer.name", "Ime je obavezno polje.");
			}
			if(manufacturerDto.getCityDto()==null) {
				errors.rejectValue("cityDto", "manufacturer.city", "Grad je obavezno polje.");
			}
		}
	}

}
