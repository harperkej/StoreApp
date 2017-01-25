package thesis.buyproducts.vo;

import javax.validation.constraints.Min;

public class PointMappingVO {

	private Long id;

	@Min(value = 0)
	private Double value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
