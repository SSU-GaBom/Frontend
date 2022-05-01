package GaBom.Bom.advice.exception;

public class CImageNotFoundException extends RuntimeException {

    public CImageNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CImageNotFoundException(String msg) {
        super(msg);
    }

    public CImageNotFoundException() {
        super();
    }
}