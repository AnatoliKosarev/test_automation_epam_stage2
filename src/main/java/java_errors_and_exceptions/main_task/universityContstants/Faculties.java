package java_errors_and_exceptions.main_task.universityContstants;

import java_errors_and_exceptions.main_task.universityContstants.courses.FacultyCourses;

import static java_errors_and_exceptions.main_task.universityContstants.courses.BiologyFacultyCourses.*;
import static java_errors_and_exceptions.main_task.universityContstants.courses.EconomicsFacultyCourses.*;

public enum Faculties {
    FACULTY_OF_BIOLOGY(MICROBIOLOGY, BIOCHEMISTRY, BIOECOLOGY),
    FACULTY_OF_ECONOMICS(BUSINESS_INFORMATICS, ECONOMICS, FINANCE_AND_CREDIT, MANAGEMENT),
    FACULTY_OF_PHILOLOGY();

    private FacultyCourses[] courseList;

    Faculties(FacultyCourses... courseList) {
        this.courseList = courseList;
    }

    public FacultyCourses[] getCourseList() {
        return courseList;
    }

    public void setCourseList(FacultyCourses[] courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        String facultyName = this.name().replaceAll("_", " ").toLowerCase();
        return facultyName.substring(0, 1).toUpperCase() + facultyName.substring(1);
    }
}
