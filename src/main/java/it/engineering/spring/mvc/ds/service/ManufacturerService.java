package it.engineering.spring.mvc.ds.service;

import java.util.List;
import it.engineering.spring.mvc.ds.dto.ManufacturerDto;

public interface ManufacturerService {
	void save(ManufacturerDto manufacturerDto) throws Exception;
	List<ManufacturerDto> getAll()throws Exception;
	ManufacturerDto findById(Long id)throws Exception;
	
	ManufacturerDto update(ManufacturerDto manufacturerDto) throws Exception;
}
