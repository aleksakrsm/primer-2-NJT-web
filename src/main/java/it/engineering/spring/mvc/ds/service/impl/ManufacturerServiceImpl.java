package it.engineering.spring.mvc.ds.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.spring.mvc.ds.converter.impl.ManufacturerConverterDtoEntity;
import it.engineering.spring.mvc.ds.dao.CityDao;
import it.engineering.spring.mvc.ds.dao.ManufacturerDao;
import it.engineering.spring.mvc.ds.dto.CityDto;
import it.engineering.spring.mvc.ds.dto.ManufacturerDto;
import it.engineering.spring.mvc.ds.entity.CityEntity;
import it.engineering.spring.mvc.ds.entity.ManufacturerEntity;
import it.engineering.spring.mvc.ds.exception.ExistEntityException;
import it.engineering.spring.mvc.ds.service.ManufacturerService;


@Service
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService{
	private final ManufacturerDao manufacturerDao;
	private final CityDao cityDao;
	private final ManufacturerConverterDtoEntity manufacturerConverterDtoEntity;
	
	@Autowired
	public ManufacturerServiceImpl(ManufacturerDao manufacturerDao,  
			CityDao cityDao,
			ManufacturerConverterDtoEntity manufacturerConverterDtoEntity){
		this.manufacturerDao = manufacturerDao;
		this.cityDao = cityDao;
		this.manufacturerConverterDtoEntity = manufacturerConverterDtoEntity;
	}
	
	@Override
	public void save(ManufacturerDto manufacturerDto) throws Exception {
		//proveri da li postoji proizvodjac
		//validacija, ako ima ID ne bi smelo da se sacuva: iskljucivo radi SAVE
		
		//proveri da li postoji grad
		CityEntity cityEntity = cityDao.findbyId(manufacturerDto.getCityDto().getNumber());
		if (cityEntity== null) throw new ExistEntityException(manufacturerDto,"Grad ne postoji");
		
		ManufacturerEntity manufacturerEntity = manufacturerConverterDtoEntity.toEntity(manufacturerDto);
		manufacturerEntity.setCity(cityEntity);
		
		//sacuvaj proizvodjaca
		manufacturerDao.save(manufacturerEntity);
		System.out.println("public void save(ManufacturerDto manufacturerDto) throws Exception ");
		System.out.println("FINISHED");
	}

	@Override
	public List<ManufacturerDto> getAll() throws Exception {
		List<ManufacturerEntity> entities = manufacturerDao.getAll();
		return entities.stream().map(entity->{return manufacturerConverterDtoEntity.toDto(entity);}).collect(Collectors.toList());
	}

	@Override
	public ManufacturerDto findById(Long id) throws Exception {
		ManufacturerEntity manufacturerEntity = manufacturerDao.findbyId(id);
		
		if (manufacturerEntity==null) throw new ExistEntityException(manufacturerEntity, "Proizvodjac ne postoji!");
	
		return manufacturerConverterDtoEntity.toDto(manufacturerEntity);
	}

	@Override
	public ManufacturerDto update(ManufacturerDto manufacturerDto) throws Exception {
		ManufacturerEntity entity = manufacturerDao.findbyId(manufacturerDto.getId());
		if (entity==null) throw new ExistEntityException(entity, "Proizvodjac ne postoji!");
		
		CityEntity cityEntity= cityDao.findbyId(manufacturerDto.getCityDto().getNumber());
		if (cityEntity==null) throw new ExistEntityException(cityEntity, "Grad ne postoji!");
		
		ManufacturerEntity manufacturerEntity = manufacturerConverterDtoEntity.toEntity(manufacturerDto);

		manufacturerEntity.setCity(cityEntity);
		
		//update
		manufacturerEntity = manufacturerDao.update(manufacturerEntity);
		manufacturerDto.getContactPersonsDto().size();
		//convert - entity to dto
		return manufacturerConverterDtoEntity.toDto(manufacturerEntity);
	}
	
}
