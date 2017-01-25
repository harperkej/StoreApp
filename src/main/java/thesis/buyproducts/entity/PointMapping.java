package thesis.buyproducts.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PointMapping {

	@Id
	private Long id;

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
