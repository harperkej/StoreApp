package thesis.buyproducts.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import thesis.buyproducts.entity.Customer;
import thesis.buyproducts.dto.CustomerDto;

public class CustomerMapperBean implements Mapper<Customer, CustomerDto> {

	@Override
	public CustomerDto mapVOFrom(Customer entity) {
		CustomerDto customerDto = new CustomerDto();
		if (entity == null) {
			return null;
		}
		if (entity.getId() != null) {
			customerDto.setId(entity.getId());
		}
		customerDto.setFirstName(entity.getFirstName());
		customerDto.setLastName(entity.getLastName());
		customerDto.setUserName(entity.getUserName());

		if (entity.getPoints() == null) {
			customerDto.setPoints(new Double(0));
		} else {
			customerDto.setPoints(entity.getPoints());
		}
		return customerDto;
	}

	@Override
	public Customer mapEntityFrom(CustomerDto dto) {
		Customer customer = new Customer();
		if (dto == null) {
			return null;
		}
		if (dto.getId() != null) {
			customer.setId(dto.getId());
		}
		customer.setFirstName(dto.getFirstName());
		customer.setLastName(dto.getLastName());
		customer.setUserName(dto.getUserName());
		if (dto.getPoints() == null) {
			customer.setPoints(new Double(0));
		} else {
			customer.setPoints(dto.getPoints());
		}
		return customer;
	}

	@Override
	public List<CustomerDto> mapEntityFrom(List<Customer> entityList) {
		List<CustomerDto> listOfCustomersVO = new ArrayList<CustomerDto>();
		if (entityList == null) {
			return null;
		}
		for (Customer customer : entityList) {
			CustomerDto customerDto = mapVOFrom(customer);
			listOfCustomersVO.add(customerDto);
		}
		return listOfCustomersVO;
	}

	@Override
	public List<Customer> mapVOFrom(List<CustomerDto> voList) {
		List<Customer> listOfCustomer = new ArrayList<>();
		if (voList == null) {
			return null;
		}
		for (CustomerDto customerDto : voList) {
			Customer customer = mapEntityFrom(customerDto);
			listOfCustomer.add(customer);
		}
		return listOfCustomer;
	}

}
