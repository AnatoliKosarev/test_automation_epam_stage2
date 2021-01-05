package java_errors_and_exceptions.main_task.universityContstants.groups;

import java_errors_and_exceptions.main_task.universityContstants.Faculties;

public enum EconomicsFacultyGroups implements Groups{
    FOE1(),
    FOE2(),
    FOE3();

    private final Faculties facultyName = Faculties.FACULTY_OF_ECONOMICS;

    EconomicsFacultyGroups() {
    }

    public Faculties getFacultyName() {
        return facultyName;
    }

    @Override
    public String toString() {
        return "group " + this.name();
    }
}
