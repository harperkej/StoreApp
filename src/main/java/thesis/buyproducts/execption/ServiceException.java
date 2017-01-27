package thesis.buyproducts.execption;

public class ServiceException extends RestApiException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public ServiceException(String message, ExceptionType exceptionType) {
        super(message, exceptionType);
    }
}
