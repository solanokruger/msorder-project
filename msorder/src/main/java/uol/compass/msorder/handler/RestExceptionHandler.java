package uol.compass.msorder.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uol.compass.msorder.constants.ErrorCode;
import uol.compass.msorder.model.dtos.response.ExceptionResponse;
import uol.compass.msorder.model.exceptions.*;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMissingServletRequestParameter (MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INVALID_PARAMETER, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INVALID_PARAMETER, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<String> errors = new ArrayList<>();
        fieldErrors.forEach(error ->
                errors.add(String.format("%s : %s", error.getField(), error.getDefaultMessage()))
        );

        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
                                                         WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }


    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INTERNAL_SERVER_ERROR, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public final ResponseEntity<Object> handlePropertyReferenceException(PropertyReferenceException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public final ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.ITEM_NOT_FOUND, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.ORDER_NOT_FOUND, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(InvalidDateException.class)
    public final ResponseEntity<Object> handleInvalidDateException(InvalidDateException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INVALID_DATE_PARAMETER, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(NullDateException.class)
    public final ResponseEntity<Object> handleNullDateException(NullDateException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.NULL_DATE_PARAMETER, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(InvalidItemValueException.class)
    public final ResponseEntity<Object> handleInvalidItemValueException(InvalidItemValueException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INVALID_ITEM_VALUE, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INVALID_PARAMETER, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public final ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.JSON_PROCESSING_ERROR, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }




}
