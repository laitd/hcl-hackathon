package com.hcl.hackathon.demo.configuration.exceptionHandler;


import com.hcl.hackathon.demo.constants.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * CustomGlobalExceptionHandler.
 *
 * @author Chinthana.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(RuntimeException ex, WebRequest request) {
        log.error("Exception Caught" + "at handleAllException {} {}", ex.getMessage(), request, ex);
        CustomErrorDetails customErrorDetails = CustomErrorDetails.
                of(StatusCode.INTERNAL_SERVER_ERROR.getCode(), StatusCode.INTERNAL_SERVER_ERROR.getDesc());
        return new ResponseEntity<>(customErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFound(NotFoundException ex, WebRequest request) {
        CustomErrorDetails customErrorDetails = CustomErrorDetails.
                of(StatusCode.NOT_FOUND.getCode(), StatusCode.NOT_FOUND.getDesc());
        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TradingException.class)
    public final ResponseEntity<Object> handleTradingException(TradingException ex, WebRequest request) {
        log.error("Exception Caught" + "at handleAllException {} {}", ex.getMessage(), request, ex);
        CustomErrorDetails customErrorDetails = CustomErrorDetails.
            of(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(customErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
