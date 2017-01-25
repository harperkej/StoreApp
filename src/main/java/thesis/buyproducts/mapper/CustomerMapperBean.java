package thesis.buyproducts.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import thesis.buyproducts.entity.Customer;
import thesis.buyproducts.vo.CustomerVO;

@Component
@Qualifier(value = "Customer")
public class CustomerMapperBean implements Mapper<Customer, CustomerVO> {

	@Override
	public CustomerVO mapVOFrom(Customer entity) {
		CustomerVO customerVO = new CustomerVO();
		if (entity == null) {
			return null;
		}
		if (entity.getId() != null) {
			customerVO.setId(entity.getId());
		}
		customerVO.setFirstName(entity.getFirstName());
		customerVO.setLastName(entity.getLastName());
		customerVO.setUserName(entity.getUserName());

		if (entity.getPoints() == null) {
			customerVO.setPoints(new Double(0));
		} else {
			customerVO.setPoints(entity.getPoints());
		}
		return customerVO;
	}

	@Override
	public Customer mapEntityFrom(CustomerVO vo) {
		Customer customer = new Customer();
		if (vo == null) {
			return null;
		}
		if (vo.getId() != null) {
			customer.setId(vo.getId());
		}
		customer.setFirstName(vo.getFirstName());
		customer.setLastName(vo.getLastName());
		customer.setUserName(vo.getUserName());
		if (vo.getPoints() == null) {
			customer.setPoints(new Double(0));
		} else {
			customer.setPoints(vo.getPoints());
		}
		return customer;
	}

	@Override
	public List<CustomerVO> mapEntityFrom(List<Customer> entityList) {
		List<CustomerVO> listOfCustomersVO = new ArrayList<CustomerVO>();
		if (entityList == null) {
			return null;
		}
		for (Customer customer : entityList) {
			CustomerVO customerVO = mapVOFrom(customer);
			listOfCustomersVO.add(customerVO);
		}
		return listOfCustomersVO;
	}

	@Override
	public List<Customer> mapVOFrom(List<CustomerVO> voList) {
		List<Customer> listOfCustomer = new ArrayList<>();
		if (voList == null) {
			return null;
		}
		for (CustomerVO customerVO : voList) {
			Customer customer = mapEntityFrom(customerVO);
			listOfCustomer.add(customer);
		}
		return listOfCustomer;
	}

}
