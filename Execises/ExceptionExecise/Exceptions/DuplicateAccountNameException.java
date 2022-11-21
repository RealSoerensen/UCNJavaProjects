package Exceptions;

public class DuplicateAccountNameException extends IllegalArgumentException {
    public DuplicateAccountNameException(String message) {
        super(message);
    }
}
