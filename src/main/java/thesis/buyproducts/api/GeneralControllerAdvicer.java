package thesis.buyproducts.api;

import java.sql.Timestamp;
import java.util.List;

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

import thesis.buyproducts.execption.ExceptionDetails;
import thesis.buyproducts.execption.RestApiException;
import thesis.buyproducts.execption.domaintype.RestApiExceptionType;
import thesis.buyproducts.util.ResolveValidationErrorMessage;
import thesis.buyproducts.dto.ValidationErrorDto;

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
		if (restApiException.getRestApiExceptionType() == RestApiExceptionType.NOT_UNIQUE_USERNAME) {
			exceptionDetails.setCode(restApiException.getRestApiExceptionType().getCode());
			exceptionDetails.setMessage(restApiException.getMessage());
			exceptionDetails.setStatus("400");
			exceptionDetails.setTimestamp(new Timestamp(System.currentTimeMillis()));
			return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.BAD_REQUEST);
		}
		if (restApiException.getRestApiExceptionType() == RestApiExceptionType.ERROR_PERSISTING) {
			exceptionDetails.setCode(restApiException.getRestApiExceptionType().getCode());
			exceptionDetails.setMessage(restApiException.getMessage());
			exceptionDetails.setStatus("500");
			exceptionDetails.setTimestamp(new Timestamp(System.currentTimeMillis()));
			return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (restApiException.getRestApiExceptionType() == RestApiExceptionType.NOT_FOUND) {
			exceptionDetails.setCode(restApiException.getRestApiExceptionType().getCode());
			exceptionDetails.setMessage(restApiException.getMessage());
			exceptionDetails.setStatus("404");
			exceptionDetails.setTimestamp(new Timestamp(System.currentTimeMillis()));
			return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.NOT_FOUND);
		}
		if (restApiException.getRestApiExceptionType() == RestApiExceptionType.INVALID_AMOUNT) {
			exceptionDetails.setCode(restApiException.getRestApiExceptionType().getCode());
			exceptionDetails.setMessage(restApiException.getMessage());
			exceptionDetails.setStatus("409");
			exceptionDetails.setTimestamp(new Timestamp(System.currentTimeMillis()));
			return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.CONFLICT);
		} else if (restApiException.getRestApiExceptionType() == RestApiExceptionType.ERROR_PROCESSING_POINTS) {
			exceptionDetails.setCode(restApiException.getRestApiExceptionType().getCode());
			exceptionDetails.setMessage(restApiException.getMessage());
			exceptionDetails.setStatus("409");
			exceptionDetails.setTimestamp(new Timestamp(System.currentTimeMillis()));
			return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
