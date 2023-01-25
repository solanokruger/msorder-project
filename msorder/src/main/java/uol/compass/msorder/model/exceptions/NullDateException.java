package uol.compass.msorder.model.exceptions;

import org.springframework.http.HttpStatus;
import uol.compass.msorder.constants.ErrorCode;

public class NullDateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public NullDateException() {
        super(ErrorCode.NULL_DATE_PARAMETER.name());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCode.NULL_DATE_PARAMETER;
        this.details = ErrorCode.NULL_DATE_PARAMETER.getMessage();
    }
}
