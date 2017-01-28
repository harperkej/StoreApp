package thesis.buyproducts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.buyproducts.dto.CustomerAccountDto;
import thesis.buyproducts.dto.CustomerDto;
import thesis.buyproducts.execption.ExceptionType;
import thesis.buyproducts.execption.RepositoryException;
import thesis.buyproducts.execption.ServiceException;
import thesis.buyproducts.repository.CustomerRepository;
import thesis.buyproducts.util.ConvertUtil;

import javax.transaction.Transactional;

@Component
@Transactional(rollbackOn = {RepositoryException.class, ServiceException.class, Exception.class})
public class PurchaseProcessorServiceImpl implements PurchaseProcessorService {

    @Autowired
    private CustomerRepository customerDao;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ConvertUtil convertUtil;

    @Override
    public CustomerAccountDto processPurchase(String username, Double amount) throws ServiceException {
        CustomerAccountDto customerAccountDto;
        try {
            CustomerDto customerEntity = customerService.findByUserName(username);
            Double currentPointsOfUser = customerEntity.getPoints();
            Double pointsOfThisPurchase = convertUtil.validateAndConvertAmountToPoints(amount);
            if (pointsOfThisPurchase.doubleValue() == -1) {
                throw new ServiceException("Amount : " + amount.toString() + " not valid :-(", ExceptionType.BAD_INPUT);
            }
            if (currentPointsOfUser == null) {
                currentPointsOfUser = new Double(0);
            }
            double totalOfPoints = currentPointsOfUser.doubleValue() + pointsOfThisPurchase;
            customerEntity.setPoints(new Double(totalOfPoints));
            customerDao.update(customerEntity);
            customerAccountDto = new CustomerAccountDto();
            customerAccountDto.setFirstName(customerEntity.getFirstName());
            customerAccountDto.setLastName(customerEntity.getLastName());
            customerAccountDto.setUserName(customerEntity.getUserName());
            customerAccountDto.setPoints(customerEntity.getPoints());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e.getExceptionType());
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getExceptionType());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), ExceptionType.UNKONW_ERROR);
        }
        return customerAccountDto;
    }

}
