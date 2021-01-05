package java_errors_and_exceptions.main_task.universityContstants.courses;

import java_errors_and_exceptions.main_task.universityContstants.Faculties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum BiologyFacultyCourses implements FacultyCourses {
    MICROBIOLOGY(getMicrobiologyGradeList()),
    BIOCHEMISTRY(getBiochemistryGradeList()),
    BIOECOLOGY(getBioecologyGradeList());

    private final Faculties faculty = Faculties.FACULTY_OF_BIOLOGY;
    private final Map<String, List<Integer>> gradeList;

    BiologyFacultyCourses(Map<String, List<Integer>> gradeList) {
        this.gradeList = gradeList;
    }

    public Map<String, List<Integer>> getGradeList() {
        return gradeList;
    }

    public Faculties getFaculty() {
        return faculty;
    }

    private static Map<String, List<Integer>> getMicrobiologyGradeList() {
        return Map.of(
                "John Lennon", new ArrayList<>(Arrays.asList(9, 9, 5)),
                "Paul McCartney", new ArrayList<>(Arrays.asList(5, 6, 7, 4)),
                "George Harrison", new ArrayList<>(Arrays.asList(7, 8, 4)),
                "Ringo Starr", new ArrayList<>(Arrays.asList(6, 4, 4, 5)));
    }

    private static Map<String, List<Integer>> getBiochemistryGradeList() {
        return Map.of(
                "John Lennon", new ArrayList<>(Arrays.asList(10, 6, 10, 3)),
                "Paul McCartney", new ArrayList<>(Arrays.asList(9, 7)),
                "George Harrison", new ArrayList<>(Arrays.asList(5, 7, 4)),
                "Ringo Starr", new ArrayList<>(Arrays.asList(5, 8)));
    }

    private static Map<String, List<Integer>> getBioecologyGradeList() {
        return Map.of(
                "John Lennon", new ArrayList<>(Arrays.asList(9, 10, 8)),
                "Paul McCartney", new ArrayList<>(Arrays.asList(9, 5, 6, 4)),
                "George Harrison", new ArrayList<>(Arrays.asList(8, 5, 7)),
                "Ringo Starr", new ArrayList<>(Arrays.asList(2, 3, 4, 3))
        );
    }

    @Override
    public String toString() {
        String facultyName = this.name().replaceAll("_", " ").toLowerCase();
        return facultyName.substring(0, 1).toUpperCase() + facultyName.substring(1) + " course";
    }
}
