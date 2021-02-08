package java_errors_and_exceptions.main_task;

import java_errors_and_exceptions.main_task.exceptions.NoFacultiesInUniversityException;
import java_errors_and_exceptions.main_task.exceptions.NoGroupsForFacultyException;
import java_errors_and_exceptions.main_task.exceptions.NoStudentsInGroupException;
import java_errors_and_exceptions.main_task.exceptions.Validator;
import java_errors_and_exceptions.main_task.universityContstants.courses.FacultyCourses;
import java_errors_and_exceptions.main_task.universityContstants.groups.BiologyFacultyGroups;
import java_errors_and_exceptions.main_task.universityContstants.groups.EconomicsFacultyGroups;
import java_errors_and_exceptions.main_task.universityContstants.groups.Groups;
import java_errors_and_exceptions.main_task.universityEntity.Faculty;
import java_errors_and_exceptions.main_task.universityEntity.Group;
import java_errors_and_exceptions.main_task.universityEntity.Student;
import java_errors_and_exceptions.main_task.universityEntity.University;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UniversityGradeHandler {
    Validator validator = new Validator();

    public void setStudentGrades(University university) throws NoGroupsForFacultyException,
            NoStudentsInGroupException, NoFacultiesInUniversityException {
        try {
            for (Faculty faculty : university.getFacultyList()) {
                try {
                    for (Group group : faculty.getGroupList()) {
                        try {
                            for (Student student : group.getStudentList()) {
                                student.setStudentGrades();
                                validator.validateStudentCourseListNotEmpty(student);
                                validator.validateGrade(student);
                            }
                        } catch (NullPointerException e) {
                            throw new NoStudentsInGroupException("Error: " + faculty.getFacultyName()
                                    + " " + group.getGroupName() + " has no students assigned to it.", e);
                        }
                    }
                } catch (NullPointerException e) {
                    throw new NoGroupsForFacultyException("Error: " + faculty.getFacultyName() +
                            " has no groups available.", e);
                }
            }
        } catch (NullPointerException e) {
            throw new NoFacultiesInUniversityException("Error: University has no faculties " +
                    "available.", e);
        }
    }

    public void countAverageGradeForStudent(Student student) {
        int counter = 0;
        int gradeSum = 0;
        for (Map.Entry<FacultyCourses, List<Integer>> entry :
                student.getStudentGrades().entrySet()) {
            for (Integer grade : entry.getValue()) {
                gradeSum += grade;
                counter++;
            }
        }
        System.out.printf("Average grade for all courses for %s: %.2f;\n", student.getStudentName()
                , (double) gradeSum / (double) counter);
    }

    public Double countAverageGradeInGroupForCertainCourse(FacultyCourses course, Group group) {
        int counter = 0;
        int gradeSum = 0;

        for (Student student : group.getStudentList()) {
            for (Map.Entry<FacultyCourses, List<Integer>> entry :
                    student.getStudentGrades().entrySet()) {
                if (entry.getKey().equals(course)) {
                    for (Integer grade : entry.getValue()) {
                        gradeSum += grade;
                        counter++;
                    }
                }
            }
        }

        if (gradeSum > 0 && counter > 0) {
            return (double)gradeSum / (double) counter;
        } else {
            return 0.0;
        }
    }

    public void countAverageGradeForCertainCourseGroupFaculty(FacultyCourses course, Group group) {
        double averageGrade;
        String groupFacultyName = null;
        List<Groups> groupList = new ArrayList<>();
        groupList.addAll(Arrays.asList(BiologyFacultyGroups.values()));
        groupList.addAll(Arrays.asList(EconomicsFacultyGroups.values()));

        for (Groups facultyGroup : groupList) {
            if (facultyGroup.toString().equals(group.getGroupName())) {
                groupFacultyName = facultyGroup.getFacultyName().toString();
            }
        }

        if (groupFacultyName != null) {
            validator.validateCourseAndGroupCorrespondence(course, group, groupFacultyName);
        }

        averageGrade = countAverageGradeInGroupForCertainCourse(course, group);

        System.out.printf("Average grade for %s in %s of %s: %.2f;\n",
                course.toString(), group.getGroupName(), groupFacultyName,
                averageGrade);
    }

    public void countAverageCourseGradeForUniversity(University university, FacultyCourses course) {
        int groupCounter = 0;
        double groupsAverageGradeForCourse = 0.0;
        for (Faculty faculty : university.getFacultyList()) {
            for (Group group : faculty.getGroupList()) {
                double temp = countAverageGradeInGroupForCertainCourse(course,
                        group);
                groupsAverageGradeForCourse += temp;
                if (temp > 0) {
                    groupCounter++;
                }
            }
        }
        System.out.printf("Average grade for University in %s: %.2f;\n", course.toString(),
                groupsAverageGradeForCourse / (double) groupCounter);
    }
}
