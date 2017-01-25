package thesis.buyproducts.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import thesis.buyproducts.entity.Customer;
import thesis.buyproducts.execption.RepositoryException;

@Repository
public class CustomerRepositoryBean implements CustomerRepository {

	@PersistenceContext
	private EntityManager entityManager;

	private static final String GET_CUSTOMER_BY_USERNAME_NAMED_QUERY = "findCustomerByUsername";

	public Customer persist(Customer customer) throws RepositoryException {
		try {
			entityManager.persist(customer);
			return customer;
		} catch (PersistenceException e) {
			throw RepositoryException.userNameNotUnique("User name : [" + customer.getUserName() + "] not unique");
		} catch (Exception e) {
			throw RepositoryException.errorPersisting("Error while trying to save resouce");
		}
	}

	@Override
	public Customer findById(Long id) throws RepositoryException {
		try {
			Customer customer = entityManager.find(Customer.class, id);
			if (customer == null) {
				throw RepositoryException.notFound("Customer with id: " + id + " not found :-(");
			}
			return customer;
		} catch (Exception e) {
			throw RepositoryException.notFound("Something bad happend while searching for user with id : " + id);
		}
	}

	@Override
	public Customer update(Customer customer) throws RepositoryException {
		try {
			return entityManager.merge(customer);
		} catch (DataIntegrityViolationException e) {
			throw RepositoryException.userNameNotUnique("User name: [" + customer.getUserName() + "] not unique! :-(");
		} catch (Exception e) {
			throw RepositoryException.errorUpdating("Something bad happened! :-(");
		}
	}

	@Override
	public Customer findByUsername(String username) throws RepositoryException {
		TypedQuery<Customer> query = entityManager.createNamedQuery(GET_CUSTOMER_BY_USERNAME_NAMED_QUERY,
				Customer.class);
		query.setParameter(1, username);
		List<Customer> customer = query.getResultList();
		if (customer == null) {
			throw RepositoryException.notFound("Customer with username : " + username + " not found!");
		}
		if (customer.isEmpty()) {
			throw RepositoryException.notFound("Customer with username : " + username + " not found!");
		}
		return customer.get(0);
	}

}
