package it.engineering.spring.mvc.ds.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import it.engineering.spring.mvc.ds.dao.ManufacturerDao;
import it.engineering.spring.mvc.ds.entity.ManufacturerEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class JpaManufacturerDaoImpl implements ManufacturerDao{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(ManufacturerEntity manufacturerEntity) throws Exception {
		entityManager.persist(manufacturerEntity);
	}

	@Override
	public ManufacturerEntity findbyId(Long id) throws Exception {
		return entityManager.find(ManufacturerEntity.class, id);
	}

	@Override
	public List<ManufacturerEntity> getAll() throws Exception {
		return entityManager
				.createQuery("SELECT m from ManufacturerEntity m ORDER BY name", ManufacturerEntity.class)
				.getResultList();
	}

	@Override
	public ManufacturerEntity update(ManufacturerEntity manufacturerEntity) throws Exception {
		return entityManager.merge(manufacturerEntity);
	}

}
