package java_errors_and_exceptions.main_task.universityContstants.groups;

public enum BiologyFacultyGroups implements Groups {
    FOB1(),
    FOB2();

    BiologyFacultyGroups() {
    }

    @Override
    public String toString() {
        return "group " + this.name();
    }
}
