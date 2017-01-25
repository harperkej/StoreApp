package thesis.buyproducts.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import thesis.buyproducts.entity.PointMapping;
import thesis.buyproducts.vo.PointMappingVO;

@Component
public class PointMappingMapperBean implements Mapper<PointMapping, PointMappingVO> {

	@Override
	public PointMappingVO mapVOFrom(PointMapping entity) {
		PointMappingVO pointMappingVO = new PointMappingVO();
		if (entity == null) {
			return null;
		}
		if (entity.getId() != null) {
			pointMappingVO.setId(entity.getId());
		}
		pointMappingVO.setValue(entity.getValue());
		return pointMappingVO;
	}

	@Override
	public PointMapping mapEntityFrom(PointMappingVO vo) {
		PointMapping pointMapping = new PointMapping();
		if (vo == null) {
			return null;
		}
		if (vo.getId() != null) {
			pointMapping.setId(vo.getId());
		}
		pointMapping.setValue(vo.getValue());
		return pointMapping;
	}

	// TODO: Implement
	@Override
	public List<PointMappingVO> mapEntityFrom(List<PointMapping> entityList) {
		return null;
	}

	// TODO: Implement
	@Override
	public List<PointMapping> mapVOFrom(List<PointMappingVO> voList) {
		return null;
	}

}
