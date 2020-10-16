package java_errors_and_exceptions.main_task.universityEntity;

public class University {
    private Faculty[] facultyList;

    public University() {}

    public University(Faculty ... facultyList) {
        this.facultyList = facultyList;
    }

    public Faculty[] getFacultyList() {
        return facultyList;
    }

}
