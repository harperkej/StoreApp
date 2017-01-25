package thesis.buyproducts.service;

import thesis.buyproducts.entity.Customer;
import thesis.buyproducts.execption.ServiceException;

public interface CustomerService {

	public Customer persist(Customer customer) throws ServiceException;

	public Customer findById(Long id) throws ServiceException;

	public Customer update(Customer customer) throws ServiceException;

	public Customer findByUserName(String userName) throws ServiceException;

}
