package lotto.adaptor;

import java.util.function.Supplier;

public interface RetryExceptionHandler<E extends IllegalArgumentException> {
    <T> T handle(Supplier<T> supplier) throws ExceededRetryException;
}
