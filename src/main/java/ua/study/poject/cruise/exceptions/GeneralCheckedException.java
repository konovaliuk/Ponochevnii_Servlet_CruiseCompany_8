package ua.study.poject.cruise.exceptions;

public class GeneralCheckedException extends Exception {

    public GeneralCheckedException(String message) {
        super(message);
    }

    public GeneralCheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralCheckedException(Throwable cause) {
        super(cause);
    }
}
