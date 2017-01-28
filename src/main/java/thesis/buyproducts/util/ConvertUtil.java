package thesis.buyproducts.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.buyproducts.execption.ServiceException;
import thesis.buyproducts.service.PointMappingService;

@Component
public class ConvertUtil {

    private static final double defaultPointMapper = 20;

    @Autowired
    private PointMappingService pointMappingService;

    public Double validateAndConvertAmountToPoints(Double amount) throws ServiceException {
        Double result;
        Double pointMapping = pointMappingService.getPointMapper();

        if (pointMapping.doubleValue() == 0) {
            pointMapping = defaultPointMapper;
        }

        if (amount != null && amount.doubleValue() > 0) {
            double amountPrimitiveDouble = amount.doubleValue();
            double res = amountPrimitiveDouble / pointMapping;
            result = new Double(res);
        } else {
            result = new Double(-1);
        }
        return result;
    }

    public Double validateAmount(Double points) {
        Double res;
        if (points == null || points.doubleValue() < 0) {
            res = new Double(-1);
        } else {
            res = points;
        }
        return points;
    }

}
