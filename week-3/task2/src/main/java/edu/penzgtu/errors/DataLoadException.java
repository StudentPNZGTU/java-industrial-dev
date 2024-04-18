package edu.penzgtu.errors;

public class DataLoadException extends RuntimeException {

    public DataLoadException() {
        super();
    }

    public DataLoadException(String message) {
        super(message);
    }

    public DataLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataLoadException(Throwable cause) {
        super(cause);
    }
}
