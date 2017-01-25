package thesis.buyproducts.vo;

import java.util.List;

public class ValidationErrorVO {

	private List<FieldErrorVO> listOfErrors;

	public List<FieldErrorVO> getListOfErrors() {
		return listOfErrors;
	}

	public void setListOfErrors(List<FieldErrorVO> listOfErrors) {
		this.listOfErrors = listOfErrors;
	}
	
}
