package thesis.buyproducts.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import thesis.buyproducts.vo.FieldErrorVO;
import thesis.buyproducts.vo.ValidationErrorVO;

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
	
	public ValidationErrorVO proccesFieldErrors(List<FieldError> fieldErrors)
	{
		ValidationErrorVO validationErrorVO = new ValidationErrorVO();
		List<FieldErrorVO> listOfFieldErrorVO = new ArrayList<FieldErrorVO>();
		FieldErrorVO fieldErrorVO;
		for (FieldError fieldError : fieldErrors) {
			String localizedMessage = resolveLocalizedError(fieldError);
			fieldErrorVO = new FieldErrorVO();
			fieldErrorVO.setField(fieldError.getField());
			fieldErrorVO.setMessage(localizedMessage);
			listOfFieldErrorVO.add(fieldErrorVO);
		}
		validationErrorVO.setListOfErrors(listOfFieldErrorVO);
		return validationErrorVO;
	}
	
}
