package thesis.buyproducts.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thesis.buyproducts.entity.PointMappingEntity;
import thesis.buyproducts.repository.PointMappingRepository;

@Service
@Transactional
public class PointMappingServiceImpl implements PointMappingService {

    @Autowired
    private PointMappingRepository pointMappingDao;

    @Override
    public boolean updatePointMapper(PointMappingEntity pointMapper) {
        return pointMappingDao.updatePointMapper(pointMapper);
    }

    @Override
    public Double getPointMapper() {
        return pointMappingDao.getPointMapper();
    }
}
