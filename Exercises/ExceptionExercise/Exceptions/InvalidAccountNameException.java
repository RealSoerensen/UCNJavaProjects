package Exercises.ExceptionExercise.Exceptions;

public class InvalidAccountNameException extends IllegalArgumentException {
    public InvalidAccountNameException(String message) {
        super(message);
    }
}
