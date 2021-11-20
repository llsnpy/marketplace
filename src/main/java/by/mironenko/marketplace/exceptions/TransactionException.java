package by.mironenko.marketplace.exceptions;

public class TransactionException extends RuntimeException {

    public TransactionException() { }
    public TransactionException(final String message) {
        super(message);
    }
    public TransactionException(final String message, final Throwable cause) {
        super(message, cause);
    }
    public TransactionException(final Throwable cause) {
        super(cause);
    }
}
