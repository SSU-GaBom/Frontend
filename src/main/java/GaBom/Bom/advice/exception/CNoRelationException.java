package GaBom.Bom.advice.exception;

public class CNoRelationException extends RuntimeException{

    public CNoRelationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNoRelationException(String msg) {
        super(msg);
    }

    public CNoRelationException(){
        super();
    }

}
