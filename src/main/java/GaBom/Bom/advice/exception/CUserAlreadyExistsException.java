package GaBom.Bom.advice.exception;

public class CUserAlreadyExistsException extends RuntimeException{

    public CUserAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public CUserAlreadyExistsException(String msg) {
        super(msg);
    }

    public CUserAlreadyExistsException() {
        super();
    }
}
