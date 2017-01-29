package thesis.buyproducts.util;

import thesis.buyproducts.dto.CustomerDto;

public class CustomerDataFactory {

    public static CustomerDto getCustomerWithNullPoints() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("Arbër");
        customerDto.setLastName("Kuçi");
        customerDto.setUserName("harperkej");
        customerDto.setPoints(new Double(0));
        return customerDto;
    }

}
