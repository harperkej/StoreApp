package thesis.buyproducts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import thesis.buyproducts.entity.Customer;
import thesis.buyproducts.execption.RepositoryException;
import thesis.buyproducts.execption.ServiceException;
import thesis.buyproducts.execption.domaintype.DaoExceptionType;
import thesis.buyproducts.repository.CustomerRepository;

@Service
@Transactional(rollbackFor = { ServiceException.class })
public class CustomerServiceBean implements CustomerService {

	@Autowired
	private CustomerRepository customerDao;

	public Customer persist(Customer customer) throws ServiceException {
		try {
			return customerDao.persist(customer);
		} catch (RepositoryException e) {
			if (e.getDaoExceptionType() == DaoExceptionType.USERNAME_NOT_UNIQUE) {
				throw ServiceException.userNameNotUnique(e.getMessage());
			} else if (e.getDaoExceptionType() == DaoExceptionType.ERROR_PERSISTING) {
				throw ServiceException.errorPersisting(e.getMessage());
			}
			return null;
		}
	}

	@Override
	public Customer findById(Long id) throws ServiceException {
		try {
			return customerDao.findById(id);
		} catch (RepositoryException e) {
			throw ServiceException.notFound(e.getMessage());
		}
	}

	@ResponseStatus(value = HttpStatus.OK)
	@Override
	public Customer update(Customer customer) throws ServiceException {
		try {
			Customer foundCustomer = customerDao.findByUsername(customer.getUserName());
			foundCustomer.setFirstName(customer.getFirstName());
			foundCustomer.setLastName(customer.getLastName());
			foundCustomer.setPoints(customer.getPoints());
			foundCustomer.setUserName(customer.getUserName());
			return customerDao.update(foundCustomer);
		} catch (RepositoryException e) {
			if (e.getDaoExceptionType() == DaoExceptionType.USERNAME_NOT_UNIQUE) {
				throw ServiceException.userNameNotUnique(e.getMessage());
			} else if (e.getDaoExceptionType() == DaoExceptionType.NOT_FOUND) {
				throw ServiceException.notFound(e.getMessage());
			} else {
				throw ServiceException.errorUpdating(e.getMessage());
			}
		}
	}

	@Override
	public Customer findByUserName(String userName) throws ServiceException {
		try {
			return customerDao.findByUsername(userName);
		} catch (RepositoryException e) {
			throw ServiceException.notFound(e.getMessage());
		}
	}

}
