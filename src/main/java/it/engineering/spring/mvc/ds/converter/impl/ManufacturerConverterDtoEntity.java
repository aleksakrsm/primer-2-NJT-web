package it.engineering.spring.mvc.ds.converter.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.engineering.spring.mvc.ds.converter.ConverterDtoEntity;
import it.engineering.spring.mvc.ds.dto.ContactPersonDto;
import it.engineering.spring.mvc.ds.dto.Dto;
import it.engineering.spring.mvc.ds.dto.ManufacturerDto;
import it.engineering.spring.mvc.ds.entity.ContactPersonEntity;
import it.engineering.spring.mvc.ds.entity.ManufacturerEntity;

@Component
public class ManufacturerConverterDtoEntity implements ConverterDtoEntity<ManufacturerDto, ManufacturerEntity> {
	private CityConverterDtoEntity cityConverterDtoEntity;
	private ContactPersonConverterDtoEntity contactPersonConverterDtoEntity;
	
	@Autowired
	public ManufacturerConverterDtoEntity(CityConverterDtoEntity cityConverterDtoEntity,
			ContactPersonConverterDtoEntity contactPersonConverterDtoEntity) {
		this.cityConverterDtoEntity = cityConverterDtoEntity;
		this.contactPersonConverterDtoEntity = contactPersonConverterDtoEntity; 
	}
	
	@Override
	public ManufacturerDto toDto(ManufacturerEntity e) {
		ManufacturerDto dto = new ManufacturerDto(e.getId(),
								e.getName(),
								cityConverterDtoEntity.toDto(e.getCity()));
		
		List<ContactPersonDto> listDto =e.getContactPersons()
				.stream()
				.map((entity)->{
					return contactPersonConverterDtoEntity.toDto(entity);
				})
				.collect(Collectors.toList());
		dto.setContactPersonsDto(listDto);
		
		return dto;
	}

	@Override
	public ManufacturerEntity toEntity(ManufacturerDto dto) {
		ManufacturerEntity entity =  new ManufacturerEntity(dto.getId(), 
										dto.getName(), 
										cityConverterDtoEntity.toEntity(dto.getCityDto()));
		List<ContactPersonEntity> listEntity =dto.getContactPersonsDto()
				.stream()
				.map((e)->{
					return contactPersonConverterDtoEntity.toEntity(e);
				})
				.map((e)->{
					e.setManufacturer(entity);
					return e;
				})
				.collect(Collectors.toList());
		entity.setContactPersons(listEntity);
		return entity;
	}

}
