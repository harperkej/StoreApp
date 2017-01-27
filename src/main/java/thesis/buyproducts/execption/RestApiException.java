package thesis.buyproducts.execption;

public class RestApiException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message;

    private ExceptionType exceptionType;


    public RestApiException(String message, ExceptionType exceptionType) {
        this.setMessage(message);
        this.exceptionType = exceptionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

}
