package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EducationalAssistant extends Teachers {


    public EducationalAssistant(String username, String password, Positions position, String completeName, String email, String departmentName, String nationalCode, String phoneNumber, TeacherPosition teacherPosition, int roomNumber, String teacherNumber) throws NoSuchAlgorithmException {
        super(username, password, position, completeName, email, departmentName, nationalCode, phoneNumber,teacherPosition, roomNumber, teacherNumber);
    }

    static boolean addALesson (String name, int numberOfLesson, int numberOfUnitsOfLesson, String teacherName, String departmentName, LevelOfEducation levelOfEducation, String classDay, String classTime, String examDate, String examTime) {
        if (FilesAndGsonBuilderMethods.findFileWithName("src/LessonsFiles",name) == null) {
            Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
            Teachers.addALesson(name,teacherName);
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
            if (!user.isRemoved) {
                if (user.position.equals(Positions.MASTER) | user.position.equals(Positions.MSC) | user.position.equals(Positions.PHD)) {
                    Students student = FilesAndGsonBuilderMethods.convertFileToStudent(user.username);
                    for (int j = 0; j < student.lessons.size(); j++) {
                        if (student.lessons.get(j).equals(lessonName)) {
                            student.lessons.remove(j);
                            break;
                        }
                    }
                    for (Map.Entry<String, Double> entry : student.temporaryScores.entrySet()) {
                        if (entry.getKey().equals(lessonName)) {
                            student.temporaryScores.remove(lessonName);
                            break;
                        }
                    }
                    for (Map.Entry<String, Double> entry : student.scores.entrySet()) {
                        if (entry.getKey().equals(lessonName)) {
                            student.scores.remove(lessonName);
                            break;
                        }
                    }
                    String newUserInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(student);
                    FilesAndGsonBuilderMethods.updateFile(userFiles[i], newUserInformation);
                } else {
                    Teachers teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(user.username);
                    for (int j = 0; j < teacher.lessons.size(); j++) {
                        if (teacher.lessons.get(j).equals(lessonName)) {
                            teacher.lessons.remove(j);
                            break;
                        }
                    }
                    String newUserInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
                    FilesAndGsonBuilderMethods.updateFile(userFiles[i], newUserInformation);
                }
            }
        }
        File lessonFile = FilesAndGsonBuilderMethods.findFileWithName("src/LessonsFiles",lessonName);
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        lesson.isRemoved = true;
        String newInformation = FilesAndGsonBuilderMethods.getStringJson(lessonFile);
        FilesAndGsonBuilderMethods.updateFile(lessonFile,newInformation);
    }

    static void editLessonInformation (int numberOfUnitsOfLesson,String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        File lessonFile = FilesAndGsonBuilderMethods.findFileWithName("src/LessonsFiles",lessonName);
        lesson.numberOfUnitsOfLesson = numberOfUnitsOfLesson;
        String newLessonInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(lesson);
        FilesAndGsonBuilderMethods.updateFile(lessonFile,newLessonInformation);
    }

    static void editLessonInformation (String teacherName,String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        File lessonFile = FilesAndGsonBuilderMethods.findFileWithName("src/LessonsFiles",lessonName);
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

    static void editLessonInformation (String time,String lessonName,boolean isClassTime,boolean isTime) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        File lessonFile = FilesAndGsonBuilderMethods.findFileWithName("src/LessonsFiles",lessonName);
        if (isTime & isClassTime) {
            lesson.classTime = time;
        } else if (isTime & !isClassTime) {
            lesson.examTime = time;
        } else if (isClassTime) {
            lesson.classDay = time;
        } else {
            lesson.examDate = time;
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
