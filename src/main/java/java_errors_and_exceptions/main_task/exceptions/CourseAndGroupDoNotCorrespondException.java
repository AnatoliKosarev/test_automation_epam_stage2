package java_errors_and_exceptions.main_task.exceptions;

public class CourseAndGroupDoNotCorrespondException extends RuntimeException {
    public CourseAndGroupDoNotCorrespondException(String message) {
        super(message);
    }
}
