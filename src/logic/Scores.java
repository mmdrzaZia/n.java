package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scores {

    static boolean changeToConstantScore (String lessonName) {
        if (canPermanentRegistration(lessonName)) {
            ArrayList<String> studentsNames = Lessons.seeStudentsOfALesson(lessonName);
            for (int i = 0; i < studentsNames.size(); i++) {
                Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentsNames.get(i));
                File studentFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles",student.username);
                for (Map.Entry<String, Double> entry : student.temporaryScores.entrySet()) {
                    if (entry.getKey().equals(lessonName)) {
                        student.scores.put(entry.getKey(), entry.getValue());
                        student.temporaryScores.remove(lessonName);
                        student.totalAverage = student.calculateTotalAverage();
                        String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(student);
                        FilesAndGsonBuilderMethods.updateFile(studentFile,newInformation);
                        break;
                    }
                }
            }
            Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
            lesson.isTemporaryRegistration = false;
            String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(lesson);
            File lessonFile = FilesAndGsonBuilderMethods.findFileWithName("src/LessonsFiles",lessonName);
            FilesAndGsonBuilderMethods.updateFile(lessonFile,newInformation);
            return true;
        } else {
            return false;
        }
    }


    static String seeStudentsScoresOfALesson (String studentName,String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentName);
        if (lesson.isTemporaryRegistration) {
            for (Map.Entry<String, Double> entry : student.temporaryScores.entrySet()) {
                if (entry.getKey().equals(lessonName)) {
                    return String.valueOf(entry.getValue());
                }
            }
            return "N/A";
        } else {
            for (Map.Entry<String, Double> entry : student.scores.entrySet()) {
                if (entry.getKey().equals(lessonName)) {
                    return String.valueOf(entry.getValue());
                }
            }
            return "N/A";
        }
    }

    static double setTemporaryScoreForStudent (String studentName, String lessonName, double score) {
        if (checkThePeriodOfScore(score)) {
            Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentName);
            File studentFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles", student.username);
            double rondScore = rondScore(score);
            student.temporaryScores.put(lessonName, rondScore);
            String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(student);
            FilesAndGsonBuilderMethods.updateFile(studentFile, newInformation);
            return rondScore;
        } else {
            return -1;
        }
    }

    static boolean canPermanentRegistration (String lessonName) {
        boolean result = true;
        ArrayList<String> studentsNames = Lessons.seeStudentsOfALesson(lessonName);
        for (int i = 0; i < studentsNames.size(); i++) {
            boolean exist = false;
            Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentsNames.get(i));
            for (Map.Entry<String, Double> entry : student.temporaryScores.entrySet()) {
                if (entry.getKey().equals(lessonName)) {
                    exist = true;
                }
            }
            if (!exist) {
                result = false;
                break;
            }
        }
        return result;
    }

    static HashMap<String,Double> seeScoresOfASpecificStudent (String studentName) {
        Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentName);
        return student.temporaryScores;
    }

    static boolean checkThePeriodOfScore (double score) {
        if (score >= 0 && score <= 20) {
            return true;
        } else {
            return false;
        }
    }

    static double rondScore (double score) {
        String stringOfScore = String.valueOf(score);
        String decimalPartOfScore = "";
        String integerPartOfScore = "";
        String rondScore = "";
        for (int i = 0; i < stringOfScore.length(); i++) {
            if (stringOfScore.charAt(i) == '.') {
                decimalPartOfScore = stringOfScore.substring(i+1);
                integerPartOfScore = stringOfScore.substring(0,i);
                break;
            }
        }
        while (decimalPartOfScore.length() != 5) {
            decimalPartOfScore += "0";
        }
        if (Integer.parseInt(decimalPartOfScore) == 0) {
            decimalPartOfScore = "0";
        } else if (Integer.parseInt(decimalPartOfScore) < 25000 & Integer.parseInt(decimalPartOfScore) <= 25000) {
            decimalPartOfScore = "25";
        } else if (Integer.parseInt(decimalPartOfScore) > 25000 & Integer.parseInt(decimalPartOfScore) <= 50000) {
            decimalPartOfScore = "5";
        } else if (Integer.parseInt(decimalPartOfScore) > 50000 & Integer.parseInt(decimalPartOfScore) <= 75000) {
            decimalPartOfScore = "75";
        } else if (Integer.parseInt(decimalPartOfScore) > 75000) {
            integerPartOfScore = String.valueOf(Integer.parseInt(integerPartOfScore) + 1);
            decimalPartOfScore = "0";
        }
        rondScore = integerPartOfScore + "." + decimalPartOfScore;
        return Double.parseDouble(rondScore);
    }
}
