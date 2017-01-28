package thesis.buyproducts.repository;

import thesis.buyproducts.dto.PointMappingDto;
import thesis.buyproducts.execption.RepositoryException;

public interface PointMappingRepository {

    public boolean updatePointMapper(PointMappingDto pointMapper) throws RepositoryException;

    public Double getPointMapper() throws RepositoryException;

}
