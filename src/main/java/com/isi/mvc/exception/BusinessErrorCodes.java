package com.isi.mvc.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

@Getter
public enum BusinessErrorCodes {
    NO_CODE(0, NOT_IMPLEMENTED, "Aucun code"),
    DUPLICATE_NAME(409, HttpStatus.CONFLICT, "Ce nom existe déjà"),
    DUPLICATE_REFERENCE(409, HttpStatus.CONFLICT, "Cette reference existe déjà"),
    ;
    private final int code;
    private final String description;
    private final HttpStatus httpStatus;
    BusinessErrorCodes(int code, HttpStatus status, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = status;
    }
}
