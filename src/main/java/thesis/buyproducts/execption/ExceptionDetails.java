package thesis.buyproducts.execption;

import java.sql.Timestamp;

public class ExceptionDetails {

    private Timestamp timestamp;

    private String message;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
