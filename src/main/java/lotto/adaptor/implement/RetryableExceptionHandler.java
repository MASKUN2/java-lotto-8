package lotto.adaptor.implement;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import lotto.adaptor.ExceededRetryException;
import lotto.adaptor.RetryExceptionHandler;

public class RetryableExceptionHandler implements RetryExceptionHandler<IllegalArgumentException> {
    static final int MAX_TRY_COUNT = 10;
    static final String ERROR_PREFIX = "[ERROR] ";

    private final ApplicationOutputLineWriter lineWriter;

    public RetryableExceptionHandler(ApplicationOutputLineWriter lineWriter) {
        this.lineWriter = lineWriter;
    }

    @Override
    public <T> T handle(Supplier<T> supplier) throws ExceededRetryException {
        AtomicInteger tryCount = new AtomicInteger(0);

        while (tryCount.incrementAndGet() <= MAX_TRY_COUNT) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                lineWriter.writeLine(ERROR_PREFIX + exception.getMessage());
            }
        }
        throw new ExceededRetryException(ERROR_PREFIX + "재시도 횟수가 초과하였습니다");
    }
}
