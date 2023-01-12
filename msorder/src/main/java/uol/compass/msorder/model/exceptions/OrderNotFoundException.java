package uol.compass.msorder.model.exceptions;

import org.springframework.http.HttpStatus;
import uol.compass.msorder.constants.ErrorCode;

public class OrderNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public OrderNotFoundException() {
        super(ErrorCode.ORDER_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.ORDER_NOT_FOUND;
        this.details = ErrorCode.ORDER_NOT_FOUND.getMessage();
    }
}
