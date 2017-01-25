package thesis.buyproducts.vo;

import javax.validation.constraints.Size;

public class CustomerVO {

	private Long id;

	@Size(min = 2, max = 32, message = "The first name must be between 2 and 32 characters")
	private String firstName;

	@Size(min = 2, max = 32, message = "The last name must be between 2 and 32 characters")
	private String lastName;

	@Size(min = 2, max = 32, message = "The user name must be between 2 and 32 characters")
	private String userName;

	private Double points;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}
}
