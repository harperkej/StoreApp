package thesis.buyproducts.execption.domaintype;

public enum ServiceExceptionType {

	USERNAME_NOT_UNIQUE("SV001", "The specified user name is not unique"), NOT_FOUND("SV002",
			"The specified resource is not found"), ERROR_PERSISTING("SV003",
					"Error while trying to persist"), ERROR_UPDARING("SV004", "Error updaing user");

	private ServiceExceptionType(String code, String message) {
		this.code = code;
		this.message = message;
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
