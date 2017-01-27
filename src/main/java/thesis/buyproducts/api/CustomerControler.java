package thesis.buyproducts.api;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import thesis.buyproducts.execption.RestApiException;
import thesis.buyproducts.execption.ServiceException;
import thesis.buyproducts.service.CustomerService;
import thesis.buyproducts.service.BuyWithPointsService;
import thesis.buyproducts.service.PurchaseProcessorService;
import thesis.buyproducts.dto.BuyWithPointsDto;
import thesis.buyproducts.dto.CustomerAccountDto;
import thesis.buyproducts.dto.CustomerDto;

@RestController
@RequestMapping(value = "/customer")
public class CustomerControler {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PurchaseProcessorService customerStateAccountStateStrategy;

    @Autowired
    private BuyWithPointsService buyWithPointsService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto persist(@RequestBody @Valid CustomerDto customerDto) throws RestApiException {
        CustomerDto customerDtoRes;
        try {
            customerDtoRes = customerService.persist(customerDto);
        } catch (ServiceException e) {
            throw new RestApiException(e.getMessage(), e.getExceptionType());
        }
        return customerDtoRes;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto updateCustomer(@RequestBody @Valid CustomerDto customerDto) throws RestApiException {
        CustomerDto customerDtoRes;
        try {
            customerDtoRes = customerService.update(customerDto);
        } catch (ServiceException e) {
            throw new RestApiException(e.getMessage(), e.getExceptionType());
        }
        return customerDtoRes;
    }

    @ResponseStatus(code = HttpStatus.FOUND)
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto findByPK(@PathVariable("id") Long id) throws RestApiException {
        CustomerDto customerDtoRes;
        try {
            customerDtoRes = customerService.findById(id);
        } catch (ServiceException e) {
            throw new RestApiException(e.getMessage(), e.getExceptionType());
        }
        return customerDtoRes;
    }

    @ResponseStatus(code = HttpStatus.FOUND)
    @RequestMapping(value = "/username/{username}/", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto findByUserName(@PathVariable("username") String userName) throws RestApiException {
        CustomerDto customerDtoRes;
        try {
            customerDtoRes = customerService.findByUserName(userName);
        } catch (ServiceException e) {
            throw new RestApiException(e.getMessage(), e.getExceptionType());
        }
        return customerDtoRes;
    }

    @RequestMapping(value = "/username/{username}/amount/{amount}/", method = RequestMethod.PUT)
    public CustomerAccountDto processPurchase(@PathVariable("username") String username,
                                              @PathVariable("amount") @Min(value = 0) Double amount) throws RestApiException {
        CustomerAccountDto customerAccountDto;
        try {
            customerAccountDto = customerStateAccountStateStrategy.processPurchase(username, amount);
        } catch (ServiceException e) {
            throw new RestApiException(e.getMessage(), e.getExceptionType());
        }
        return customerAccountDto;
    }

    @RequestMapping(value = "/usepoints/username/{username}/amount/{amount}/", method = RequestMethod.PUT)
    public BuyWithPointsDto buyWithPoints(@PathVariable("username") String username,
                                          @PathVariable("amount") @Min(value = 0) Double amount) throws RestApiException {
        BuyWithPointsDto buyWithPointsDto;
        try {
            buyWithPointsDto = buyWithPointsService.processPurchaseWithPoints(username, amount);
        } catch (ServiceException e) {
            throw new RestApiException(e.getMessage(), e.getExceptionType());
        }
        return buyWithPointsDto;
    }

}
