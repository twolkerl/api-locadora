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

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class, EmptyResultDataAccessException.class, ConstraintViolationException.class})
    public final ResponseEntity<ErroApi> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        ex.printStackTrace();

        if (ex instanceof BusinessException || ex instanceof EmptyResultDataAccessException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;

            return handleExceptionInternal(ex, headers, status, request);
        } else if (ex instanceof ConstraintViolationException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ConstraintViolationException cve = (ConstraintViolationException) ex;

            return handleConstraintViolationException(cve, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, headers, status, request);
        }
    }

    private ResponseEntity<ErroApi> handleConstraintViolationException(ConstraintViolationException cve, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> erros = new ArrayList<>();

        cve.getConstraintViolations().forEach(constraintViolation -> erros.add(constraintViolation.getMessage()));

        ErroApi erroApi = new ErroApi(erros, cve.getClass().getSimpleName(), Arrays.toString(cve.getStackTrace()));

        return new ResponseEntity<>(erroApi, headers, status);
    }

    protected ResponseEntity<ErroApi> handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        ErroApi erroApi = new ErroApi(Collections.singletonList(ex.getMessage()), ex.getClass().getSimpleName(), Arrays.toString(ex.getStackTrace()));

        return new ResponseEntity<>(erroApi, headers, status);
    }
}
