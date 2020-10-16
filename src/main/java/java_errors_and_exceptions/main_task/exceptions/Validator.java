package java_errors_and_exceptions.main_task.exceptions;

import java_errors_and_exceptions.main_task.universityContstants.Faculties;
import java_errors_and_exceptions.main_task.universityContstants.courses.FacultyCourses;
import java_errors_and_exceptions.main_task.universityEntity.Group;
import java_errors_and_exceptions.main_task.universityEntity.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Validator {

    public void validateGrade(Student student) {
        for (Map.Entry<FacultyCourses, List<Integer>> entry :
                student.getStudentGrades().entrySet()) {
            for (Integer grade : entry.getValue()) {
                if (grade < 0 || grade > 10) {
                    String errorMessage = "Incorrect grade: " + grade + " for " + "student "
                                    + student.getStudentName() + " for " + entry.getKey().toString();
                    errorMessage += grade < 0 ? ". Grade cannot be less than 0." : ". Grade " +
                            "cannot be greater than 10.";
                    throw new IncorrectGradeValueException(errorMessage);
                }
            }
        }
    }

    public void validateStudentCourseListNotEmpty(Student student) {
        if (student.getStudentGrades().isEmpty()) {
            throw new StudentEmptyCourseListException("Error: " + student.getStudentName() + " "
                    + "has no courses assigned. Each student has to have at least one course "
                    + "assigned.");
        }
    }

    public void validateCourseAndGroupCorrespondence(FacultyCourses course, Group group) {
        for (Faculties faculty : Faculties.values()) {
            if (faculty.equals(group.getFacultyName())) {
                if (!Arrays.asList(faculty.getCourseList()).contains(course)) {
                    throw new CourseAndGroupDoNotCorrespondException("Error: " + course.toString()
                            + " and " + group.getGroupName() + " do not correspond. " + course
                            + " is not found" + " " + "in " + group.getFacultyName().toString()
                            + " " + group.getGroupName() + " course list.");
                }
            }
        }
    }
}
