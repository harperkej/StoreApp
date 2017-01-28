package thesis.buyproducts.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import thesis.buyproducts.dto.PointMappingDto;
import thesis.buyproducts.execption.ExceptionType;
import thesis.buyproducts.execption.RestApiException;
import thesis.buyproducts.execption.ServiceException;
import thesis.buyproducts.service.PointMappingService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/pointmapper")
public class PointMappingController {

    @Autowired
    private PointMappingService pointMappingService;

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean updatePointMapper(@RequestBody @Valid PointMappingDto pointMapperDto) throws RestApiException {
        boolean result;
        try {
            result = pointMappingService.updatePointMapper(pointMapperDto);
        } catch (ServiceException e) {
            throw new RestApiException(e.getMessage(), ExceptionType.UNKONW_ERROR);
        }
        return result;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Double getPointMapper() throws RestApiException {
        Double result;
        try {
            result = pointMappingService.getPointMapper();
        } catch (ServiceException e) {
            throw new RestApiException(e.getMessage(), ExceptionType.UNKONW_ERROR);
        }
        return result;
    }

}
