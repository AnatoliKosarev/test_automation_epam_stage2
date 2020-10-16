package java_errors_and_exceptions.main_task.universityContstants.courses;

import java_errors_and_exceptions.main_task.universityContstants.Faculties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum EconomicsFacultyCourses implements FacultyCourses {
    ECONOMICS(getEconomicsGradeList()),
    FINANCE_AND_CREDIT(getFinanceAndCreditGradeList()),
    BUSINESS_INFORMATICS(getBusinessInformaticsGradeList()),
    MANAGEMENT(getManagementGradeList());

    private final Faculties faculty = Faculties.FACULTY_OF_ECONOMICS;
    private final Map<String, List<Integer>> gradeList;

    EconomicsFacultyCourses(Map<String, List<Integer>> gradeList) {
        this.gradeList = gradeList;
    }

    public Map<String, List<Integer>> getGradeList() {
        return gradeList;
    }

    public Faculties getFaculty() {
        return faculty;
    }

    private static Map<String, List<Integer>> getEconomicsGradeList() {
        return Map.of(
                "James Hetfield", new ArrayList<>(Arrays.asList(8, 7, 6, 9)),
                "Kirk Hammett", new ArrayList<>(Arrays.asList(6, 7, 4, 3)),
                "Cliff Burton", new ArrayList<>(Arrays.asList(7, 6, 5, 8)),
                "Lars Ulrich", new ArrayList<>(Arrays.asList(6, 8, 5, 4)));
    }

    private static Map<String, List<Integer>> getFinanceAndCreditGradeList() {
        return Map.of(
                "James Hetfield", new ArrayList<>(Arrays.asList(7, 8, 4)),
                "Kirk Hammett", new ArrayList<>(Arrays.asList(9, 10)),
                "Cliff Burton", new ArrayList<>(Arrays.asList(6, 5, 5)),
                "Lars Ulrich", new ArrayList<>(Arrays.asList(8, 9)));
    }

    private static Map<String, List<Integer>> getBusinessInformaticsGradeList() {
        return Map.of(
                "James Hetfield", new ArrayList<>(Arrays.asList(10, 10, 5)),
                "Kirk Hammett", new ArrayList<>(Arrays.asList(9, 5, 4, 8)),
                "Cliff Burton", new ArrayList<>(Arrays.asList(9, 9, 9)),
                "Lars Ulrich", new ArrayList<>(Arrays.asList(8, 4, 5, 6)));
    }

    private static Map<String, List<Integer>> getManagementGradeList() {
        return Map.of(
                "James Hetfield", new ArrayList<>(Arrays.asList(10, 9, 8)),
                "Kirk Hammett", new ArrayList<>(Arrays.asList(9, 9, 7, 4)),
                "Cliff Burton", new ArrayList<>(Arrays.asList(9, 9, 7)),
                "Lars Ulrich", new ArrayList<>(Arrays.asList(8, 8, 6, 5)));
    }

    @Override
    public String toString() {
        String facultyName = this.name().replaceAll("_", " ").toLowerCase();
        return facultyName.substring(0, 1).toUpperCase() + facultyName.substring(1) + " course";
    }
}
