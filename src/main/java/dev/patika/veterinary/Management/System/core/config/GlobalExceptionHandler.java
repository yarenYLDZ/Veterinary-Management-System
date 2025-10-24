package dev.patika.veterinary.Management.System.core.config;

import dev.patika.veterinary.Management.System.core.exception.CustomException;
import dev.patika.veterinary.Management.System.core.exception.NotFoundException;
import dev.patika.veterinary.Management.System.core.exception.ValidationException;
import dev.patika.veterinary.Management.System.core.result.Result;
import dev.patika.veterinary.Management.System.core.result.ResultData;
import dev.patika.veterinary.Management.System.core.utilies.Messages;
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
        return new ResponseEntity<>(
                ResultHelper.missingParamError(e.getParameterName()),
                HttpStatus.BAD_REQUEST
        );
    }

    // DTO @Valid doğrulama hataları (dinamik mesaj)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e){
        List<String> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        // DTO veya entity türüne göre mesaj seç
        String entityName = e.getBindingResult().getTarget().getClass().getSimpleName();
        String message;
        switch (entityName) {
            case "AppointmentRequest":
                message = Messages.APPOINTMENT_VALIDATION_ERROR;
                break;
            case "CustomerSaveRequest":   // <-- DTO’nun gerçek ismi
                message = Messages.CUSTOMER_VALIDATION_ERROR;
                break;
            case "AnimalSaveRequest":     // <-- hayvan DTO’su
                message = Messages.ANIMAL_VALIDATION_ERROR;
                break;
            default:
                message = Messages.VALIDATE_ERROR;
        }

        return new ResponseEntity<>(
                ResultHelper.validateError(message, validationErrorList),
                HttpStatus.BAD_REQUEST
        );
    }


    // Entity constraint hataları
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResultData<List<String>>> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errors = e.getConstraintViolations()
                .stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                ResultHelper.validateError(Messages.VALIDATE_ERROR, errors),
                HttpStatus.BAD_REQUEST
        );
    }

    // NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(
                ResultHelper.notFoundError(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    // Custom exception
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Result> handleCustomException(CustomException e) {
        return new ResponseEntity<>(
                ResultHelper.validateError(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    // ValidationException (manuel kontrol)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Result> handleValidationException(ValidationException e) {
        String msg = switch (e.getEntityName()) {
            case "Appointment" -> Messages.APPOINTMENT_VALIDATION_ERROR + " " + e.getMessage();
            case "Customer" -> Messages.CUSTOMER_VALIDATION_ERROR + " " + e.getMessage();
            case "Animal" -> Messages.ANIMAL_VALIDATION_ERROR + " " + e.getMessage();
            default -> Messages.VALIDATE_ERROR + ": " + e.getMessage();
        };

        return new ResponseEntity<>(ResultHelper.validateError(msg), HttpStatus.BAD_REQUEST);
    }
}
