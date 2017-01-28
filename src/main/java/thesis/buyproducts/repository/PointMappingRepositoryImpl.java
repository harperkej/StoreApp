package thesis.buyproducts.repository;

import org.springframework.stereotype.Repository;
import thesis.buyproducts.dto.PointMappingDto;
import thesis.buyproducts.entity.PointMappingEntity;
import thesis.buyproducts.execption.ExceptionType;
import thesis.buyproducts.execption.RepositoryException;
import thesis.buyproducts.mapper.PointMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PointMappingRepositoryImpl implements PointMappingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean updatePointMapper(PointMappingDto pointMapper) throws RepositoryException {
        boolean update;
        try {
            pointMapper.setId(new Long(1));
            entityManager.merge(PointMapper.getInstance().mapDtoFrom(pointMapper));
            update = true;
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage(), ExceptionType.UNKONW_ERROR);
        }
        return update;
    }

    @Override
    public Double getPointMapper() throws RepositoryException {
        Double result;
        try {
            PointMappingEntity pointMapper = entityManager.find(PointMappingEntity.class, new Long(1));
            if (pointMapper == null) {
                result = new Double(0);
            } else {
                result = pointMapper.getValue();
            }
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage(), ExceptionType.UNKONW_ERROR);
        }
        return result;
    }

}
