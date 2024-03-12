package anubis.lab.anubisproject.exceptions;


public class BadRequestExeption extends Exception {

    public BadRequestExeption() {
    }

    public BadRequestExeption(String message) {
        super(message);
    }

    public BadRequestExeption(Throwable cause) {
        super(cause);
    }

    public BadRequestExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
