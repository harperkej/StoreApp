package thesis.buyproducts.strategy;

import thesis.buyproducts.execption.StrategyException;
import thesis.buyproducts.vo.CustomerStateAccountVO;

public interface PurchaseProcessorStrategy {

	public CustomerStateAccountVO processPurchase(String username, Double amount)
			throws StrategyException;

}
