package thesis.buyproducts.strategy;

import thesis.buyproducts.execption.StrategyException;
import thesis.buyproducts.dto.CustomerAccountDto;

public interface PurchaseProcessorStrategy {

	public CustomerAccountDto processPurchase(String username, Double amount)
			throws StrategyException;

}
