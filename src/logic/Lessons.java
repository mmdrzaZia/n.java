package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Lessons {
    String name;
    int numberOfLesson;
    int numberOfUnitsOfLesson;
    String teacherName; //Add to file after adding teachers
    String departmentName;
    LevelOfEducation levelOfEducation;
    boolean haveTeacher;
    String classDay;
    String classTime;
    String examDate;
    String examTime;
    boolean isTemporaryRegistration = true;

    public Lessons(String name, int numberOfLesson, int numberOfUnitsOfLesson, String teacherName, String departmentName, LevelOfEducation levelOfEducation, String classDay, String classTime, String examDate, String examTime) {
        this.name = name;
        this.numberOfLesson = numberOfLesson;
        this.numberOfUnitsOfLesson = numberOfUnitsOfLesson;
        this.teacherName = teacherName;
        this.departmentName = departmentName;
        this.levelOfEducation = levelOfEducation;
        this.classDay = classDay;
        this.classTime = classTime;
        this.examDate = examDate;
        this.examTime = examTime;
    }

    static ArrayList<Lessons> seeWeeklySchedule (String Username) {
        ArrayList<Lessons> weeklySchedule = new ArrayList<>();
        String classInformation = "";
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(Username);
        for (int i = 0; i < user.lessons.size(); i++) {
            Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(user.lessons.get(i));
            if (user.position.equals(positions.PROFESSOR) | user.position.equals(positions.EDUCATIONAL_ASSISTANT) | user.position.equals(positions.BOSS_OF_DEPARTMENT)) {
                classInformation = "name: " + lesson.name + " - " + "classDay: " + lesson.classDay + " - " + "classTime: " + lesson.classTime;
            } else {
                classInformation = "name: " + lesson.name + " - " + "classDay: " + lesson.classDay + " - " + "classTime: " + lesson.classTime + " - " + "teacherName: " + lesson.teacherName;
            }
            weeklySchedule.add(lesson);
        }
        return weeklySchedule;
    }

    static ArrayList<Lessons> seeLessons () {
        ArrayList<Lessons> lessonsInformation = new ArrayList<>();
        File[] lessons = new File("src/LessonsFiles").listFiles();
        for (int i = 0; i < lessons.length; i++) {
            String lessonJson = FilesAndGsonBuilderMethods.getStringJson(lessons[i]);
            Lessons lesson = FilesAndGsonBuilderMethods.getClassJson().fromJson(lessonJson,Lessons.class);
            lessonsInformation.add(lesson);
        }
        return lessonsInformation;
    }

    static ArrayList<Lessons> seeLessons (String filter) {
        ArrayList<Lessons> lessonsInformation = new ArrayList<>();
        File[] lessons = new File("src/LessonsFiles").listFiles();
        for (int i = 0; i < lessons.length; i++) {
            String lessonJson = FilesAndGsonBuilderMethods.getStringJson(lessons[i]);
            Lessons lesson = FilesAndGsonBuilderMethods.getClassJson().fromJson(lessonJson,Lessons.class);
            if (lesson.departmentName.equalsIgnoreCase(filter) || lesson.teacherName.equalsIgnoreCase(filter) || lesson.classTime.equals(filter) || lesson.examTime.equals(filter) || lesson.classDay.equalsIgnoreCase(filter) || lesson.examDate.equalsIgnoreCase(filter) || lesson.levelOfEducation.toString().equalsIgnoreCase(filter) || String.valueOf(lesson.numberOfUnitsOfLesson).equals(filter)) {
                lessonsInformation.add(lesson);
            }
        }
        return lessonsInformation;
    }

    static double averageOfLesson (String lessonName) {
        double average = 0;
        ArrayList<String> studentsNames = seeStudentsOfALesson(lessonName);
        for (int i = 0; i < studentsNames.size(); i++) {
            Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentsNames.get(i));
            for (Map.Entry<String, Double> entry : student.scores.entrySet()) {
                if (entry.getKey().equals(lessonName)) {
                    average += entry.getValue();
                    break;
                }
            }
        }
        return average/(studentsNames.size());
    }

    static double averageOfLessonWithoutFailedStudents (String lessonName) {
        double average = 0;
        ArrayList<String> studentsNames = seeStudentsOfALesson(lessonName);
        int numberOfPassedStudents = 0;
        for (int i = 0; i < studentsNames.size(); i++) {
            Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentsNames.get(i));
            for (Map.Entry<String, Double> entry : student.scores.entrySet()) {
                if (entry.getKey().equals(lessonName)) {
                    if (entry.getValue() >= 10) {
                        average += entry.getValue();
                        ++numberOfPassedStudents;
                    }
                    break;
                }
            }
        }
        return average/numberOfPassedStudents;
    }

    static int numberOfFailedStudents (String lessonName,boolean wantNumberOfFailedStudents) {
        int numberOfFailedStudents = 0;
        int numberOfPassedStudents = 0;
        ArrayList<String> studentsNames = seeStudentsOfALesson(lessonName);
        for (int i = 0; i < studentsNames.size(); i++) {
            Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentsNames.get(i));
            for (Map.Entry<String, Double> entry : student.scores.entrySet()) {
                if (entry.getKey().equals(lessonName)) {
                    if (entry.getValue() < 10) {
                        ++numberOfFailedStudents;
                        break;
                    } else {
                        ++numberOfPassedStudents;
                        break;
                    }
                }
            }
        }
        if (wantNumberOfFailedStudents) {
            return numberOfFailedStudents;
        } else {
            return numberOfPassedStudents;
        }
    }

    static ArrayList<String> seeStudentsOfALesson (String lessonName) {
        ArrayList<String> studentsNames = new ArrayList<>();
        File[] studentsFiles = new File("src/UserFiles").listFiles();
        for (int i = 0; i < studentsFiles.length; i++) {
            String information = FilesAndGsonBuilderMethods.getStringJson(studentsFiles[i]);
            Users user = FilesAndGsonBuilderMethods.getClassJson().fromJson(information,Users.class);
            if ((user.position.equals(positions.MASTER)) | (user.position.equals(positions.MSC)) | (user.position.equals(positions.PHD))) {
                for (int j = 0; j < user.lessons.size(); j++) {
                    if (user.lessons.get(j).equals(lessonName)) {
                        studentsNames.add(user.completeName);
                        break;
                    }
                }
            }
        }
        return studentsNames;
    }
}
