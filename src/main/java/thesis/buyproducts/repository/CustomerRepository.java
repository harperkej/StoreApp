package thesis.buyproducts.repository;

import thesis.buyproducts.dto.CustomerDto;
import thesis.buyproducts.entity.CustomerEntity;
import thesis.buyproducts.execption.RepositoryException;

import java.util.List;

public interface CustomerRepository {

    public CustomerDto persist(CustomerDto customerDto) throws RepositoryException;

    public CustomerDto findById(Long id) throws RepositoryException;

    public CustomerDto update(CustomerDto customerDto) throws RepositoryException;

    public List<CustomerDto> findByUsername(String username) throws RepositoryException;
}
