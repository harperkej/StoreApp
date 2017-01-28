package thesis.buyproducts.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thesis.buyproducts.dto.CustomerDto;
import thesis.buyproducts.execption.ExceptionType;
import thesis.buyproducts.execption.RepositoryException;
import thesis.buyproducts.execption.ServiceException;
import thesis.buyproducts.repository.CustomerRepository;
import thesis.buyproducts.util.ConvertUtil;
import thesis.buyproducts.dto.BuyWithPointsDto;

@Component
@Transactional
public class BuyWithPointsServiceImpl implements BuyWithPointsService {

    @Autowired
    private CustomerRepository customerepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ConvertUtil convertUtil;

    @Override
    public BuyWithPointsDto processPurchaseWithPoints(String username, Double amount) throws ServiceException {
        BuyWithPointsDto purchaseResult = null;
        try {
            CustomerDto customerDto = customerService.findByUserName(username);
            Double pointsOfTheCostumer = customerDto.getPoints();
            if (pointsOfTheCostumer == null) {
                pointsOfTheCostumer = new Double(0);
            }
            Double pointsBasedOnAmount = convertUtil.validateAmount(amount);
            if (new Double(-1).equals(pointsBasedOnAmount)) {
                throw new ServiceException("Amount : " + amount + " not valid amount :-(", ExceptionType.BAD_INPUT);
            }

            if (pointsOfTheCostumer.doubleValue() == pointsBasedOnAmount.doubleValue()) {
                pointsOfTheCostumer = new Double(0);
                customerDto.setPoints(pointsOfTheCostumer);
                customerepository.update(customerDto);
                purchaseResult = new BuyWithPointsDto();
                purchaseResult.setFirstName(customerDto.getFirstName());
                purchaseResult.setLastName(customerDto.getLastName());
                purchaseResult.setUsername(customerDto.getUserName());
                purchaseResult.setHasToPay(new Double(0));
                purchaseResult.setPointsLeft(customerDto.getPoints());
            } else if (pointsOfTheCostumer > pointsBasedOnAmount) {
                pointsOfTheCostumer = pointsOfTheCostumer - pointsBasedOnAmount;
                customerDto.setPoints(pointsOfTheCostumer);
                customerepository.update(customerDto);
                purchaseResult.setFirstName(customerDto.getFirstName());
                purchaseResult.setLastName(customerDto.getLastName());
                purchaseResult.setUsername(customerDto.getUserName());
                purchaseResult.setHasToPay(new Double(0));
                purchaseResult.setPointsLeft(customerDto.getPoints());
            } else if (pointsOfTheCostumer < pointsBasedOnAmount) {
                customerDto.setPoints(new Double(0));
                customerepository.update(customerDto);
                purchaseResult.setFirstName(customerDto.getFirstName());
                purchaseResult.setFirstName(customerDto.getFirstName());
                purchaseResult.setLastName(customerDto.getLastName());
                purchaseResult.setUsername(customerDto.getUserName());
                purchaseResult.setPointsLeft(customerDto.getPoints());
                Double pointsToConvertInAmount = pointsBasedOnAmount - pointsOfTheCostumer;
                Double amountToPay = convertUtil.validateAmount(pointsToConvertInAmount);
                purchaseResult.setHasToPay(amountToPay);
            }

        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e.getExceptionType());
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getExceptionType());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), ExceptionType.UNKONW_ERROR);
        }
        return purchaseResult;
    }

}
