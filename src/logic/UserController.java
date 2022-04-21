package logic;

import java.util.ArrayList;

public class UserController {
    static Users user;
    static Students student;
    static Teachers teacher;

    public static boolean login (String username,String password) {
        if (Login.checkUsernameForLogin(username,password)) {
            user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
            if (user.position.equals(positions.PROFESSOR) | user.position.equals(positions.EDUCATIONAL_ASSISTANT) | user.position.equals(positions.BOSS_OF_DEPARTMENT)) {
                teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(username);
            } else {
                student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
            }
        }
        return Login.checkUsernameForLogin(username,password);
    }

    public static int determineTheTypeOfUser () {
        if (user.position.equals(positions.MASTER)) {
            return 1;
        } else if (user.position.equals(positions.MSC)) {
            return 2;
        } else if (user.position.equals(positions.PHD)) {
            return 3;
        } else if (user.position.equals(positions.PROFESSOR)) {
            return 4;
        } else if (user.position.equals(positions.EDUCATIONAL_ASSISTANT)) {
            return 5;
        } else {
            return 6;
        }
    }

    public static String getUserCompleteName () {
        return user.completeName;
    }

    public static String getUserEmail () {
        return user.email;
    }

    public static String getStudyingCondition () {
        if (student.studentCondition.equals(StudentCondition.STUDYING)) {
            if (student.position.equals(positions.MASTER)) {
                return "Studying (Master)";
            } else if (student.position.equals(positions.MSC)) {
                return "Studying (Msc)";
            } else {
                return "Studying (Phd)";
            }
        } else if (student.studentCondition.equals(StudentCondition.GRADUATED)) {
            return "Graduated";
        } else {
            return "Withdrawal from education";
        }
    }

    public static String getSupervisorName () {
        return student.supervisorName;
    }

    public static String[][] seeListOfTeachersByStudent () {
        ArrayList<Teachers> teachers = Teachers.seeTeachers();
        String[][] information = new String[teachers.size()][6];
        for (int i = 0; i < teachers.size(); i++) {
            information[i][0] = teachers.get(i).completeName;
            information[i][1] = teachers.get(i).email;
            information[i][2] = teachers.get(i).departmentName;
            information[i][3] = String.valueOf(teachers.get(i).roomNumber);
            information[i][4] = teachers.get(i).teacherPosition.toString();
            information[i][5] = teachers.get(i).ATeacherLessons();
        }
        return information;
    }

    public static String[][] seeListOfTeachersByStudent (String filter) {
        ArrayList<Teachers> teachers = Teachers.seeTeachers(filter);
        String[][] information = new String[teachers.size()][6];
        for (int i = 0; i < teachers.size(); i++) {
            information[i][0] = teachers.get(i).completeName;
            information[i][1] = teachers.get(i).email;
            information[i][2] = teachers.get(i).departmentName;
            information[i][3] = String.valueOf(teachers.get(i).roomNumber);
            information[i][4] = teachers.get(i).teacherPosition.toString();
            information[i][5] = teachers.get(i).ATeacherLessons();
        }
        return information;
    }


}
