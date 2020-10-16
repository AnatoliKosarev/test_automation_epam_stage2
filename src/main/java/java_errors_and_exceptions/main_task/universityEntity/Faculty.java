package java_errors_and_exceptions.main_task.universityEntity;

import java.util.List;

public class Faculty {
    private final String facultyName;
    private List<Group> groupList;

    public Faculty(String facultyName) {
        this.facultyName = facultyName;
    }

    public Faculty(String facultyName, List<Group> groupList) {
        this.facultyName = facultyName;
        this.groupList = groupList;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

}
