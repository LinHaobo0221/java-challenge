package jp.co.axa.apidemo.exceptions;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException() {
        super("record not found");
    }
}
