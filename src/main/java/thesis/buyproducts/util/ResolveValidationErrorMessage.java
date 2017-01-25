package thesis.buyproducts.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import thesis.buyproducts.dto.FieldErrorDto;
import thesis.buyproducts.dto.ValidationErrorDto;

@Component
public class ResolveValidationErrorMessage {

	@Autowired
	private MessageSource messageSource;
	
	public MessageSource getMessageSource()
	{
		return messageSource;
	}
	
	public String resolveLocalizedError(FieldError fieldError)
	{
		Locale currentLocale = LocaleContextHolder.getLocale();
		return getMessageSource().getMessage(fieldError, currentLocale);
	}
	
	public ValidationErrorDto proccesFieldErrors(List<FieldError> fieldErrors)
	{
		ValidationErrorDto validationErrorDto = new ValidationErrorDto();
		List<FieldErrorDto> listOfFieldErrorDto = new ArrayList<FieldErrorDto>();
		FieldErrorDto fieldErrorDto;
		for (FieldError fieldError : fieldErrors) {
			String localizedMessage = resolveLocalizedError(fieldError);
			fieldErrorDto = new FieldErrorDto();
			fieldErrorDto.setField(fieldError.getField());
			fieldErrorDto.setMessage(localizedMessage);
			listOfFieldErrorDto.add(fieldErrorDto);
		}
		validationErrorDto.setListOfErrors(listOfFieldErrorDto);
		return validationErrorDto;
	}
	
}
