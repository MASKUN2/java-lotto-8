package lotto.adaptor.implement;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import lotto.adaptor.ExceptionHandler;

public class RetryableExceptionHandler implements ExceptionHandler {
    private static final int MAX_RETRY_COUNT = 10;

    @Override
    public <T> T handle(Supplier<T> supplier) {
        AtomicInteger retryCount = new AtomicInteger(0);

        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                System.out.println("[ERROR] " + exception.getMessage());
                if (retryCount.incrementAndGet() >= MAX_RETRY_COUNT) {
                    throw exception;
                }
            }
        }
    }
}
