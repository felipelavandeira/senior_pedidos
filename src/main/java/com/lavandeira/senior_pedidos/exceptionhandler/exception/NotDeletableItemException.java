package com.lavandeira.senior_pedidos.exceptionhandler.exception;

public class NotDeletableItemException extends RuntimeException{

    public NotDeletableItemException(String message) {
        super(message);
    }
}
