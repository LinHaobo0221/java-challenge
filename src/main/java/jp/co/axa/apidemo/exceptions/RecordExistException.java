package jp.co.axa.apidemo.exceptions;

public class RecordExistException extends RuntimeException{
    public RecordExistException() {
        super("record already existed");
    }
}
