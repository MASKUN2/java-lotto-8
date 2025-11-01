package lotto.adaptor;

public class ExceededRetryException extends RuntimeException {
    public ExceededRetryException(String message) {
        super(message);
    }
}
