package thesis.buyproducts.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import thesis.buyproducts.dto.ValidationErrorDto;
import thesis.buyproducts.execption.ExceptionDetails;
import thesis.buyproducts.execption.RestApiException;
import thesis.buyproducts.util.ResolveValidationErrorMessage;

import java.sql.Timestamp;
import java.util.List;

@ControllerAdvice
public class GeneralControllerAdvicer {

    @Autowired
    private ResolveValidationErrorMessage resolveValidationErrorMessage;

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ValidationErrorDto handleValidationViolation(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        return resolveValidationErrorMessage.proccesFieldErrors(errors);
    }

    @ResponseBody
    @ExceptionHandler(value = RestApiException.class)
    public ResponseEntity<ExceptionDetails> handleRestApiExceptino(RestApiException restApiException) {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        ResponseEntity<ExceptionDetails> responseEntity;
        exceptionDetails.setMessage(restApiException.getMessage());
        exceptionDetails.setTimestamp(new Timestamp(System.currentTimeMillis()));
        switch (restApiException.getExceptionType()) {
            case BAD_INPUT:
                responseEntity = new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
                break;
            case ERORR_UPDATING:
                responseEntity = new ResponseEntity<>(exceptionDetails, HttpStatus.CONFLICT);
                break;
            case NOTHING_FOUND:
                responseEntity = new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
                break;
            case ERROR_SAVING:
            case UNKONW_ERROR:
                responseEntity = new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
                break;
            default:
                responseEntity = new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
                break;
        }
        return responseEntity;
    }
}
