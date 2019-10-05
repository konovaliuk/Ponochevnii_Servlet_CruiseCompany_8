package ua.study.poject.cruise.exceptions;

/**
 * GeneralCheckedException is a class that wraps all other exceptions
 * so that there is a single interface at all program levels
 */
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
