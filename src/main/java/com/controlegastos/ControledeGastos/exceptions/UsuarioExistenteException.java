package com.controlegastos.ControledeGastos.exceptions;

public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException(String message) {
        super(message);
    }
}
