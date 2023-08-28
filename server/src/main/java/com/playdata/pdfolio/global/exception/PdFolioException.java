package com.playdata.pdfolio.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PdFolioException extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public PdFolioException() {
        ExceptionType exceptionType = ExceptionType.of(this.getClass());
        this.message = exceptionType.getMessage();
        this.httpStatus = exceptionType.getHttpStatus();
    }

}
