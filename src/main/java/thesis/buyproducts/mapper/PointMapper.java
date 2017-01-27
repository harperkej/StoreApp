package thesis.buyproducts.mapper;

import java.util.ArrayList;
import java.util.List;

import thesis.buyproducts.entity.PointMappingEntity;
import thesis.buyproducts.dto.PointMappingDto;

public final class PointMapper implements Mapper<PointMappingEntity, PointMappingDto> {


    private PointMapper() {
    }

    public static PointMapper getInstance() {
        return new PointMapper();
    }

    @Override
    public PointMappingDto mapEntityFrom(PointMappingEntity pointMappingEntity) {
        PointMappingDto pointMappingDto = null;
        if (pointMappingEntity != null) {
            pointMappingDto = new PointMappingDto();
            if (pointMappingEntity.getId() != null) {
                pointMappingDto.setId(pointMappingEntity.getId());
            }
            pointMappingDto.setValue(pointMappingEntity.getValue());
        }
        return pointMappingDto;
    }

    @Override
    public PointMappingEntity mapDtoFrom(PointMappingDto pointMappingDto) {
        PointMappingEntity pointMappingEntity = null;
        if (pointMappingDto != null) {
            pointMappingEntity = new PointMappingEntity();
            if (pointMappingDto.getId() != null) {
                pointMappingEntity.setId(pointMappingDto.getId());
            }
            pointMappingEntity.setValue(pointMappingDto.getValue());
        }
        return pointMappingEntity;
    }

    @Override
    public List<PointMappingDto> mapEntitiesFrom(List<PointMappingEntity> pointMappingEntities) {
        List<PointMappingDto> result = null;
        if (pointMappingEntities != null && pointMappingEntities.isEmpty()) {
            result = new ArrayList<>();
            PointMappingDto pointMappingDto;
            for (PointMappingEntity pointMappingEntity : pointMappingEntities) {
                pointMappingDto = this.mapEntityFrom(pointMappingEntity);
                result.add(pointMappingDto);
            }
        }
        return result;
    }

    @Override
    public List<PointMappingEntity> mapDtosFrom(List<PointMappingDto> pointMappingDtos) {
        List<PointMappingEntity> result = null;
        if (pointMappingDtos != null && !pointMappingDtos.isEmpty()) {
            result = new ArrayList<>();
            PointMappingEntity pointMappingEntity;
            for (PointMappingDto pointMappingDto : pointMappingDtos) {
                pointMappingEntity = this.mapDtoFrom(pointMappingDto);
                result.add(pointMappingEntity);
            }
        }
        return result;
    }
}
