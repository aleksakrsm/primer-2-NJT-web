package it.engineering.spring.mvc.ds.converter.impl;

import java.util.List;

import javax.transaction.NotSupportedException;

import org.springframework.stereotype.Component;

import it.engineering.spring.mvc.ds.converter.ConverterDtoEntity;
import it.engineering.spring.mvc.ds.dto.CityDto;
import it.engineering.spring.mvc.ds.dto.Dto;
import it.engineering.spring.mvc.ds.entity.CityEntity;

@Component
public class CityConverterDtoEntity implements ConverterDtoEntity<CityDto, CityEntity> {

	@Override
	public CityDto toDto(CityEntity e) {
		return new CityDto(e.getNumber(), e.getName());
	}

	@Override
	public CityEntity toEntity(CityDto dto) {
		return new CityEntity(dto.getNumber(),dto.getName());
	}
}
