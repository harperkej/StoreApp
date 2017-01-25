package thesis.buyproducts.execption;

import thesis.buyproducts.execption.domaintype.ServiceExceptionType;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ServiceExceptionType serviceExceptionType;

	private String message;

	public ServiceException(ServiceExceptionType exceptionType, String message) {
		this.setServiceExceptionType(exceptionType);
		this.setMessage(message);
	}

	public ServiceExceptionType getServiceExceptionType() {
		return serviceExceptionType;
	}

	public void setServiceExceptionType(ServiceExceptionType serviceExceptionType) {
		this.serviceExceptionType = serviceExceptionType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static ServiceException notFound(String message) {
		return new ServiceException(ServiceExceptionType.NOT_FOUND, message);
	}

	public static ServiceException userNameNotUnique(String message) {
		return new ServiceException(ServiceExceptionType.USERNAME_NOT_UNIQUE, message);
	}

	public static ServiceException errorPersisting(String message) {
		return new ServiceException(ServiceExceptionType.ERROR_PERSISTING, message);
	}

	public static ServiceException errorUpdating(String message) {
		return new ServiceException(ServiceExceptionType.ERROR_UPDARING, message);
	}
}
