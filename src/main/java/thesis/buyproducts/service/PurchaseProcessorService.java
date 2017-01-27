package thesis.buyproducts.service;

import thesis.buyproducts.dto.CustomerAccountDto;
import thesis.buyproducts.execption.ServiceException;

public interface PurchaseProcessorService {

    public CustomerAccountDto processPurchase(String username, Double amount)
            throws ServiceException;

}
