package thesis.buyproducts.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thesis.buyproducts.dto.CustomerDto;
import thesis.buyproducts.execption.ExceptionType;
import thesis.buyproducts.execption.RepositoryException;
import thesis.buyproducts.execption.ServiceException;
import thesis.buyproducts.repository.CustomerRepository;

import java.util.List;

@Service
@Transactional(rollbackFor = {ServiceException.class})
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private Logger logger = Logger.getLogger(CustomerServiceImpl.class);

    @Override
    public CustomerDto persist(CustomerDto customerEntity) throws ServiceException {
        final String MN = "persist";
        logger.info(MN);
        CustomerDto result;
        try {
            result = customerRepository.persist(customerEntity);
        } catch (RepositoryException e) {
            logger.error(e);
            throw new ServiceException(e.getMessage(), e.getExceptionType());
        } catch (ServiceException e) {
            logger.error(e);
            throw new ServiceException(e.getMessage(), e.getExceptionType());
        } catch (Exception e) {
            logger.error(e);
            throw new ServiceException(e.getMessage(), ExceptionType.UNKONW_ERROR);
        }
        logger.info(MN);
        return result;
    }

    @Override
    public CustomerDto findById(Long id) throws ServiceException {
        CustomerDto result = null;
        try {
            result = customerRepository.findById(id);
            if (result == null) {
                throw new ServiceException("Nothing found with the given id : " + id, ExceptionType.NOTHING_FOUND);
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e.getExceptionType());
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getExceptionType());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), ExceptionType.UNKONW_ERROR);
        }
        return result;
    }

    @Override
    public CustomerDto update(CustomerDto customerEntity) throws ServiceException {
        CustomerDto result;
        try {
            if (customerEntity == null) {
                throw new ServiceException("Customer was null!", ExceptionType.BAD_INPUT);
            }
            result = this.findByUserName(customerEntity.getUserName());
            result.setFirstName(customerEntity.getFirstName());
            result.setLastName(customerEntity.getLastName());
            result.setPoints(customerEntity.getPoints());
            result.setUserName(customerEntity.getUserName());
            result = customerRepository.update(result);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e.getExceptionType());
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getExceptionType());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), ExceptionType.UNKONW_ERROR);
        }
        return result;
    }

    @Override
    public CustomerDto findByUserName(String userName) throws ServiceException {
        List<CustomerDto> resultList;
        try {
            resultList = customerRepository.findByUsername(userName);
            if (resultList == null || resultList.isEmpty()) {
                throw new ServiceException("No user found with given user name : " + userName, ExceptionType.NOTHING_FOUND);
            }
            if (resultList.size() > 1) {
                throw new ServiceException("More than two users found with the same user name : " + userName, ExceptionType.NOTHING_FOUND);
            }
        } catch (RepositoryException e) {

            throw new ServiceException(e.getMessage(), e.getExceptionType());
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getExceptionType());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), ExceptionType.UNKONW_ERROR);
        }
        return resultList.get(0);
    }

}
