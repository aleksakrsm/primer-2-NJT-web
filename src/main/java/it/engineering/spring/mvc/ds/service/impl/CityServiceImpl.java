package it.engineering.spring.mvc.ds.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.spring.mvc.ds.converter.impl.CityConverterDtoEntity;
import it.engineering.spring.mvc.ds.dao.CityDao;
import it.engineering.spring.mvc.ds.dto.CityDto;
import it.engineering.spring.mvc.ds.entity.CityEntity;
import it.engineering.spring.mvc.ds.exception.ExistEntityException;
import it.engineering.spring.mvc.ds.service.CityService;

@Service("cityServiceImpl")
@Transactional
public class CityServiceImpl  implements CityService{
	private final CityDao cityDao;
	private final CityConverterDtoEntity cityConverterDtoEntity;
	
	@Autowired
	public CityServiceImpl(CityDao cityDao, CityConverterDtoEntity cityConverterDtoEntity) throws Exception {
		this.cityDao = cityDao;
		this.cityConverterDtoEntity = cityConverterDtoEntity;
	}
	
	@Override
	public void save(CityDto cityDto) throws Exception{
		//ToDo validacija
		//proveri da li postoji
		CityEntity existingCity = cityDao.findbyId(cityDto.getNumber());
		if (existingCity!=null) {
			throw new ExistEntityException(cityDto,"City with code number " + cityDto.getNumber()+" already exist!");
		}
		
		cityDao.save(cityConverterDtoEntity.toEntity(cityDto));
	}

	@Override
	public List<CityDto> getAll() throws Exception{
		List<CityEntity> entities = cityDao.getAll();
		return entities.stream().map(entity->{return cityConverterDtoEntity.toDto(entity);}).collect(Collectors.toList());
	}

	@Override
	public CityDto findById(Long number) throws Exception{
		CityEntity cityEntity = cityDao.findbyId(number);
		return cityConverterDtoEntity.toDto(cityEntity);
	}

	@Override
	public List<CityDto> findAll(int page, int size) throws Exception {
		List<CityEntity> entities = cityDao.findAll(page,size);
		return entities.stream().map(entity->{return cityConverterDtoEntity.toDto(entity);}).collect(Collectors.toList());
	}

	@Override
	public long count() {
		return cityDao.count();
	}

	@Override
	public void delete(Long number) throws Exception {
		System.out.println("SERVICE: delete " + number);
		CityEntity cityEntity = cityDao.findbyId(number);
		if (cityEntity==null) {
			throw new ExistEntityException(cityEntity, "Grad ne postoji!");
		}
		
		cityDao.delete(cityEntity);
		System.out.println("SERVICE: deleted ");
	}

}
