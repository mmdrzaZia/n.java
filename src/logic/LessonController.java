package logic;

import graphic.EditLessonsPage;

import java.util.ArrayList;

public class LessonController {

    public static String getNumberOfLesson (String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        return String.valueOf(lesson.numberOfLesson);
    }

    public static String getNumberOfUnitsOfLesson (String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        return String.valueOf(lesson.numberOfUnitsOfLesson);
    }

    public static String getTeacherName (String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        return String.valueOf(lesson.teacherName);
    }

    public static String getDepartmentName (String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        return String.valueOf(lesson.departmentName);
    }

    public static String getClassDay (String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        return String.valueOf(lesson.classDay);
    }

    public static String getClassTime (String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        return String.valueOf(lesson.classTime);
    }

    public static String getExamDay (String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        return String.valueOf(lesson.examDate);
    }

    public static String getExamTime (String lessonName) {
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        return String.valueOf(lesson.examTime);
    }

    public static String[][] seeLessonsByUser() {
        ArrayList<Lessons> lessons = Lessons.seeLessons();
        String[][] information = new String[lessons.size()][8];
        for (int i = 0; i < lessons.size(); i++) {
            information[i][0] = lessons.get(i).name;
            information[i][1] = String.valueOf(lessons.get(i).numberOfLesson);
            information[i][2] = String.valueOf(lessons.get(i).numberOfUnitsOfLesson);
            information[i][3] = lessons.get(i).levelOfEducation.toString();
            information[i][4] = lessons.get(i).teacherName;
            information[i][5] = lessons.get(i).departmentName;
            information[i][6] = lessons.get(i).classDay + "-" + lessons.get(i).classTime;
            information[i][7] = lessons.get(i).examDate + "-" + lessons.get(i).examTime;
        }
        return information;
    }

    public static String[][] seeLessonsByUser(String filter) {
        ArrayList<Lessons> lessons = Lessons.seeLessons(filter);
        String[][] information = new String[lessons.size()][8];
        for (int i = 0; i < lessons.size(); i++) {
            information[i][0] = lessons.get(i).name;
            information[i][1] = String.valueOf(lessons.get(i).numberOfLesson);
            information[i][2] = String.valueOf(lessons.get(i).numberOfUnitsOfLesson);
            information[i][3] = lessons.get(i).levelOfEducation.toString();
            information[i][4] = lessons.get(i).teacherName;
            information[i][5] = lessons.get(i).departmentName;
            information[i][6] = lessons.get(i).classDay + "-" + lessons.get(i).classTime;
            information[i][7] = lessons.get(i).examDate + "-" + lessons.get(i).examTime;
        }
        return information;
    }

    public static String[][] seeWeeklyScheduleByStudents (String username) {
        ArrayList<Lessons> lessons = Lessons.seeWeeklySchedule(username);
        String[][] information = new String[lessons.size()][8];
        for (int i = 0; i < lessons.size(); i++) {
            information[i][0] = lessons.get(i).name;
            information[i][1] = String.valueOf(lessons.get(i).numberOfLesson);
            information[i][2] = String.valueOf(lessons.get(i).numberOfUnitsOfLesson);
            information[i][3] = lessons.get(i).levelOfEducation.toString();
            information[i][4] = lessons.get(i).teacherName;
            information[i][5] = lessons.get(i).departmentName;
            information[i][6] = lessons.get(i).classDay + "-" + lessons.get(i).classTime;
            information[i][7] = lessons.get(i).examDate + "-" + lessons.get(i).examTime;
        }
        return information;
    }

    public static String[][] seeWeeklyScheduleByTeachers (String username) {
        ArrayList<Lessons> lessons = Lessons.seeWeeklySchedule(username);
        String[][] information = new String[lessons.size()][7];
        for (int i = 0; i < lessons.size(); i++) {
            information[i][0] = lessons.get(i).name;
            information[i][1] = String.valueOf(lessons.get(i).numberOfLesson);
            information[i][2] = String.valueOf(lessons.get(i).numberOfUnitsOfLesson);
            information[i][3] = lessons.get(i).levelOfEducation.toString();
            information[i][4] = lessons.get(i).departmentName;
            information[i][5] = lessons.get(i).classDay + "-" + lessons.get(i).classTime;
            information[i][6] = lessons.get(i).examDate + "-" + lessons.get(i).examTime;
        }
        return information;
    }

    public static String[][] seeExamScheduleByStudents (String username) {
        ArrayList<Lessons> lessons = Users.seeExamsSchedule(username);
        String[][] information = new String[lessons.size()][8];
        for (int i = 0; i < lessons.size(); i++) {
            information[i][0] = lessons.get(i).name;
            information[i][1] = String.valueOf(lessons.get(i).numberOfLesson);
            information[i][2] = String.valueOf(lessons.get(i).numberOfUnitsOfLesson);
            information[i][3] = lessons.get(i).levelOfEducation.toString();
            information[i][4] = lessons.get(i).teacherName;
            information[i][5] = lessons.get(i).departmentName;
            information[i][6] = lessons.get(i).classDay + "-" + lessons.get(i).classTime;
            information[i][7] = lessons.get(i).examDate + "-" + lessons.get(i).examTime;
        }
        return information;
    }

    public static String[][] seeExamScheduleByTeachers (String username) {
        ArrayList<Lessons> lessons = Users.seeExamsSchedule(username);
        String[][] information = new String[lessons.size()][7];
        for (int i = 0; i < lessons.size(); i++) {
            information[i][0] = lessons.get(i).name;
            information[i][1] = String.valueOf(lessons.get(i).numberOfLesson);
            information[i][2] = String.valueOf(lessons.get(i).numberOfUnitsOfLesson);
            information[i][3] = lessons.get(i).levelOfEducation.toString();
            information[i][4] = lessons.get(i).departmentName;
            information[i][5] = lessons.get(i).classDay + "-" + lessons.get(i).classTime;
            information[i][6] = lessons.get(i).examDate + "-" + lessons.get(i).examTime;
        }
        return information;
    }

    public static boolean canEditOrRemoveALesson (String educationAssistantUsername,String lessonName) {
        return EducationalAssistant.canEditALesson(educationAssistantUsername,lessonName);
    }

    public static void removeALesson (String lessonName) {
        EducationalAssistant.removeALesson(lessonName);
    }

    public static void editLessonInformation (String lessonName,String numberOfUnitOfLesson,String teacherName,String classDay,String classTime,String examDay,String examTime) {
        EducationalAssistant.editLessonInformation(Integer.parseInt(numberOfUnitOfLesson),lessonName);
        EducationalAssistant.editLessonInformation(teacherName,lessonName);
        EducationalAssistant.editLessonInformation(classDay,lessonName,true,false);
        EducationalAssistant.editLessonInformation(classTime,lessonName,true,true);
        EducationalAssistant.editLessonInformation(examDay,lessonName,false,false);
        EducationalAssistant.editLessonInformation(examTime,lessonName,false,true);
    }

    public static boolean addALesson (String name, String numberOfLesson, String numberOfUnitsOfLesson, String teacherName, String departmentName, String levelOfEducation, String classDay, String classTime, String examDate, String examTime) {
        LevelOfEducation lessonLevelOfEducation = null;
        if (levelOfEducation.equals("MASTER")) {
            lessonLevelOfEducation = LevelOfEducation.MASTER;
        } else if (levelOfEducation.equals("MSC")) {
            lessonLevelOfEducation = LevelOfEducation.MSC;
        } else if (levelOfEducation.equals("PHD")) {
            lessonLevelOfEducation = LevelOfEducation.PHD;
        }
        return EducationalAssistant.addALesson(name,Integer.parseInt(numberOfLesson),Integer.parseInt(numberOfUnitsOfLesson),teacherName,departmentName,lessonLevelOfEducation,classDay,classTime,examDate,examTime);
    }
}
