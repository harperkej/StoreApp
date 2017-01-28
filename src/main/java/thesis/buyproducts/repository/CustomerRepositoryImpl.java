package thesis.buyproducts.repository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import thesis.buyproducts.dto.CustomerDto;
import thesis.buyproducts.entity.CustomerEntity;
import thesis.buyproducts.execption.ExceptionType;
import thesis.buyproducts.execption.RepositoryException;
import thesis.buyproducts.mapper.CustomerMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private static final String GET_CUSTOMER_BY_USERNAME_NAMED_QUERY = "findCustomerByUsername";
    @PersistenceContext
    private EntityManager entityManager;

    public CustomerDto persist(CustomerDto customerDto) throws RepositoryException {
        CustomerDto customerDtoRes;
        CustomerEntity customerEntity;
        try {
            customerEntity = CustomerMapper.getInstance().mapDtoFrom(customerDto);
            //When the customer is created for the first time, he/she has zero points.
            customerEntity.setPoints(new Double(0));
            entityManager.persist(customerEntity);
            customerDtoRes = CustomerMapper.getInstance().mapEntityFrom(customerEntity);
        } catch (Exception e) {
            throw new RepositoryException("Error while trying to save resouce", ExceptionType.UNKONW_ERROR);
        }
        return customerDtoRes;
    }

    @Override
    public CustomerDto findById(Long id) throws RepositoryException {
        final String MN = "findById";
        CustomerEntity customerResult;
        CustomerDto customerDto;
        try {
            customerResult = entityManager.find(CustomerEntity.class, id);
            customerDto = CustomerMapper.getInstance().mapEntityFrom(customerResult);
        } catch (Exception e) {
            throw new RepositoryException("Something bad happend while searching for user with id : " + id, ExceptionType.NOTHING_FOUND);
        }
        return customerDto;
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) throws RepositoryException {
        CustomerEntity customerEntity;
        CustomerDto customerDtoRes;
        try {
            customerEntity = entityManager.merge(CustomerMapper.getInstance().mapDtoFrom(customerDto));
            customerDtoRes = CustomerMapper.getInstance().mapEntityFrom(customerEntity);
        } catch (DataIntegrityViolationException e) {
            throw new RepositoryException("User name: [" + customerDto.getUserName() + "] not unique! :-(", ExceptionType.ERORR_UPDATING);
        } catch (Exception e) {
            throw new RepositoryException("Something bad happened! :-(", ExceptionType.UNKONW_ERROR);
        }
        return customerDtoRes;
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
            throw new RepositoryException(e.getMessage(), ExceptionType.NOTHING_FOUND);
        }
        return customerDtoList;
    }
}
