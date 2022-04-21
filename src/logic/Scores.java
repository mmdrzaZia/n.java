package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scores {

    static void changeToConstantScore (String lessonName) {
        ArrayList<String> studentsNames = Lessons.seeStudentsOfALesson(lessonName);
        for (int i = 0; i < studentsNames.size(); i++) {
            Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentsNames.get(i));
            File studentFile = FilesAndGsonBuilderMethods.findFileWhitName("src/UserFiles",student.username);
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
        File lessonFile = FilesAndGsonBuilderMethods.findFileWhitName("src/LessonsFiles",lessonName);
        FilesAndGsonBuilderMethods.updateFile(lessonFile,newInformation);
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

    static void temporaryRegistration (String studentName,String lessonName,double score) {
        Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentName);
        File studentFile = FilesAndGsonBuilderMethods.findFileWhitName("src/UserFiles",student.username);
        student.temporaryScores.put(lessonName,score);
        String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(student);
        FilesAndGsonBuilderMethods.updateFile(studentFile,newInformation);
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
}
