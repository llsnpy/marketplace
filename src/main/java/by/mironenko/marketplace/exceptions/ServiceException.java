package by.mironenko.marketplace.exceptions;

public class ServiceException extends RuntimeException {

    public ServiceException() { }

    public ServiceException(final String message) {
        super(message);
    }
    public ServiceException(final Throwable cause) {
        super(cause);
    }
    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
