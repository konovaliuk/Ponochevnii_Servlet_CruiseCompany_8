package ua.study.poject.cruise.exceptions;

public class GeneralCheckedException extends Exception {

    private static final long serialVersionUID = 6658646255562726058L;

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
