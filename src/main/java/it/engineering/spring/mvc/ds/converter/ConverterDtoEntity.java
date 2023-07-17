package it.engineering.spring.mvc.ds.converter;

import java.util.List;
import it.engineering.spring.mvc.ds.dto.Dto;
import it.engineering.spring.mvc.ds.entity.Entity;

public interface ConverterDtoEntity<DTO extends Dto,ENTITY extends Entity> {
	public DTO toDto(ENTITY e);
	public ENTITY toEntity(DTO dto);
}
