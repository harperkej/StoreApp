package thesis.buyproducts.execption.domaintype;

public enum StrategyExceptionType {

	AMOUNT_NOT_VALID("PPS001", "Amount is not valid"), COULT_NOT_FIND_USER("PPS002",
			"Could not find customer"), ERROR_UPDAING_USER("PPS003",
					"Error updating user while processing purchase"), ERROR_PROCESSING_POINTS("PPS004",
							"Error while processing points");

	private String code;

	private String message;

	StrategyExceptionType(String code, String message) {

		this.message = message;
		this.code = code;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
