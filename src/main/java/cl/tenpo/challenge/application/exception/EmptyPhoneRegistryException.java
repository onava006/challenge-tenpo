package cl.tenpo.challenge.application.exception;

public class EmptyPhoneRegistryException extends RuntimeException {

    public EmptyPhoneRegistryException(String message){
        super(message);
    }
}
