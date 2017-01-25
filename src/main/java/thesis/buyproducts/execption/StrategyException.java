package thesis.buyproducts.execption;

import thesis.buyproducts.execption.domaintype.StrategyExceptionType;

public class StrategyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7470112634504367007L;

	private StrategyExceptionType exceptionType;

	private String message;

	public StrategyException(StrategyExceptionType exceptionType, String message) {
		this.exceptionType = exceptionType;
		this.message = message;
	}

	public StrategyExceptionType getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(StrategyExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static StrategyException amountNotValid(String message) {
		return new StrategyException(StrategyExceptionType.AMOUNT_NOT_VALID, message);
	}
	
	public static StrategyException couldNotFoundCustomer(String message)
	{
		return new StrategyException(StrategyExceptionType.COULT_NOT_FIND_USER, message);
	}
	public static StrategyException errorUpdatingCustomer(String message)
	{
		return new StrategyException(StrategyExceptionType.ERROR_UPDAING_USER, message);
	}

	public static StrategyException errorProcessingPoints(String message) {
		return new StrategyException(StrategyExceptionType.ERROR_PROCESSING_POINTS, message);
	}

}
