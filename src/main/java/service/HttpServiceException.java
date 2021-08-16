package service;

public class HttpServiceException extends Exception {

    public HttpServiceException() {
        super();
    }

    public HttpServiceException(String message) {
        super(message);
    }

    public HttpServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpServiceException(Throwable cause) {
        super(cause);
    }
}
