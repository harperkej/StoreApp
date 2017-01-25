package thesis.buyproducts.execption.domaintype;

public enum DaoExceptionType {

	NOT_FOUND("DB001", "Could not find the the required resource."), USERNAME_NOT_UNIQUE("DB002",
			"The specified username is not unique"), ERROR_PERSISTING("DB0003",
					"Unkown error while trying to persist"), ERROR_UPDATING("BD004", "Could not update the user");

	private DaoExceptionType(String code, String message) {
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
