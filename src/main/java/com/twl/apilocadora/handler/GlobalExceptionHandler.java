package com.twl.apilocadora.handler;

import com.twl.apilocadora.dto.ErroApi;
import com.twl.apilocadora.exceptions.BusinessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ BusinessException.class, EmptyResultDataAccessException.class })
    public final ResponseEntity<ErroApi> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        ex.printStackTrace();

        if (ex instanceof BusinessException || ex instanceof EmptyResultDataAccessException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;

            return handleExceptionInternal(ex, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, headers, status, request);
        }
    }

    protected ResponseEntity<ErroApi> handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        ErroApi erroApi = new ErroApi(ex.getMessage(), ex.getClass().getSimpleName(), Arrays.toString(ex.getStackTrace()));

        return new ResponseEntity<>(erroApi, headers, status);
    }
}
