package uol.compass.msorder.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uol.compass.msorder.model.exceptions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void whenHandleItemNotFoundException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleItemNotFoundException(new ItemNotFoundException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleOrderNotFoundException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleOrderNotFoundException(new OrderNotFoundException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleInvalidDateException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleInvalidDateException(new InvalidDateException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleNullDateException(){
        ResponseEntity<Object> response = exceptionHandler
                .handleNullDateException(new NullDateException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenHandleInvalidItemValueException (){
        ResponseEntity<Object> response = exceptionHandler
                .handleInvalidItemValueException(new InvalidItemValueException());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

}
