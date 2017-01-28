package thesis.buyproducts.dto;

import java.util.List;

public class ValidationErrorDto {

    private List<FieldErrorDto> listOfErrors;

    public List<FieldErrorDto> getListOfErrors() {
        return listOfErrors;
    }

    public void setListOfErrors(List<FieldErrorDto> listOfErrors) {
        this.listOfErrors = listOfErrors;
    }

}
