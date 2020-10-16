package java_errors_and_exceptions.main_task.exceptions;

public class IncorrectGradeValueException extends RuntimeException {
    public IncorrectGradeValueException(String message) {
        super(message);
    }
}
