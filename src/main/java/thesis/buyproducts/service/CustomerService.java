package thesis.buyproducts.service;

import thesis.buyproducts.dto.CustomerDto;
import thesis.buyproducts.execption.ServiceException;

public interface CustomerService {

    public CustomerDto persist(CustomerDto customerEntity) throws ServiceException;

    public CustomerDto findById(Long id) throws ServiceException;

    public CustomerDto update(CustomerDto customerEntity) throws ServiceException;

    public CustomerDto findByUserName(String userName) throws ServiceException;

}
