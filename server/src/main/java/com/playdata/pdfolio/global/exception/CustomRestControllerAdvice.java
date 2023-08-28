package com.playdata.pdfolio.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class CustomRestControllerAdvice {

    private static final String INVALID_DTO_FIELD_ERROR_MESSAGE_FORMAT = "%s : %s (request value: %s)";

    @ExceptionHandler(PdFolioException.class)
    public ResponseEntity<ExceptionResponse> PdFolioExceptionHandler(PdFolioException e){
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ExceptionResponse.from(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleBindingException(MethodArgumentNotValidException e) {
        log.info(String.format("MethodArgumentNotValidException %s", e.getMessage()), e);

        FieldError fieldError = e.getFieldError();
        Objects.requireNonNull(fieldError);

        String message = String.format(INVALID_DTO_FIELD_ERROR_MESSAGE_FORMAT, fieldError.getField(),
                fieldError.getDefaultMessage(), fieldError.getRejectedValue());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.from(message));
    }

}
