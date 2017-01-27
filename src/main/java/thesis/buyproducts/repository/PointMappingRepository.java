package thesis.buyproducts.repository;

import thesis.buyproducts.entity.PointMappingEntity;

public interface PointMappingRepository {

	public boolean updatePointMapper(PointMappingEntity pointMapper);

	public Double getPointMapper();

}
