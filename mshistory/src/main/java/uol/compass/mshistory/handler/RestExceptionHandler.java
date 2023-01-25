package uol.compass.mshistory.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uol.compass.mshistory.constants.ErrorCode;
import uol.compass.mshistory.models.response.ExceptionResponse;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INTERNAL_SERVER_ERROR, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public final ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.JSON_PROCESSING_ERROR, ex);
        log.warn("Tried to receive JSON Order - Throwed JsonProcessingException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

}
