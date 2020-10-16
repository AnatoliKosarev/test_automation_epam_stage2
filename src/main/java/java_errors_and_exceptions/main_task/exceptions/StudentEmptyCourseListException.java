package java_errors_and_exceptions.main_task.exceptions;

public class StudentEmptyCourseListException extends RuntimeException {
    public StudentEmptyCourseListException(String message) {
        super(message);
    }
}
