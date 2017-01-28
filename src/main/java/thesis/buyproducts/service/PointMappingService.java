package thesis.buyproducts.service;

import thesis.buyproducts.dto.PointMappingDto;
import thesis.buyproducts.execption.ServiceException;

public interface PointMappingService {

    public boolean updatePointMapper(PointMappingDto pointMapper) throws ServiceException;

    public Double getPointMapper() throws ServiceException;

}
