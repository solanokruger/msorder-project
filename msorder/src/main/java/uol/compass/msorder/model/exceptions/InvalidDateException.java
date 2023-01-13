package uol.compass.msorder.model.exceptions;

import org.springframework.http.HttpStatus;
import uol.compass.msorder.constants.ErrorCode;

public class InvalidDateException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public InvalidDateException() {
        super(ErrorCode.INVALID_DATE_PARAMETER.name());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCode.INVALID_DATE_PARAMETER;
        this.details = ErrorCode.INVALID_DATE_PARAMETER.getMessage();
    }
}
