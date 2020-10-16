package java_errors_and_exceptions.main_task.exceptions;

public class NoStudentsInGroupException extends Exception {
    public NoStudentsInGroupException(String message, Throwable cause) {
        super(message, cause);
    }
}
