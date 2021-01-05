package java_errors_and_exceptions.main_task;

import java_errors_and_exceptions.main_task.exceptions.NoFacultiesInUniversityException;
import java_errors_and_exceptions.main_task.exceptions.NoGroupsForFacultyException;
import java_errors_and_exceptions.main_task.exceptions.NoStudentsInGroupException;
import java_errors_and_exceptions.main_task.universityEntity.Faculty;
import java_errors_and_exceptions.main_task.universityEntity.Group;
import java_errors_and_exceptions.main_task.universityEntity.Student;
import java_errors_and_exceptions.main_task.universityEntity.University;

import java.util.Arrays;
import java.util.List;

import static java_errors_and_exceptions.main_task.universityContstants.Faculties.*;
import static java_errors_and_exceptions.main_task.universityContstants.courses.EconomicsFacultyCourses.*;
import static java_errors_and_exceptions.main_task.universityContstants.groups.BiologyFacultyGroups.*;
import static java_errors_and_exceptions.main_task.universityContstants.groups.EconomicsFacultyGroups.*;

public class UniversityRunner {
    public static void main(String[] args) throws NoGroupsForFacultyException,
            NoStudentsInGroupException, NoFacultiesInUniversityException {
        UniversityGradeHandler universityGradeHandler = new UniversityGradeHandler();

        /**
         * To test IncorrectGradeValueException change grade in any courses enums (e.g. in
         * universityConstants.courses.EconomicsFacultyCourses) to invalid (grade < 0 || grade > 10)
         */

        Student fob1Student1 = new Student("John Lennon");
        Student fob1Student2 = new Student("Paul McCartney");
        List<Student> fob1GroupStudentList = Arrays.asList(fob1Student1, fob1Student2);

        Student fob2Student1 = new Student("George Harrison");
        Student fob2Student2 = new Student("Ringo Starr");

        /**
         * To test StudentEmptyCourseListException uncomment lines 40-41 and comment line 42
         */
//        Student fob2Student3 = new Student("Sid Vicious");
//        List<Student> fob2GroupStudentList = Arrays.asList(fob2Student1, fob2Student2, fob2Student3);
        List<Student> fob2GroupStudentList = Arrays.asList(fob2Student1, fob2Student2);

        Student foe1Student1 = new Student("James Hetfield");
        Student foe1Student2 = new Student("Kirk Hammett");
        List<Student> foe1GroupStudentList = Arrays.asList(foe1Student1, foe1Student2);

        Student foe2Student1 = new Student("Cliff Burton");
        Student foe2Student2 = new Student("Lars Ulrich");
        List<Student> foe2GroupStudentList = Arrays.asList(foe2Student1, foe2Student2);

        Group fob1Group = new Group(FOB1.toString(), fob1GroupStudentList);
        Group fob2Group = new Group(FOB2.toString(), fob2GroupStudentList);
        List<Group> biologyFacultyGroupList = Arrays.asList(fob1Group, fob2Group);

        Group foe1Group = new Group(FOE1.toString(), foe1GroupStudentList);
        Group foe2Group = new Group(FOE2.toString(), foe2GroupStudentList);

        /**
         * To test NoStudentsInGroupException uncomment line 62-63 and comment line 64
         */
//        Group foe3Group = new Group(FOE3.toString(), FACULTY_OF_ECONOMICS);
//        List<Group> economicsFacultyGroupList = Arrays.asList(foe1Group, foe2Group, foe3Group);
        List<Group> economicsFacultyGroupList = Arrays.asList(foe1Group, foe2Group);

        Faculty biologyFaculty = new Faculty(FACULTY_OF_BIOLOGY.toString(),
                biologyFacultyGroupList);
        Faculty economicsFaculty = new Faculty(FACULTY_OF_ECONOMICS.toString(),
                economicsFacultyGroupList);

        /**
         * To test NoGroupsForFacultyException uncomment line 74, 83-84 and comment line 81
         */
//        Faculty philologicalFaculty = new Faculty(FACULTY_OF_PHILOLOGY.toString());

        /**
         * To test NoFacultiesInUniversityException uncomment line 79 and comment line 81
         */
//        University university = new University();

        University university = new University(biologyFaculty, economicsFaculty);

//        University university = new University(philologicalFaculty, biologyFaculty,
//                economicsFaculty);

        universityGradeHandler.setStudentGrades(university);

        universityGradeHandler.countAverageGradeForStudent(fob1Student1);

        universityGradeHandler.countAverageGradeForCertainCourseGroupFaculty(ECONOMICS,
                foe2Group);

        universityGradeHandler.countAverageCourseGradeForUniversity(university,
                BUSINESS_INFORMATICS);
    }
}
