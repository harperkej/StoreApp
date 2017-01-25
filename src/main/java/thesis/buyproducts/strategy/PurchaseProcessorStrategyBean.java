package thesis.buyproducts.strategy;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thesis.buyproducts.entity.Customer;
import thesis.buyproducts.execption.RepositoryException;
import thesis.buyproducts.execption.StrategyException;
import thesis.buyproducts.execption.domaintype.DaoExceptionType;
import thesis.buyproducts.repository.CustomerRepository;
import thesis.buyproducts.util.ConvertUtil;
import thesis.buyproducts.dto.CustomerAccountDto;

@Component
@Transactional
public class PurchaseProcessorStrategyBean implements PurchaseProcessorStrategy {

	@Autowired
	private CustomerRepository customerDao;

	@Autowired
	private ConvertUtil convertUtil;

	@Override
	public CustomerAccountDto processPurchase(String username, Double amount) throws StrategyException {
		try {
			Customer customer = customerDao.findByUsername(username);
			Double currentPointsOfUser = customer.getPoints();
			Double pointsOfThisPurchase = convertUtil.validateAndConvertAmountToPoints(amount);
			if (pointsOfThisPurchase.doubleValue() == -1) {
				throw StrategyException.amountNotValid("Amount : " + amount.toString() + " not valid :-(");
			}
			if (currentPointsOfUser == null) {
				currentPointsOfUser = new Double(0);
			}
			double totalOfPoints = currentPointsOfUser.doubleValue() + pointsOfThisPurchase;
			customer.setPoints(new Double(totalOfPoints));
			customerDao.update(customer);
			CustomerAccountDto customerStateAccount = new CustomerAccountDto();
			customerStateAccount.setFirstName(customer.getFirstName());
			customerStateAccount.setLastName(customer.getLastName());
			customerStateAccount.setUserName(customer.getUserName());
			customerStateAccount.setPoints(customer.getPoints());
			return customerStateAccount;
		} catch (RepositoryException e) {
			if (e.getDaoExceptionType() == DaoExceptionType.NOT_FOUND) {
				throw StrategyException.couldNotFoundCustomer("Could not find customer with username : " + username);
			} else if (e.getDaoExceptionType() == DaoExceptionType.ERROR_UPDATING) {
				throw StrategyException.errorUpdatingCustomer("Error while updating the customer : " + username);
			}
		} catch (StrategyException exception) {
			throw StrategyException.amountNotValid(exception.getMessage());
		}

		return null;
	}

}
