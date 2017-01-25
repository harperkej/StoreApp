package thesis.buyproducts.execption;

import thesis.buyproducts.execption.domaintype.DaoExceptionType;

public class RepositoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4132467661986058909L;

	private DaoExceptionType daoExceptionType;

	private String message;

	public RepositoryException(DaoExceptionType daoExceptionType, String message) {
		this.daoExceptionType = daoExceptionType;
		this.message = message;
	}

	public DaoExceptionType getDaoExceptionType() {
		return daoExceptionType;
	}

	public void setDaoExceptionType(DaoExceptionType daoExceptionType) {
		this.daoExceptionType = daoExceptionType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static RepositoryException notFound(String message) {
		return new RepositoryException(DaoExceptionType.NOT_FOUND, message);
	}

	public static RepositoryException userNameNotUnique(String message) {
		return new RepositoryException(DaoExceptionType.USERNAME_NOT_UNIQUE, message);
	}

	public static RepositoryException errorPersisting(String message) {
		return new RepositoryException(DaoExceptionType.ERROR_PERSISTING, message);
	}

	public static RepositoryException errorUpdating(String message) {
		return new RepositoryException(DaoExceptionType.ERROR_UPDATING, message);
	}

}
