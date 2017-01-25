package thesis.buyproducts.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import thesis.buyproducts.mapper.PointMappingMapperBean;
import thesis.buyproducts.service.PointMappingService;
import thesis.buyproducts.vo.PointMappingVO;

@RestController
@RequestMapping(value = "/pointmapping")
public class PointMappingControler {

	@Autowired
	private PointMappingService pointMappingService;

	@Autowired
	private PointMappingMapperBean pointMappingMapperBean;

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean updatePointMapper(@RequestBody @Valid PointMappingVO pointMapperVO) {
		return pointMappingService.updatePointMapper(pointMappingMapperBean.mapEntityFrom(pointMapperVO));
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Double getPointMapper() {
		return pointMappingService.getPointMapper();
	}

}
