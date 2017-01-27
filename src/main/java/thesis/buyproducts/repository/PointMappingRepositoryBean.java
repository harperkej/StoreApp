package thesis.buyproducts.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import thesis.buyproducts.entity.PointMappingEntity;

@Repository
public class PointMappingRepositoryBean implements PointMappingRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean updatePointMapper(PointMappingEntity pointMapper) {
		try {
			pointMapper.setId(new Long(1));
			entityManager.merge(pointMapper);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Double getPointMapper() {
		PointMappingEntity pointMapper = entityManager.find(PointMappingEntity.class, new Long(1));
		if (pointMapper == null) {
			return new Double(0);
		} else {
			return pointMapper.getValue();
		}
	}

}
