package Exercises.ExceptionExercise.Exceptions;

public class BalanceUnderrunException extends IllegalArgumentException {
    public BalanceUnderrunException(String message) {
        super(message);
    }
}
