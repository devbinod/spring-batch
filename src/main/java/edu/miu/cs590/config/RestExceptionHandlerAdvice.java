package edu.miu.cs590.config;

import edu.miu.cs590.exception.ErrorResponse;
import edu.miu.cs590.exception.InvalidCredentialException;
import edu.miu.cs590.exception.InvalidTokenException;
import edu.miu.cs590.exception.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandlerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler({InvalidCredentialException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> badRequestException(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), webRequest.getDescription(false))
                , HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> invalidToken(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> internalServerException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                request.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
