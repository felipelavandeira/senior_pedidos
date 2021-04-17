package com.lavandeira.senior_pedidos.exceptionhandler.exception;

public class DisabledItemException extends RuntimeException{

    public DisabledItemException(String message) {
        super(message);
    }
}
