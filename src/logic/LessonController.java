package logic;

import java.util.ArrayList;

public class LessonController {

    public static String[][] seeLessonsByStudent () {
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

    public static String[][] seeLessonsByStudent (String filter) {
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


}
