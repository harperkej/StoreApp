package thesis.buyproducts.execption;

public class RepositoryException extends ServiceException {

    /**
     *
     */
    private static final long serialVersionUID = 4132467661986058909L;

    public RepositoryException(String message, ExceptionType exceptionType) {
        super(message, exceptionType);
    }
}
