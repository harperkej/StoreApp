package thesis.buyproducts.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import thesis.buyproducts.dto.BuyWithPointsDto;
import thesis.buyproducts.dto.CustomerDto;
import thesis.buyproducts.execption.ServiceException;
import thesis.buyproducts.repository.CustomerRepository;
import thesis.buyproducts.util.ConvertUtil;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static thesis.buyproducts.util.CustomerDataFactory.getCustomerWithNullPoints;


@RunWith(MockitoJUnitRunner.class)
public class BuyWithPointsServiceUnitTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerService customerService;

    @Mock
    ConvertUtil convertUtil;

    BuyWithPointsServiceImpl buyWithPointsService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Before
    public void setUp() {
        buyWithPointsService = new BuyWithPointsServiceImpl();
        buyWithPointsService.customerepository = this.customerRepository;
        buyWithPointsService.customerService = this.customerService;
        buyWithPointsService.convertUtil = this.convertUtil;

    }

    /**
     * Tests the case the amount of the purchase is negative. In this an exception is expected.
     *
     * @throws Exception
     */
    @Test
    public void testProcessPurchaseWhenPurchaseValueIsNegative() throws Exception {
        expectedException.expect(ServiceException.class);
        when(customerService.findByUserName(anyString())).thenReturn(new CustomerDto());
        when(convertUtil.validateAndConvertAmountToPoints(anyDouble())).thenReturn(new Double(-1));
        buyWithPointsService.processPurchaseWithPoints(anyString(), new Double(-1));
    }

    /**
     * Tests the case when for the given user no purchase yet registered. In this case the
     * user has to pay the amount and no discount is performed.
     *
     * @throws Exception
     */
    @Test
    public void testProcessPurchaseWhenCustomerHasNoPointsAndWantsToBuyByPoints() throws Exception {
        Double pointsBasedOnPurchaseAmount = 15.0;

        when(customerService.findByUserName(anyString())).thenReturn(getCustomerWithNullPoints());

        when(convertUtil.validateAndConvertAmountToPoints(Matchers.anyDouble())).thenReturn(pointsBasedOnPurchaseAmount);

        when(customerRepository.update(any(CustomerDto.class))).thenReturn(any(CustomerDto.class));

        BuyWithPointsDto buyWithPointsDto = buyWithPointsService.processPurchaseWithPoints("", new Double(12));

        assertThat(pointsBasedOnPurchaseAmount, is(equalTo(buyWithPointsDto.getHasToPay())));
        assertThat(getCustomerWithNullPoints().getFirstName(), is(equalTo(buyWithPointsDto.getFirstName())));
        assertThat(getCustomerWithNullPoints().getLastName(), is(equalTo(buyWithPointsDto.getLastName())));
        assertThat(getCustomerWithNullPoints().getPoints(), is(equalTo(buyWithPointsDto.getPointsLeft())));
    }

}
