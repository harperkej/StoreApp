package thesis.buyproducts.util;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thesis.buyproducts.service.PointMappingService;

@Component
@Transactional
public class ConvertUtil {

	private static final double defaultPointMapper = 20;

	@Autowired
	private PointMappingService pointMappingService;

	public Double validateAndConvertAmountToPoints(Double amount) {
		Double result;

		Double pointMapping = pointMappingService.getPointMapper();
		if (pointMapping.doubleValue() == 0) {
			pointMapping = defaultPointMapper;
		}

		if (amount != null && amount.doubleValue() > 0) {
			double amountPrimitiveDouble = amount.doubleValue();
			double res = amountPrimitiveDouble / pointMapping;
			result = new Double(res);
			return result;
		}
		result = new Double(-1);
		return result;
	}

	public Double validateAmount(Double points) {
		if (points == null || points.doubleValue() < 0) {
			return new Double(-1);
		}
		return points;
	}

}
