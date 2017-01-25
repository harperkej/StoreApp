package thesis.buyproducts.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import thesis.buyproducts.entity.PointMapping;
import thesis.buyproducts.dto.PointMappingDto;

public final class PointMappingMapperBean implements Mapper<PointMapping, PointMappingDto> {


    private PointMappingMapperBean() {
    }

    public static PointMappingMapperBean getInstance() {
        return new PointMappingMapperBean();
    }

    @Override
    public PointMappingDto mapEntityFrom(PointMapping pointMapping) {
        PointMappingDto pointMappingDto = null;
        if (pointMapping != null) {
            pointMappingDto = new PointMappingDto();
            if (pointMapping.getId() != null) {
                pointMappingDto.setId(pointMapping.getId());
            }
            pointMappingDto.setValue(pointMapping.getValue());
        }
        return pointMappingDto;
    }

    @Override
    public PointMapping mapDtoFrom(PointMappingDto pointMappingDto) {
        PointMapping pointMapping = null;
        if (pointMappingDto != null) {
            pointMapping = new PointMapping();
            if (pointMappingDto.getId() != null) {
                pointMapping.setId(pointMappingDto.getId());
            }
            pointMapping.setValue(pointMappingDto.getValue());
        }
        return pointMapping;
    }

    @Override
    public List<PointMappingDto> mapEntitiesFrom(List<PointMapping> pointMappings) {
        List<PointMappingDto> result = null;
        if (pointMappings != null && pointMappings.isEmpty()) {
            result = new ArrayList<>();
            PointMappingDto pointMappingDto;
            for (PointMapping pointMapping : pointMappings) {
                pointMappingDto = this.mapEntityFrom(pointMapping);
                result.add(pointMappingDto);
            }
        }
        return result;
    }

    @Override
    public List<PointMapping> mapDtosFrom(List<PointMappingDto> pointMappingDtos) {
        List<PointMapping> result = null;
        if (pointMappingDtos != null && !pointMappingDtos.isEmpty()) {
            result = new ArrayList<>();
            PointMapping pointMapping;
            for (PointMappingDto pointMappingDto : pointMappingDtos) {
                pointMapping = this.mapDtoFrom(pointMappingDto);
                result.add(pointMapping);
            }
        }
        return result;
    }
}
