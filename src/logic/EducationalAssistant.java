package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EducationalAssistant extends Teachers {


    public EducationalAssistant(String username, String password, positions position, String completeName, String email, ArrayList<String> lessons, String departmentName, String nationalCode, String phoneNumber, String universityName, TeacherPosition teacherPosition, int roomNumber, String teacherNumber) {
        super(username, password, position, completeName, email, lessons, departmentName, nationalCode, phoneNumber, universityName, teacherPosition, roomNumber, teacherNumber);
    }

    static boolean addALesson (String name, int numberOfLesson, int numberOfUnitsOfLesson, String teacherName, String departmentName, LevelOfEducation levelOfEducation, String classDay, String classTime, String examDate, String examTime) {
        if (FilesAndGsonBuilderMethods.findFileWhitName("src/LessonsFiles",name) == null) {
            Lessons lesson = new Lessons(name, numberOfLesson, numberOfUnitsOfLesson, teacherName, departmentName, levelOfEducation,classDay,classTime,examDate,examTime);
            String information = FilesAndGsonBuilderMethods.getClassJson().toJson(lesson);
            String pathLessonFile = "src/LessonsFiles/" + name + ".txt";
            File lessonFile = new File(pathLessonFile);
            try {
                lessonFile.createNewFile();
                FileWriter fileWriter = new FileWriter(lessonFile);
                fileWriter.write(information);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    static void removeALesson (String lessonName) {
        File[] userFiles = new File("src/UserFiles").listFiles();
        for (int i = 0; i < userFiles.length; i++) {
            String information = FilesAndGsonBuilderMethods.getStringJson(userFiles[i]);
            Users user = FilesAndGsonBuilderMethods.getClassJson().fromJson(information,Users.class);
            for (int j = 0; j < user.lessons.size(); j++) {
                if (user.lessons.get(i).equals(lessonName)) {
                    user.lessons.remove(i);
                    break;
                }
            }
        }
        File lessonFile = FilesAndGsonBuilderMethods.findFileWhitName("src/LessonsFiles",lessonName);
        lessonFile.delete();
    }

    static void editLessonInformation (int numberOfUnitsOfLesson,String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        File lessonFile = FilesAndGsonBuilderMethods.findFileWhitName("src/LessonsFiles",lessonName);
        lesson.numberOfUnitsOfLesson = numberOfUnitsOfLesson;
        String newLessonInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(lesson);
        FilesAndGsonBuilderMethods.updateFile(lessonFile,newLessonInformation);
    }

    static void editLessonInformation (String teacherName,String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        File lessonFile = FilesAndGsonBuilderMethods.findFileWhitName("src/LessonsFiles",lessonName);
        if (lesson.haveTeacher) {
            Teachers.removeALesson(lessonName, lesson.teacherName);
        } else {
            lesson.haveTeacher = true;
        }
        lesson.teacherName = teacherName;
        Teachers.addALesson(lessonName,teacherName);
        String newLessonInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(lesson);
        FilesAndGsonBuilderMethods.updateFile(lessonFile,newLessonInformation);
    }

    static void editLessonInformation (String time,String lessonName,boolean isClassTime) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        File lessonFile = FilesAndGsonBuilderMethods.findFileWhitName("src/LessonsFiles",lessonName);
        if (isClassTime) {
            lesson.classTime = time;
        } else {
            lesson.examTime = time;
        }
        String newLessonInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(lesson);
        FilesAndGsonBuilderMethods.updateFile(lessonFile,newLessonInformation);
    }

    static boolean canEditALesson (String educationAssistantUsername,String lessonName) {
        EducationalAssistant educationalAssistant = FilesAndGsonBuilderMethods.convertFileToEducationalAssistant(educationAssistantUsername);
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        if (educationalAssistant.departmentName.equals(lesson.departmentName)) {
            return true;
        }
        return false;
    }

    static ArrayList<String> seeLessonsOfADepartment (String educationalAssistantUsername) {
        EducationalAssistant educationalAssistant = FilesAndGsonBuilderMethods.convertFileToEducationalAssistant(educationalAssistantUsername);
        Department department = FilesAndGsonBuilderMethods.convertFileToDepartment(educationalAssistant.departmentName);
        return department.lessons;
    }

    static HashMap<String,Double> seeScoresOfAStudent (String studentNameOrStudentNumber) {
        Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentNameOrStudentNumber);
        return student.scores;
    }

    static double seeTotalAverageOfAStudent (String studentNameOrStudentNumber) {
        Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentNameOrStudentNumber);
        return student.totalAverage;
    }


}
