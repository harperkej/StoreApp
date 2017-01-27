package thesis.buyproducts.service;

import thesis.buyproducts.dto.BuyWithPointsDto;
import thesis.buyproducts.execption.ServiceException;

public interface BuyWithPointsService {

    public BuyWithPointsDto processPurchaseWithPoints(String username, Double amount) throws ServiceException;

}
