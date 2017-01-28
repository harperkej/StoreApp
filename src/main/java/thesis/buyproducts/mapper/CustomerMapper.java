package thesis.buyproducts.mapper;

import thesis.buyproducts.dto.CustomerDto;
import thesis.buyproducts.entity.CustomerEntity;

import java.util.ArrayList;
import java.util.List;

public final class CustomerMapper implements Mapper<CustomerEntity, CustomerDto> {

    /**
     * Do not initialize me please!
     */
    private CustomerMapper() {

    }

    public static CustomerMapper getInstance() {
        return new CustomerMapper();
    }

    @Override
    public CustomerDto mapEntityFrom(CustomerEntity customerEntity) {
        CustomerDto customerDto = null;
        if (customerEntity != null) {
            customerDto = new CustomerDto();
            if (customerEntity.getId() != null) {
                customerDto.setId(customerEntity.getId());
            }
            customerDto.setFirstName(customerEntity.getFirstName());
            customerDto.setLastName(customerEntity.getLastName());
            customerDto.setPoints(customerEntity.getPoints() != null ? customerEntity.getPoints() : 0);
            customerDto.setUserName(customerEntity.getUserName());
        }
        return customerDto;
    }

    @Override
    public CustomerEntity mapDtoFrom(CustomerDto customerDto) {
        CustomerEntity customerEntity = null;
        if (customerDto != null) {
            customerEntity = new CustomerEntity();
            if (customerDto.getId() != null) {
                customerEntity.setId(customerDto.getId());
            }
            customerEntity.setUserName(customerDto.getUserName());
            customerEntity.setPoints(customerDto.getPoints() != null ? customerDto.getPoints() : 0);
            customerEntity.setLastName(customerDto.getLastName());
            customerEntity.setFirstName(customerDto.getFirstName());
        }
        return customerEntity;
    }

    @Override
    public List<CustomerDto> mapEntitiesFrom(List<CustomerEntity> customerEntities) {
        List<CustomerDto> result = null;
        if (customerEntities != null && !customerEntities.isEmpty()) {
            result = new ArrayList<>();
            CustomerDto customerDto;
            for (CustomerEntity customerEntity : customerEntities) {
                customerDto = this.mapEntityFrom(customerEntity);
                result.add(customerDto);
            }
        }
        return result;
    }

    @Override
    public List<CustomerEntity> mapDtosFrom(List<CustomerDto> dtoList) {
        List<CustomerEntity> result = null;
        if (dtoList != null && !dtoList.isEmpty()) {
            result = new ArrayList<>();
            CustomerEntity customerEntity;
            for (CustomerDto customerDto : dtoList) {
                customerEntity = this.mapDtoFrom(customerDto);
                result.add(customerEntity);
            }
        }
        return result;
    }
}
