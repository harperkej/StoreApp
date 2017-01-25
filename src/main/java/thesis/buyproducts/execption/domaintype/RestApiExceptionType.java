package thesis.buyproducts.execption.domaintype;

public enum RestApiExceptionType {

	NOT_FOUND("RESTAPI001", "Resource not found"), NOT_UNIQUE_USERNAME("RESTAPI002",
			"User name not unique"), ERROR_PERSISTING("RESTAPI003", "Error while trying to persist"), ERROR_UPDATING(
					"RESTAPI004", "Error while updating the user"), INVALID_AMOUNT("RESTAPI005",
							"The amount was not a valid amount!"), ERROR_PROCESSING_POINTS("RESTAPI006",
									"Error processing points"), UNHANDLED_EXCEPTION("RESTAPI007",
											"An unhandle exception occured");

	private RestApiExceptionType(String code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}

	private String code;

	private String message;

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
