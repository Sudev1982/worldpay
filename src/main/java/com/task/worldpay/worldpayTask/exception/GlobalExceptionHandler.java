package com.task.worldpay.worldpayTask.exception;

import com.task.worldpay.worldpayTask.model.response.ApiErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, HttpServletRequest request) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setErrorMessage("Error Occured");
        apiErrorResponse.setRequestUri(request.getRequestURI());
        return new ResponseEntity<>(apiErrorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
