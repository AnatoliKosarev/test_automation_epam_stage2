package java_errors_and_exceptions.main_task.universityEntity;

import java_errors_and_exceptions.main_task.universityContstants.courses.BiologyFacultyCourses;
import java_errors_and_exceptions.main_task.universityContstants.courses.EconomicsFacultyCourses;
import java_errors_and_exceptions.main_task.universityContstants.courses.FacultyCourses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java_errors_and_exceptions.main_task.universityContstants.Faculties.FACULTY_OF_BIOLOGY;
import static java_errors_and_exceptions.main_task.universityContstants.Faculties.FACULTY_OF_ECONOMICS;

public class Student {
    private final String studentName;
    private final String facultyName;
    private Map<FacultyCourses, List<Integer>> studentGrades;

    public Student(String studentName, String facultyName) {
        this.studentName = studentName;
        this.facultyName = facultyName;
    }

    public String getStudentName() {
        return studentName;
    }

    public Map<FacultyCourses, List<Integer>> getStudentGrades() {
        return studentGrades;
    }

    public void setStudentGrades() {
        studentGrades = new HashMap<>();
        if (facultyName.equals(FACULTY_OF_BIOLOGY.toString())) {
            for (BiologyFacultyCourses course : BiologyFacultyCourses.values()) {
                for (Map.Entry<String, List<Integer>> entry : course.getGradeList().entrySet()) {
                    if (entry.getKey().equals(this.studentName)) {
                        studentGrades.put(course, entry.getValue());
                    }
                }
            }
        }
        else if (facultyName.equals(FACULTY_OF_ECONOMICS.toString())) {
            for (EconomicsFacultyCourses course : EconomicsFacultyCourses.values()) {
                for (Map.Entry<String, List<Integer>> entry : course.getGradeList().entrySet()) {
                    if (entry.getKey().equals(this.studentName)) {
                        studentGrades.put(course, entry.getValue());
                    }
                }
            }
        }
    }
}
