package java_errors_and_exceptions.main_task.universityContstants.groups;

public enum EconomicsFacultyGroups {
    FOE1(),
    FOE2(),
    FOE3();

    EconomicsFacultyGroups() {
    }

    @Override
    public String toString() {
        return "group " + this.name();
    }
}
