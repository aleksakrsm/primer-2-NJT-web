package it.engineering.spring.mvc.ds.dao;

import java.util.List;

import it.engineering.spring.mvc.ds.entity.CityEntity;

public interface CityDao {
	void save(CityEntity cityEntity) throws Exception;
	CityEntity findbyId(long number) throws Exception;
	List<CityEntity> getAll() throws Exception;
	
	List<CityEntity> findAll(int page, int size)throws Exception;
	Long count();
	void delete(CityEntity cityEntity) throws Exception;
}
