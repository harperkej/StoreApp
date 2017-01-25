package thesis.buyproducts.execption;

import thesis.buyproducts.execption.domaintype.RestApiExceptionType;

public class RestApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RestApiExceptionType restApiExceptionType;

	private String message;

	public RestApiException(RestApiExceptionType restApiExceptionType, String message) {
		this.setRestApiExceptionType(restApiExceptionType);
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public RestApiExceptionType getRestApiExceptionType() {
		return restApiExceptionType;
	}

	public void setRestApiExceptionType(RestApiExceptionType restApiExceptionType) {
		this.restApiExceptionType = restApiExceptionType;
	}

	public static RestApiException notFound(String message) {
		return new RestApiException(RestApiExceptionType.NOT_FOUND, message);
	}

	public static RestApiException userNameNotUnique(String message) {
		return new RestApiException(RestApiExceptionType.NOT_UNIQUE_USERNAME, message);
	}

	public static RestApiException errorPersisting(String message) {
		return new RestApiException(RestApiExceptionType.ERROR_PERSISTING, message);
	}

	public static RestApiException errorUpdating(String message) {
		return new RestApiException(RestApiExceptionType.ERROR_UPDATING, message);
	}

	public static RestApiException invalidAmount(String message) {
		return new RestApiException(RestApiExceptionType.INVALID_AMOUNT, message);
	}

	public static RestApiException errorProcessingPoints(String message) {
		return new RestApiException(RestApiExceptionType.ERROR_PROCESSING_POINTS, message);
	}

	public static RestApiException unhandledException(String message) {
		// TODO Auto-generated method stub
		return new RestApiException(RestApiExceptionType.UNHANDLED_EXCEPTION,message);
	}

}
