package com.ghazala.yassir.bank.exceptions;

public class NegativeFundsException extends Exception{
    public NegativeFundsException(String message) {
        super(message);
    }
}
