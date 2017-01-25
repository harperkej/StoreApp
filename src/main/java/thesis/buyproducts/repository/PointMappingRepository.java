package thesis.buyproducts.repository;

import thesis.buyproducts.entity.PointMapping;

public interface PointMappingRepository {

	public boolean updatePointMapper(PointMapping pointMapper);

	public Double getPointMapper();

}
