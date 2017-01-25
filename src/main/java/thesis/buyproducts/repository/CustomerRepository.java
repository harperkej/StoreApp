package thesis.buyproducts.repository;

import thesis.buyproducts.entity.Customer;
import thesis.buyproducts.execption.RepositoryException;

public interface CustomerRepository {

	public Customer persist(Customer customer) throws RepositoryException;

	public Customer findById(Long id) throws RepositoryException;

	public Customer update(Customer customerVO) throws RepositoryException;
	
	public Customer findByUsername(String username) throws RepositoryException;
}
