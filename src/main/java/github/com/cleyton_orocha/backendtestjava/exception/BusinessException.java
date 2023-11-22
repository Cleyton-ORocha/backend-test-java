package github.com.cleyton_orocha.backendtestjava.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String msg){
        super(msg);
    }
}
