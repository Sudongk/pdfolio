package com.playdata.pdfolio.global.exception;

public record ExceptionResponse(String message) {

    public static ExceptionResponse from(PdFolioException e){
        return new ExceptionResponse(e.getMessage());
    }
    public static ExceptionResponse from(String message){
        return new ExceptionResponse(message);
    }
}
