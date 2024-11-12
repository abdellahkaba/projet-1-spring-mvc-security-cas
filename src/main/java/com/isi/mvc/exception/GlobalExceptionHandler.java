package com.isi.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ReferenceConflictException.class)
    public ResponseEntity<ExceptionResponse> handleException(ReferenceConflictException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.DUPLICATE_REFERENCE.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.DUPLICATE_REFERENCE.getCode())
                                .businessErrorDescription(BusinessErrorCodes.DUPLICATE_REFERENCE.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(NameConflictException.class)
    public ResponseEntity<ExceptionResponse> handleException(NameConflictException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.DUPLICATE_NAME.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.DUPLICATE_NAME.getCode())
                                .businessErrorDescription(BusinessErrorCodes.DUPLICATE_NAME.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception exp) {
        exp.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorDescription("Erreur interne, Il se peut que le nom de la reference ou le produit existe ! " +
                                        "\n" + "Veuillez contacter l'administrateur !")
                                .error(exp.getMessage())
                                .build()
                );
    }
}
