package com.ra.web.config.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(value = PriorityOrdered.HIGHEST_PRECEDENCE)
public class CommonHandlerException {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonHandlerException.class);

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handlerException(Throwable ex, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        BaseException baseException;
        if (ex instanceof BaseException) {
            baseException = (BaseException) ex;
            return new ResponseEntity<>(baseException.getErrorMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        baseException = new BaseException("RA-00-001");
        return new ResponseEntity<>(baseException.getErrorMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
