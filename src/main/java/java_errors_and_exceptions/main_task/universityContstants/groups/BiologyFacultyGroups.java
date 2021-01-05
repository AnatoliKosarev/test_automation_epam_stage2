package java_errors_and_exceptions.main_task.universityContstants.groups;

import java_errors_and_exceptions.main_task.universityContstants.Faculties;

public enum BiologyFacultyGroups implements Groups {
    FOB1(),
    FOB2();

    private final Faculties facultyName = Faculties.FACULTY_OF_BIOLOGY;

    BiologyFacultyGroups() {
    }

    public Faculties getFacultyName() {
        return facultyName;
    }

    @Override
    public String toString() {
        return "group " + this.name();
    }
}
