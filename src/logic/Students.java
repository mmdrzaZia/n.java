package logic;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Students extends Users {
    String supervisorName;
    String studentNumber;
    int entryYear;
    double totalAverage;
    StudentCondition studentCondition;
    HashMap<String,Double> scores;
    HashMap<String,Double> temporaryScores;

    public Students(String username, String password, Positions position, String completeName, String email, ArrayList<String> lessons, String departmentName, String nationalCode, String phoneNumber, String supervisorName, String studentNumber, int entryYear, StudentCondition studentCondition) throws NoSuchAlgorithmException {
        super(username, password, position, completeName, email, departmentName, nationalCode, phoneNumber);
        this.supervisorName = supervisorName;
        this.studentNumber = studentNumber;
        this.entryYear = entryYear;
        this.studentCondition = studentCondition;
        this.lessons = lessons;
        scores = new HashMap<>();
        temporaryScores = new HashMap<>();
    }

    static Students findStudentFromCompleteNameAndStudentNumber (String studentNameOrStudentNumber) {
        File[] studentFiles = new File("src/UserFiles").listFiles();
        for (int i = 0; i < studentFiles.length; i++) {
            String information = FilesAndGsonBuilderMethods.getStringJson(studentFiles[i]);
            Users user = FilesAndGsonBuilderMethods.getClassJson().fromJson(information,Users.class);
            if (user.position.equals(Positions.MASTER) | user.position.equals(Positions.MSC) | user.position.equals(Positions.PHD)) {
                Students student = FilesAndGsonBuilderMethods.getClassJson().fromJson(information,Students.class);
                if (student.completeName.equals(studentNameOrStudentNumber) | student.studentNumber.equals(studentNameOrStudentNumber)) {
                    return student;
                }
            }
        }
        return null;
    }

    String findPassedLessons () {
        String passLessons = "";
        for (HashMap.Entry<String, Double> entry : scores.entrySet()) {
            if (entry.getValue() >= 10) {
                passLessons += "," + entry.getKey();
            }
        }
        if (passLessons.equals("")) {
            return "nothing";
        } else {
            return passLessons.substring(1);
        }
    }

    String findGradesOfPassedLessons () {
        String grades = "";
        for (Map.Entry<String, Double> entry : scores.entrySet()) {
            if (entry.getValue() >= 10) {
                grades += "," + String.valueOf(entry.getValue());
            }
        }
        if (grades.equals("")) {
            return "under the 10";
        } else {
            return grades.substring(1);
        }
    }

    double calculateTotalAverage () {
        double average = 0;
        int numberOfUnits = 0;
        for (Map.Entry<String, Double> entry : scores.entrySet()) {
            Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(entry.getKey());
            average += (entry.getValue() * lesson.numberOfUnitsOfLesson);
            numberOfUnits += lesson.numberOfUnitsOfLesson;
        }
        if (numberOfUnits == 0) {
            return 0;
        } else {
            return average / numberOfUnits;
        }
    }

    static int seeNumberOfPassedUnits (String studentNameOrStudentNumber) {
        Students student = findStudentFromCompleteNameAndStudentNumber(studentNameOrStudentNumber);
        int numberOfUnits = 0;
        for (Map.Entry<String, Double> entry : student.scores.entrySet()) {
            if (entry.getValue() >= 10) {
                Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(entry.getKey());
                numberOfUnits += lesson.numberOfUnitsOfLesson;
            }
        }
        return numberOfUnits;
    }


}
