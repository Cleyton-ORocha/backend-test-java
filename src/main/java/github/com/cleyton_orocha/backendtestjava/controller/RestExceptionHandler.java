package github.com.cleyton_orocha.backendtestjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import github.com.cleyton_orocha.backendtestjava.exception.ApiErrors;
import github.com.cleyton_orocha.backendtestjava.exception.BusinessException;

@RestControllerAdvice
public class RestExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ApiErrors handleValidationExceptions(MethodArgumentNotValidException ex) {
    BindingResult bindingResult = ex.getBindingResult();

    return new ApiErrors(bindingResult);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BusinessException.class)
  public ApiErrors handleBusinessExceptions(BusinessException ex) {
    return new ApiErrors(ex);
  }

}
