package dev.patika.veterinary.Management.System.core.config;

import dev.patika.veterinary.Management.System.core.exception.NotFoundException;
import dev.patika.veterinary.Management.System.core.result.Result;
import dev.patika.veterinary.Management.System.core.result.ResultData;
import dev.patika.veterinary.Management.System.core.utilies.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Eksik query parametreleri
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Result> handleMissingParam(MissingServletRequestParameterException e) {
        return new ResponseEntity<>(ResultHelper.missingParamError(e.getParameterName()), HttpStatus.BAD_REQUEST);
    }

    // RequestBody @Valid doğrulama hataları
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e){
        List<String> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    // NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Entity constraint hataları (örn: tarih vs.)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResultData<List<String>>> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errors = e.getConstraintViolations()
                .stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(ResultHelper.validateError(errors), HttpStatus.BAD_REQUEST);
    }
}
