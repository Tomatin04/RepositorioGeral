package com.project.api.infra.exceptions;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String mensaggem) {
        super(mensaggem);
    }
}
