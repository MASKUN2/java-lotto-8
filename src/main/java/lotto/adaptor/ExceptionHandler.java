package lotto.adaptor;

import java.util.function.Supplier;

public interface ExceptionHandler {
    <T> T handle(Supplier<T> supplier);
}
