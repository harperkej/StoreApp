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

import thesis.buyproducts.mapper.PointMapper;
import thesis.buyproducts.service.PointMappingService;
import thesis.buyproducts.dto.PointMappingDto;

@RestController
@RequestMapping(value = "/pointmapping")
public class PointMappingControler {

    @Autowired
    private PointMappingService pointMappingService;

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean updatePointMapper(@RequestBody @Valid PointMappingDto pointMapperVO) {
        return pointMappingService.updatePointMapper(PointMapper.getInstance().mapDtoFrom(pointMapperVO));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Double getPointMapper() {
        return pointMappingService.getPointMapper();
    }

}
