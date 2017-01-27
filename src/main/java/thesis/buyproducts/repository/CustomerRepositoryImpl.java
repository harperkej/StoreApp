package thesis.buyproducts.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import thesis.buyproducts.dto.CustomerDto;
import thesis.buyproducts.entity.CustomerEntity;
import thesis.buyproducts.execption.ExceptionType;
import thesis.buyproducts.execption.RepositoryException;
import thesis.buyproducts.mapper.CustomerMapper;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String GET_CUSTOMER_BY_USERNAME_NAMED_QUERY = "findCustomerByUsername";

    private final Logger logger = Logger.getLogger(CustomerRepositoryImpl.class);

    public CustomerDto persist(CustomerDto customerDto) throws RepositoryException {
        final String MN = "persist";
        logger.info(MN);
        CustomerDto customerDtoRes;
        CustomerEntity customerEntity;
        try {
            customerEntity = CustomerMapper.getInstance().mapDtoFrom(customerDto);
            entityManager.persist(customerEntity);
            customerDtoRes = CustomerMapper.getInstance().mapEntityFrom(customerEntity);
        } catch (Exception e) {
            logger.error(e);
            throw new RepositoryException("Error while trying to save resouce", ExceptionType.UNKONW_ERROR);
        }
        logger.info(MN);
        return customerDtoRes;
    }

    @Override
    public CustomerDto findById(Long id) throws RepositoryException {
        final String MN = "findById";
        logger.info(MN);
        CustomerEntity customerResult;
        CustomerDto customerDto;
        try {
            customerResult = entityManager.find(CustomerEntity.class, id);
            customerDto = CustomerMapper.getInstance().mapEntityFrom(customerResult);
        } catch (Exception e) {
            logger.error(e);
            throw new RepositoryException("Something bad happend while searching for user with id : " + id, ExceptionType.NOTHING_FOUND);
        }
        logger.info(MN);
        return customerDto;
    }

    @Override
    public CustomerDto update(CustomerDto customerEntity) throws RepositoryException {
        final String MN = "update";
        logger.info(MN);
        CustomerEntity customerResult;
        CustomerDto customerDto;
        try {
            customerResult = entityManager.merge(CustomerMapper.getInstance().mapDtoFrom(customerEntity));
            customerDto = CustomerMapper.getInstance().mapEntityFrom(customerResult);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryException("User name: [" + customerEntity.getUserName() + "] not unique! :-(", ExceptionType.ERORR_UPDATING);
        } catch (Exception e) {
            logger.error(e);
            throw new RepositoryException("Something bad happened! :-(", ExceptionType.UNKONW_ERROR);
        }
        logger.info(MN);
        return customerDto;
    }

    @Override
    public List<CustomerDto> findByUsername(String username) throws RepositoryException {
        List<CustomerEntity> customerEntities;
        List<CustomerDto> customerDtoList;
        try {
            TypedQuery<CustomerEntity> query = entityManager.createNamedQuery(GET_CUSTOMER_BY_USERNAME_NAMED_QUERY,
                    CustomerEntity.class);
            query.setParameter(1, username);
            customerEntities = query.getResultList();
            customerDtoList = CustomerMapper.getInstance().mapEntitiesFrom(customerEntities);
        } catch (Exception e) {
            logger.error(e);
            throw new RepositoryException(e.getMessage(), ExceptionType.NOTHING_FOUND);
        }
        return customerDtoList;
    }
}
