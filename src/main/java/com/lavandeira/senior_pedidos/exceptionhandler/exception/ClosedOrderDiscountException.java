package com.lavandeira.senior_pedidos.exceptionhandler.exception;

public class ClosedOrderDiscountException extends RuntimeException{

    public ClosedOrderDiscountException(String message) {
        super(message);
    }
}
