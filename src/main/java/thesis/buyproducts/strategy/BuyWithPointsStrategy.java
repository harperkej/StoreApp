package thesis.buyproducts.strategy;

import thesis.buyproducts.execption.StrategyException;
import thesis.buyproducts.dto.BuyWithPointsDto;

public interface BuyWithPointsStrategy {

	public BuyWithPointsDto processPurchaseWithPoints(String username, Double amount) throws StrategyException;

}
