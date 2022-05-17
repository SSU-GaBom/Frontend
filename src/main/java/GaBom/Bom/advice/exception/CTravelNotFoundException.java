package GaBom.Bom.advice.exception;

public class CTravelNotFoundException extends RuntimeException{

    public CTravelNotFoundException() {
        super();
    }

    public CTravelNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CTravelNotFoundException(String msg) {
        super(msg);
    }

}