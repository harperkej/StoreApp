package thesis.buyproducts.mapper;

import java.util.ArrayList;
import java.util.List;

import thesis.buyproducts.entity.Customer;
import thesis.buyproducts.dto.CustomerDto;

public final class CustomerMapper implements Mapper<Customer, CustomerDto> {

    /**
     * Do not initialize me please!
     */
    private CustomerMapper() {

    }

    public static CustomerMapper getInstance() {
        return new CustomerMapper();
    }


    @Override
    public CustomerDto mapEntityFrom(Customer customer) {
        CustomerDto customerDto = null;
        if (customer != null) {
            customerDto = new CustomerDto();
            if (customer.getId() != null) {
                customerDto.setId(customer.getId());
            }
            customerDto.setFirstName(customer.getFirstName());
            customerDto.setLastName(customer.getLastName());
            customerDto.setPoints(customer.getPoints() != null ? customer.getPoints() : 0);
            customerDto.setUserName(customer.getUserName());
        }
        return customerDto;
    }

    @Override
    public Customer mapDtoFrom(CustomerDto customerDto) {
        Customer customer = null;
        if (customerDto != null) {
            customer = new Customer();
            if (customerDto.getId() != null) {
                customer.setId(customerDto.getId());
            }
            customer.setUserName(customerDto.getUserName());
            customer.setPoints(customerDto.getPoints() != null ? customerDto.getPoints() : 0);
            customer.setLastName(customerDto.getLastName());
            customer.setFirstName(customerDto.getFirstName());
        }
        return customer;
    }

    @Override
    public List<CustomerDto> mapEntitiesFrom(List<Customer> customers) {
        List<CustomerDto> result = null;
        if (customers != null && !customers.isEmpty()) {
            result = new ArrayList<>();
            CustomerDto customerDto;
            for (Customer customer : customers) {
                customerDto = this.mapEntityFrom(customer);
                result.add(customerDto);
            }
        }
        return result;
    }

    @Override
    public List<Customer> mapDtosFrom(List<CustomerDto> dtoList) {
        List<Customer> result = null;
        if (dtoList != null && !dtoList.isEmpty()) {
            result = new ArrayList<>();
            Customer customer;
            for (CustomerDto customerDto : dtoList) {
                customer = this.mapDtoFrom(customerDto);
                result.add(customer);
            }
        }
        return result;
    }
}
