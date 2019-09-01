package ua.epam.poject.cruise.exceptions;

 public class GeneralRunTimeException extends RuntimeException {

    public GeneralRunTimeException(String message){
        super(message);
    }

    public GeneralRunTimeException(Throwable cause) {
        super(cause);
    }

    public GeneralRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }


}
