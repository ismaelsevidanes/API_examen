package com.examen.api.error.exception;

public class UsuarioNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException(String message) {
        super(message);
    }
}