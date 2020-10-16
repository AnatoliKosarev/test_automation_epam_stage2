package java_errors_and_exceptions.main_task.universityEntity;

import java_errors_and_exceptions.main_task.universityContstants.Faculties;

import java.util.List;

public class Group {
    private final String groupName;
    private final Faculties facultyName;
    private List<Student> studentList;

    public Group(String groupName, Faculties facultyName) {
        this.groupName = groupName;
        this.facultyName = facultyName;
    }

    public Group(String groupName, Faculties facultyName, List<Student> studentList) {
        this.groupName = groupName;
        this.facultyName = facultyName;
        this.studentList = studentList;
    }

    public String getGroupName() {
        return groupName;
    }

    public Faculties getFacultyName() {
        return facultyName;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

}
