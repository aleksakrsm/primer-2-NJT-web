package it.engineering.spring.mvc.ds.converter.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.engineering.spring.mvc.ds.converter.ConverterDtoEntity;
import it.engineering.spring.mvc.ds.dto.CityDto;
import it.engineering.spring.mvc.ds.dto.ContactPersonDto;
import it.engineering.spring.mvc.ds.dto.Dto;
import it.engineering.spring.mvc.ds.entity.CityEntity;
import it.engineering.spring.mvc.ds.entity.ContactPersonEntity;

@Component
public class ContactPersonConverterDtoEntity implements ConverterDtoEntity<ContactPersonDto, ContactPersonEntity> {

	@Override
	public ContactPersonDto toDto(ContactPersonEntity e) {
		return new ContactPersonDto(e.getId(),e.getFirstname(),e.getLastname());
	}

	@Override
	public ContactPersonEntity toEntity(ContactPersonDto dto) {
		return new ContactPersonEntity(dto.getId(),dto.getFirstname(),dto.getLastname());
	}

}
