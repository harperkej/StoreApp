package thesis.buyproducts.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import thesis.buyproducts.dto.BuyWithPointsDto;
import thesis.buyproducts.dto.CustomerAccountDto;
import thesis.buyproducts.dto.CustomerDto;
import thesis.buyproducts.execption.RestApiException;
import thesis.buyproducts.execption.ServiceException;
import thesis.buyproducts.service.BuyWithPointsService;
import thesis.buyproducts.service.CustomerService;
import thesis.buyproducts.service.PurchaseProcessorService;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@RestController
@RequestMapping(value = "api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PurchaseProcessorService purchaseProcessorService;

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
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/username/{username}/amount/{amount}/points", method = RequestMethod.PUT)
    public CustomerAccountDto processPurchase(@PathVariable("username") String username,
                                              @PathVariable("amount") @Min(value = 0) Double amount) throws RestApiException {
        CustomerAccountDto customerAccountDtoRes;
        try {
            customerAccountDtoRes = purchaseProcessorService.processPurchase(username, amount);
        } catch (ServiceException e) {
            throw new RestApiException(e.getMessage(), e.getExceptionType());
        }
        return customerAccountDtoRes;
    }

    @RequestMapping(value = "/username/{username}/amount/{amount}/discount", method = RequestMethod.PUT)
    public BuyWithPointsDto buyWithPoints(@PathVariable("username") String username,
                                          @PathVariable("amount") @Min(value = 0) Double amount) throws RestApiException {
        BuyWithPointsDto buyWithPointsDtoRes;
        try {
            buyWithPointsDtoRes = buyWithPointsService.processPurchaseWithPoints(username, amount);
        } catch (ServiceException e) {
            throw new RestApiException(e.getMessage(), e.getExceptionType());
        }
        return buyWithPointsDtoRes;
    }

}
