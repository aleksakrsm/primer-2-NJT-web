package it.engineering.spring.mvc.ds.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.spring.mvc.ds.dao.CityDao;
import it.engineering.spring.mvc.ds.entity.CityEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class JpaCityDaoImpl implements CityDao{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(CityEntity cityEntity) throws Exception {
		entityManager.persist(cityEntity);
	}

	@Override
	public CityEntity findbyId(long number) throws Exception {
		return entityManager.find(CityEntity.class,number);
	}

	@Override
	public List<CityEntity> getAll() throws Exception {
		return entityManager
				.createQuery("SELECT city from CityEntity city ORDER BY name", CityEntity.class)
				.getResultList();
	}

	@Override
	public List<CityEntity> findAll(int pageNumber, int pageSize) throws Exception {
		Query query = entityManager.createQuery("SELECT city from CityEntity city ORDER BY name");
		query.setFirstResult((pageNumber-1) * pageSize); 
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public Long count() {
		return  entityManager.createQuery("SELECT COUNT(city) from CityEntity city",Long.class).getSingleResult();
	}

	@Override
	public void delete(CityEntity cityEntity) throws Exception {
		System.out.println("REMOVE:" + cityEntity);
		entityManager.remove(cityEntity);
	}

}
