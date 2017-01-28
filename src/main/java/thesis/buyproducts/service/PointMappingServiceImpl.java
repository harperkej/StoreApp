package thesis.buyproducts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thesis.buyproducts.dto.PointMappingDto;
import thesis.buyproducts.execption.ExceptionType;
import thesis.buyproducts.execption.RepositoryException;
import thesis.buyproducts.execption.ServiceException;
import thesis.buyproducts.repository.PointMappingRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class PointMappingServiceImpl implements PointMappingService {

    @Autowired
    private PointMappingRepository pointMappingRepository;

    @Override
    public boolean updatePointMapper(PointMappingDto pointMapper) throws ServiceException {
        Boolean update;
        try {
            update = pointMappingRepository.updatePointMapper(pointMapper);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), ExceptionType.UNKONW_ERROR);
        }
        return update;
    }

    @Override
    public Double getPointMapper() throws ServiceException {
        Double result;
        try {
            result = pointMappingRepository.getPointMapper();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), ExceptionType.UNKONW_ERROR);
        }
        return result;
    }
}
