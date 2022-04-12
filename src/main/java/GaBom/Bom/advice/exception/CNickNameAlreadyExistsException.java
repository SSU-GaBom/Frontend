package GaBom.Bom.advice.exception;

public class CNickNameAlreadyExistsException extends RuntimeException{

    public CNickNameAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNickNameAlreadyExistsException(String msg) {
        super(msg);
    }

    public CNickNameAlreadyExistsException(){
        super();
    }

}
