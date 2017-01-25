package thesis.buyproducts.strategy;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thesis.buyproducts.entity.Customer;
import thesis.buyproducts.execption.RepositoryException;
import thesis.buyproducts.execption.StrategyException;
import thesis.buyproducts.execption.domaintype.DaoExceptionType;
import thesis.buyproducts.execption.domaintype.StrategyExceptionType;
import thesis.buyproducts.repository.CustomerRepository;
import thesis.buyproducts.util.PointToAmountAndViceVersaConvertor;
import thesis.buyproducts.vo.BuyWithPointsVO;

@Component
@Transactional
public class BuyWithPointsStrategyBean implements BuyWithPointsStrategy {

	@Autowired
	private CustomerRepository customerDao;

	@Autowired
	private PointToAmountAndViceVersaConvertor pointToAmountAndViceVersaConvertor;

	@Override
	public BuyWithPointsVO processPurchaseWithPoints(String username, Double amount) throws StrategyException {
		BuyWithPointsVO purchaseResult = new BuyWithPointsVO();
		try {
			Customer customer = customerDao.findByUsername(username);
			Double pointsOfTheCostumer = customer.getPoints();
			if (pointsOfTheCostumer == null) {
				pointsOfTheCostumer = new Double(0);
			}
			Double pointsBasedOnAmount = pointToAmountAndViceVersaConvertor.validateAmount(amount);
			if (pointsBasedOnAmount.doubleValue() == -1) {
				throw StrategyException.amountNotValid("Amount : " + amount + " not valid amount :-(");
			}
			if (pointsOfTheCostumer.doubleValue() == pointsBasedOnAmount.doubleValue()) {
				pointsOfTheCostumer = new Double(0);
				customer.setPoints(pointsOfTheCostumer);
				customerDao.update(customer);
				purchaseResult.setFirstName(customer.getFirstName());
				purchaseResult.setLastName(customer.getLastName());
				purchaseResult.setUsername(customer.getUserName());
				purchaseResult.setHasToPay(new Double(0));
				purchaseResult.setPointsLeft(customer.getPoints());
			} else if (pointsOfTheCostumer > pointsBasedOnAmount) {
				pointsOfTheCostumer = pointsOfTheCostumer - pointsBasedOnAmount;
				customer.setPoints(pointsOfTheCostumer);
				customerDao.update(customer);
				purchaseResult.setFirstName(customer.getFirstName());
				purchaseResult.setLastName(customer.getLastName());
				purchaseResult.setUsername(customer.getUserName());
				purchaseResult.setHasToPay(new Double(0));
				purchaseResult.setPointsLeft(customer.getPoints());
			} else if (pointsOfTheCostumer < pointsBasedOnAmount) {
				customer.setPoints(new Double(0));
				customerDao.update(customer);
				purchaseResult.setFirstName(customer.getFirstName());
				purchaseResult.setFirstName(customer.getFirstName());
				purchaseResult.setLastName(customer.getLastName());
				purchaseResult.setUsername(customer.getUserName());
				purchaseResult.setPointsLeft(customer.getPoints());
				Double pointsToConvertInAmount = pointsBasedOnAmount - pointsOfTheCostumer;
				Double amountToPa = pointToAmountAndViceVersaConvertor.validateAmount(pointsToConvertInAmount);
				if (amountToPa == -1) {
					throw StrategyException.errorProcessingPoints("Error processing points! :-(. Try again.");
				}
				purchaseResult.setHasToPay(amountToPa);
			}

		} catch (RepositoryException exception) {
			if (exception.getDaoExceptionType() == DaoExceptionType.NOT_FOUND) {
				throw StrategyException.couldNotFoundCustomer(exception.getMessage());
			} else if (exception.getDaoExceptionType() == DaoExceptionType.ERROR_UPDATING) {
				throw StrategyException.errorUpdatingCustomer(exception.getMessage());
			}
		} catch (StrategyException exception) {
			if (exception.getExceptionType() == StrategyExceptionType.AMOUNT_NOT_VALID) {
				throw StrategyException.amountNotValid(exception.getMessage());
			} else if (exception.getExceptionType() == StrategyExceptionType.ERROR_PROCESSING_POINTS) {
				throw StrategyException.errorProcessingPoints(exception.getMessage());
			}
		}
		return purchaseResult;
	}

}
