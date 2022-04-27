package GaBom.Bom.advice.exception;

public class CNotSameUserException extends RuntimeException{

    public CNotSameUserException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotSameUserException(String msg) {
        super(msg);
    }

    public CNotSameUserException(){
        super();
    }

}
