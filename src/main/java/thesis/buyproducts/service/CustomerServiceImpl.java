package thesis.buyproducts.service;

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
@Transactional(rollbackFor = {RepositoryException.class, ServiceException.class, Exception.class})
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public CustomerDto persist(CustomerDto customerEntity) throws ServiceException {
        CustomerDto result;
        try {
            result = customerRepository.persist(customerEntity);
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
    public CustomerDto findById(Long id) throws ServiceException {
        CustomerDto result;
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
    public CustomerDto update(CustomerDto customerDto) throws ServiceException {
        CustomerDto result;
        try {
            if (customerDto == null) {
                throw new ServiceException("Customer was null!", ExceptionType.BAD_INPUT);
            }
            result = this.findByUserName(customerDto.getUserName());
            result.setFirstName(customerDto.getFirstName());
            result.setLastName(customerDto.getLastName());
            result.setPoints(customerDto.getPoints());
            result.setUserName(customerDto.getUserName());
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
