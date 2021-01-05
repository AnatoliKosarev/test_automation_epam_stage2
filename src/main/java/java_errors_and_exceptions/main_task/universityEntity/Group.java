package java_errors_and_exceptions.main_task.universityEntity;

import java_errors_and_exceptions.main_task.universityContstants.Faculties;

import java.util.List;

public class Group {
    private final String groupName;
    private List<Student> studentList;

    public Group(String groupName, Faculties facultyName) {
        this.groupName = groupName;
    }

    public Group(String groupName, List<Student> studentList) {
        this.groupName = groupName;
        this.studentList = studentList;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

}
