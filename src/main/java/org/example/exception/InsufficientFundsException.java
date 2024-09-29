package org.example.exception;

/**
 * Исключение симулятора банкомата непосредственно.
 * Имеет место в случае невозможности сбора точной суммы.
 *
 * @author Valera
 * @version 1.0
 */
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
