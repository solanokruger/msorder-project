package uol.compass.msorder.model.exceptions;

import org.springframework.http.HttpStatus;
import uol.compass.msorder.constants.ErrorCode;

public class InvalidItemValueException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public InvalidItemValueException() {
        super(ErrorCode.INVALID_ITEM_VALUE.name());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCode.INVALID_ITEM_VALUE;
        this.details = ErrorCode.INVALID_ITEM_VALUE.getMessage();
    }
}
