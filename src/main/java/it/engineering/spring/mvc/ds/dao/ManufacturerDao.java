package it.engineering.spring.mvc.ds.dao;

import java.util.List;
import it.engineering.spring.mvc.ds.entity.ManufacturerEntity;

public interface ManufacturerDao {
	void save(ManufacturerEntity manufacturerEntity) throws Exception;
	ManufacturerEntity update(ManufacturerEntity manufacturerEntity) throws Exception;
	ManufacturerEntity findbyId(Long id) throws Exception;
	List<ManufacturerEntity> getAll() throws Exception;
}
