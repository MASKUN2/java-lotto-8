package lotto.controller;

import java.util.function.Supplier;

public class RetryableExceptionHandler implements ExceptionHandler {

    @Override
    public <T> T handle(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                System.out.println("[ERROR} " + exception.getMessage());
            }
        }
    }
}
