package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class ScoresAndReportCardController {

    public static int getNumberOfPassedUnits (String username) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
        return Students.seeNumberOfPassedUnits(student.completeName);
    }

    public static double getTotalAverageScore (String username) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
        return student.calculateTotalAverage();
    }

    public static String[][] seeFinalScores (String username) {
        boolean haveFinalScore;
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
        String[][] finalScores = new String[student.lessons.size()][2];
        for (int i = 0; i < student.lessons.size(); i++) {
            haveFinalScore = false;
            finalScores[i][0] = student.lessons.get(i);
            for (Map.Entry<String, Double> entry : student.scores.entrySet()) {
                if (entry.getKey().equals(student.lessons.get(i))) {
                    finalScores[i][1] = String.valueOf(entry.getValue());
                    haveFinalScore = true;
                }
            }
            if (!haveFinalScore) {
                finalScores[i][1] = "N/A";
            }
        }
        return finalScores;
    }

    public static String[][] seeTemporaryScoresByStudents (String username) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
        File[] requestsFiles = new File("src/RequestsFiles").listFiles();
        String[][] temporaryScores = new String[student.temporaryScores.size()][5];
        int index = 0;
        for (Map.Entry<String, Double> entry : student.temporaryScores.entrySet()) {
            temporaryScores[index][0] = entry.getKey();
            temporaryScores[index][1] = String.valueOf(entry.getValue());
            temporaryScores[index][4] = "Register objection";
            for (int i = 0; i < requestsFiles.length; i++) {
                String information = FilesAndGsonBuilderMethods.getStringJson(requestsFiles[i]);
                Requests request = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, Requests.class);
                if (request.typeOfRequest.equals(TypeOfRequest.OBJECTION_TO_THE_TEMPORARY_SCORE)) {
                    ObjectionToTheTemporaryScore objectionToTheTemporaryScore = FilesAndGsonBuilderMethods.getClassJson().fromJson(information,ObjectionToTheTemporaryScore.class);
                    if (objectionToTheTemporaryScore.studentName.equals(student.completeName) && objectionToTheTemporaryScore.lessonName.equals(entry.getKey())) {
                        temporaryScores[index][2] = objectionToTheTemporaryScore.objection;
                        temporaryScores[index][3] = objectionToTheTemporaryScore.responseText;
                        break;
                    }
                }
            }
            ++index;
        }
        return temporaryScores;
    }

    public static String[][] seeTemporaryScoresOfAStudentsByEducationalAssistant (String studentName) {
        Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentName);
        File[] requestsFiles = new File("src/RequestsFiles").listFiles();
        String[][] temporaryScores = new String[student.temporaryScores.size()][4];
        int index = 0;
        for (Map.Entry<String, Double> entry : student.temporaryScores.entrySet()) {
            temporaryScores[index][0] = entry.getKey();
            temporaryScores[index][1] = String.valueOf(entry.getValue());
            for (int i = 0; i < requestsFiles.length; i++) {
                String information = FilesAndGsonBuilderMethods.getStringJson(requestsFiles[i]);
                Requests request = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, Requests.class);
                if (request.typeOfRequest.equals(TypeOfRequest.OBJECTION_TO_THE_TEMPORARY_SCORE)) {
                    ObjectionToTheTemporaryScore objectionToTheTemporaryScore = FilesAndGsonBuilderMethods.getClassJson().fromJson(information,ObjectionToTheTemporaryScore.class);
                    if (objectionToTheTemporaryScore.studentName.equals(student.completeName) && objectionToTheTemporaryScore.lessonName.equals(entry.getKey())) {
                        temporaryScores[index][2] = objectionToTheTemporaryScore.objection;
                        temporaryScores[index][3] = objectionToTheTemporaryScore.responseText;
                        break;
                    }
                }
            }
            ++index;
        }
        return temporaryScores;
    }

    public static String[][] seeTemporaryScoresByTeachers (String lessonName) {
        ArrayList<String> studentsName = Lessons.seeStudentsOfALesson(lessonName);
        String[][] temporaryScores = new String[studentsName.size()][7];
        File[] requestsFiles = new File("src/RequestsFiles").listFiles();
        for (int i = 0; i < studentsName.size(); i++) {
            Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentsName.get(i));
            temporaryScores[i][0] = student.completeName;
            temporaryScores[i][1] = student.studentNumber;
            for (Map.Entry<String, Double> entry : student.temporaryScores.entrySet()) {
                if (entry.getKey().equals(lessonName)) {
                    temporaryScores[i][2] = String.valueOf(entry.getValue());
                }
            }
            for (int j = 0; j < requestsFiles.length; j++) {
                String information = FilesAndGsonBuilderMethods.getStringJson(requestsFiles[j]);
                Requests request = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, Requests.class);
                if (request.typeOfRequest.equals(TypeOfRequest.OBJECTION_TO_THE_TEMPORARY_SCORE)) {
                    ObjectionToTheTemporaryScore objectionToTheTemporaryScore = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, ObjectionToTheTemporaryScore.class);
                    if (objectionToTheTemporaryScore.studentName.equals(student.completeName) && objectionToTheTemporaryScore.lessonName.equals(lessonName)) {
                        temporaryScores[i][3] = objectionToTheTemporaryScore.objection;
                        temporaryScores[i][4] = objectionToTheTemporaryScore.responseText;
                        break;
                    }
                }
            }
            temporaryScores[i][5] = "Register answer";
            temporaryScores[i][6] = "Register score";
        }
        return temporaryScores;
    }

    public static String[][] seeTemporaryScoresOfALessonByEducationalAssistant(String lessonName) {
        ArrayList<String> studentsName = Lessons.seeStudentsOfALesson(lessonName);
        String[][] temporaryScores = new String[studentsName.size()][5];
        File[] requestsFiles = new File("src/RequestsFiles").listFiles();
        for (int i = 0; i < studentsName.size(); i++) {
            Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentsName.get(i));
            temporaryScores[i][0] = student.completeName;
            temporaryScores[i][1] = student.studentNumber;
            boolean existScore = false;
            for (Map.Entry<String, Double> entry : student.temporaryScores.entrySet()) {
                if (entry.getKey().equals(lessonName)) {
                    existScore = true;
                    temporaryScores[i][2] = String.valueOf(entry.getValue());
                }
            }
            if (!existScore) {
                temporaryScores[i][2] = "N/A";
            }
            for (int j = 0; j < requestsFiles.length; j++) {
                String information = FilesAndGsonBuilderMethods.getStringJson(requestsFiles[j]);
                Requests request = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, Requests.class);
                if (request.typeOfRequest.equals(TypeOfRequest.OBJECTION_TO_THE_TEMPORARY_SCORE)) {
                    ObjectionToTheTemporaryScore objectionToTheTemporaryScore = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, ObjectionToTheTemporaryScore.class);
                    if (objectionToTheTemporaryScore.studentName.equals(student.completeName) && objectionToTheTemporaryScore.lessonName.equals(lessonName)) {
                        temporaryScores[i][3] = objectionToTheTemporaryScore.objection;
                        temporaryScores[i][4] = objectionToTheTemporaryScore.responseText;
                        break;
                    }
                }
            }
        }
        return temporaryScores;
    }

    public static String[][] seeFinalScoresByTeachers (String lessonName) {
        ArrayList<String> studentsName = Lessons.seeStudentsOfALesson(lessonName);
        String[][] temporaryScores = new String[studentsName.size()][5];
        File[] requestsFiles = new File("src/RequestsFiles").listFiles();
        for (int i = 0; i < studentsName.size(); i++) {
            Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentsName.get(i));
            temporaryScores[i][0] = student.completeName;
            temporaryScores[i][1] = student.studentNumber;
            for (Map.Entry<String, Double> entry : student.scores.entrySet()) {
                if (entry.getKey().equals(lessonName)) {
                    temporaryScores[i][2] = String.valueOf(entry.getValue());
                }
            }
            for (int j = 0; j < requestsFiles.length; j++) {
                String information = FilesAndGsonBuilderMethods.getStringJson(requestsFiles[j]);
                Requests request = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, Requests.class);
                if (request.typeOfRequest.equals(TypeOfRequest.OBJECTION_TO_THE_TEMPORARY_SCORE)) {
                    ObjectionToTheTemporaryScore objectionToTheTemporaryScore = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, ObjectionToTheTemporaryScore.class);
                    if (objectionToTheTemporaryScore.studentName.equals(student.completeName) && objectionToTheTemporaryScore.lessonName.equals(lessonName)) {
                        temporaryScores[i][3] = objectionToTheTemporaryScore.objection;
                        temporaryScores[i][4] = objectionToTheTemporaryScore.responseText;
                        break;
                    }
                }
            }
        }
        return temporaryScores;
    }

    public static boolean isTemporaryOrFinal (String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        return lesson.isTemporaryRegistration;
    }

    public static double setTemporaryScoreForAStudent (String studentName,String lessonName,double score) {
        return Scores.setTemporaryScoreForStudent(studentName,lessonName,score);
    }

    public static boolean registerFinalScoresForALesson (String lessonName) {
        return Scores.changeToConstantScore(lessonName);
    }
}
