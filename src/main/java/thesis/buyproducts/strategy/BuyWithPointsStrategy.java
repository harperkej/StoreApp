package thesis.buyproducts.strategy;

import thesis.buyproducts.execption.StrategyException;
import thesis.buyproducts.vo.BuyWithPointsVO;

public interface BuyWithPointsStrategy {

	public BuyWithPointsVO processPurchaseWithPoints(String username, Double amount) throws StrategyException;

}
