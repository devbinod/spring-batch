package edu.miu.cs590.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private LocalDateTime timestamp = LocalDateTime.now();
    private int code;
    private String status;
    private String message;
    private String details;

    public ErrorResponse(HttpStatus httpStatus,String message, String details) {
        this.code = httpStatus.value();
        this.status = httpStatus.toString();
        this.message = message;
        this.details = details;
    }
}
