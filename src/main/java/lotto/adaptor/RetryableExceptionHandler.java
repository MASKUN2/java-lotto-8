package lotto.adaptor;

import java.util.function.Supplier;

public interface RetryableExceptionHandler<E extends IllegalArgumentException> {
    <T> T handle(Supplier<T> supplier) throws ExceededRetryException;
}
