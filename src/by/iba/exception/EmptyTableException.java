package by.iba.exception;

public class EmptyTableException extends Exception {

    public EmptyTableException(){
        super();
    }

    public EmptyTableException(String message) {
        super(message);
    }

    public EmptyTableException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyTableException(Throwable cause) {
        super(cause);
    }

    protected EmptyTableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
