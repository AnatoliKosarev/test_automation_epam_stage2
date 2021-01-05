package java_errors_and_exceptions.main_task.universityEntity;

import java_errors_and_exceptions.main_task.universityContstants.Faculties;
import java_errors_and_exceptions.main_task.universityContstants.courses.FacultyCourses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private final String studentName;
    private Map<FacultyCourses, List<Integer>> studentGrades;

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public Map<FacultyCourses, List<Integer>> getStudentGrades() {
        return studentGrades;
    }

    public void setStudentGrades() {
        studentGrades = new HashMap<>();
        for (Faculties faculty : Faculties.values()) {
            for (FacultyCourses course : faculty.getCourseList()) {
                for (Map.Entry<String, List<Integer>> entry : course.getGradeList().entrySet()) {
                    if (entry.getKey().equals(this.studentName)) {
                        studentGrades.put(course, entry.getValue());
                    }
                }
            }
        }
    }
}
