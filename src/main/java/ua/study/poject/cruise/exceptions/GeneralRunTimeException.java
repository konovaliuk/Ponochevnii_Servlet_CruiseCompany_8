package ua.study.poject.cruise.exceptions;

 public class GeneralRunTimeException extends RuntimeException {

     private static final long serialVersionUID = -122202224927951650L;

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
