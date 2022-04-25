package logic;

import java.io.File;
import java.util.ArrayList;

public class UserController {
    static Users user;
    static Students student;
    static Teachers teacher;

    public static boolean login (String username,String password) {
        if (Login.checkUsernameForLogin(username,password)) {
            user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
            if (user.position.equals(Positions.PROFESSOR) | user.position.equals(Positions.EDUCATIONAL_ASSISTANT) | user.position.equals(Positions.BOSS_OF_DEPARTMENT)) {
                teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(username);
            } else {
                student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
            }
        }
        return Login.checkUsernameForLogin(username,password);
    }

    public static int determineTheTypeOfUser () {
        if (user.position.equals(Positions.MASTER)) {
            return 1;
        } else if (user.position.equals(Positions.MSC)) {
            return 2;
        } else if (user.position.equals(Positions.PHD)) {
            return 3;
        } else if (user.position.equals(Positions.PROFESSOR)) {
            return 4;
        } else if (user.position.equals(Positions.EDUCATIONAL_ASSISTANT)) {
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

    public static String getTeacherEmail (String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        return teacher.email;
    }

    public static String getUserNationalCode () {
        return user.nationalCode;
    }

    public static String getTeacherNationalCode (String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        return teacher.nationalCode;
    }

    public static String getUserPhoneNumber () {
        return user.phoneNumber;
    }

    public static String getTeacherPhoneNumber (String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        return teacher.phoneNumber;
    }

    public static String getUserDepartmentName () {
        return user.departmentName;
    }

    public static String getTeacherDepartmentName (String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        return teacher.departmentName;
    }

    public static String getStudentNumber() {
        return student.studentNumber;
    }

    public static String getTeacherNumber () {
        return teacher.teacherNumber;
    }

    public static String getTeacherNumber (String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        return teacher.teacherNumber;
    }

    public static String getTeacherRoomNumber () {
        return String.valueOf(teacher.roomNumber);
    }

    public static String getTeacherRoomNumber (String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        return String.valueOf(teacher.roomNumber);
    }

    public static String getUserEntryYear () {
        return String.valueOf(student.entryYear);
    }

    public static String getUserSupervisorName () {
        return student.supervisorName;
    }

    public static String getStudentTotalAverageScore () {
        return String.valueOf(student.totalAverage);
    }

    public static String getUserEducationalDegree () {
        if (student.position.equals(Positions.MASTER)) {
            return "Master";
        } else if (student.position.equals(Positions.MSC)) {
            return "Msc";
        } else {
            return "PhD";
        }
    }

    public static String getTeacherPosition () {
        if (teacher.teacherPosition.equals(TeacherPosition.FULL_PROFESSOR)) {
            return "Full professor";
        } else if (teacher.teacherPosition.equals(TeacherPosition.ASSISTANT_PROFESSOR)) {
            return "Assistant professor";
        } else {
            return "Associate professor";
        }
    }

    public static String getStudyingCondition () {
        if (student.studentCondition.equals(StudentCondition.STUDYING)) {
            if (student.position.equals(Positions.MASTER)) {
                return "Studying (Master)";
            } else if (student.position.equals(Positions.MSC)) {
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

    public static String[] getLessonsOfATeacher () {
        return teacher.lessons.toArray(new String[0]);
    }

    public static String[][] seeListOfTeachersByUser() {
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

    public static String[][] seeListOfTeachersByUser(String filter) {
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

    public static void removeATeacher (String teacherName) {
        BossOfDepartment.removeATeacher(teacherName);
    }

    public static void editATeacher (String teacherName,String roomNumber,String teacherPosition,String generalPosition) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        BossOfDepartment.editTeacherInformation(Integer.parseInt(roomNumber),teacherName);
        if (teacherPosition.equals("FULL_PROFESSOR")) {
            BossOfDepartment.editTeacherInformation(TeacherPosition.FULL_PROFESSOR,teacherName);
        } else if (teacherPosition.equals("ASSOCIATE_PROFESSOR")) {
            BossOfDepartment.editTeacherInformation(TeacherPosition.ASSOCIATE_PROFESSOR,teacherName);
        } else if (teacherPosition.equals("ASSISTANT_PROFESSOR")) {
            BossOfDepartment.editTeacherInformation(TeacherPosition.ASSISTANT_PROFESSOR,teacherName);
        }
        if (generalPosition.equals("EDUCATIONAL_ASSISTANT") & !teacher.position.equals(Positions.EDUCATIONAL_ASSISTANT)) {
            BossOfDepartment.putOrRemoveEducationalAssistant(teacherName,true);
        } else if (generalPosition.equals("PROFESSOR") & !teacher.position.equals(Positions.PROFESSOR)) {
            BossOfDepartment.putOrRemoveEducationalAssistant(teacherName,false);
        }
    }

    public static boolean canEditATeacher (String bossOfDepartmentUsername,String teacherName) {
        return BossOfDepartment.canEditATeacher(bossOfDepartmentUsername,teacherName);
    }

    public static boolean addATeacher (String username, String password, String position, String completeName, String email,String departmentName, String nationalCode, String phoneNumber,String teacherPosition, int roomNumber, String teacherNumber) {
        Positions positions = null;
        if (position.equals("EDUCATIONAL_ASSISTANT")) {
            positions = Positions.EDUCATIONAL_ASSISTANT;
        } else if (position.equals("PROFESSOR")) {
            positions = Positions.PROFESSOR;
        }
        TeacherPosition userTeacherPosition = null;
        if (teacherPosition.equals("FULL_PROFESSOR")) {
            userTeacherPosition = TeacherPosition.FULL_PROFESSOR;
        } else if (teacherPosition.equals("ASSOCIATE_PROFESSOR")) {
            userTeacherPosition = TeacherPosition.ASSOCIATE_PROFESSOR;
        } else {
            userTeacherPosition = TeacherPosition.ASSISTANT_PROFESSOR;
        }
        if (Users.addATeacher(username,password,positions,completeName,email,departmentName,nationalCode,phoneNumber,userTeacherPosition,roomNumber,teacherNumber)) {
            if (positions.equals(Positions.EDUCATIONAL_ASSISTANT)) {
                BossOfDepartment.putOrRemoveEducationalAssistant(completeName, true);
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean addAStudent (String username, String password, String position, String completeName, String email, String lessons, String departmentName, String nationalCode, String phoneNumber, String supervisorName, String studentNumber, String entryYear, String studentCondition) {
        Positions positions = null;
        if (position.equals("MASTER")) {
            positions = Positions.MASTER;
        } else if (position.equals("MSC")) {
            positions = Positions.MSC;
        } else if (position.equals("PHD")) {
            positions = Positions.PHD;
        }
        StudentCondition studentEducationalCondition = null;
        if (studentCondition.equals("STUDYING")) {
            studentEducationalCondition = StudentCondition.STUDYING;
        } else if (studentCondition.equals("GRADUATED")) {
            studentEducationalCondition = StudentCondition.GRADUATED;
        } else if (studentCondition.equals("WITHDRAWAL_FROM_EDUCATION")) {
            studentEducationalCondition = StudentCondition.WITHDRAWAL_FROM_EDUCATION;
        }
        ArrayList<String> studentLessons = new ArrayList<>();
        int previousIndex = -1;
        int newIndex = 0;
        for (int i = 0; i < lessons.length(); i++) {
            if (lessons.charAt(i) == ',') {
                newIndex = i;
                if (studentLessons.size() == 0) {
                    studentLessons.add(lessons.charAt(0) + lessons.substring(previousIndex + 1, newIndex));
                } else {
                    studentLessons.add(lessons.substring(previousIndex + 1, newIndex));
                }
            }
            previousIndex = newIndex;
        }
        if (studentLessons.size() == 0) {
            studentLessons.add(lessons.substring(newIndex));
        } else {
            studentLessons.add(lessons.substring(previousIndex + 1));
        }
        if (lessons.length() == 0) {
            studentLessons.remove(0);
        }
        return Users.addAStudent(username,password,positions,completeName,email,studentLessons,departmentName,nationalCode,phoneNumber,supervisorName,studentNumber,Integer.parseInt(entryYear),studentEducationalCondition);
    }

    public static String[] seeStudentOfADepartment (String educationalAssistantUsername) {
        Teachers teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(educationalAssistantUsername);
        ArrayList<String> studentsNames = new ArrayList<>();
        File[] usersFiles = new File("src/UserFiles").listFiles();
        for (int i = 0; i < usersFiles.length; i++) {
            String userInformation = FilesAndGsonBuilderMethods.getStringJson(usersFiles[i]);
            Users user = FilesAndGsonBuilderMethods.getClassJson().fromJson(userInformation,Users.class);
            if (user.position.equals(Positions.MASTER) | user.position.equals(Positions.MSC) | user.position.equals(Positions.PHD)) {
                Students student = FilesAndGsonBuilderMethods.getClassJson().fromJson(userInformation,Students.class);
                if (student.departmentName.equals(teacher.departmentName)) {
                    studentsNames.add(student.completeName);
                }
            }
        }
        return studentsNames.toArray(new String[0]);
    }

    public static String[] seeTeachersOfADepartment (String educationalAssistantUsername) {
        Teachers teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(educationalAssistantUsername);
        ArrayList<String> teachersNames = new ArrayList<>();
        File[] usersFiles = new File("src/UserFiles").listFiles();
        for (int i = 0; i < usersFiles.length; i++) {
            String userInformation = FilesAndGsonBuilderMethods.getStringJson(usersFiles[i]);
            Users user = FilesAndGsonBuilderMethods.getClassJson().fromJson(userInformation,Users.class);
            if (user.position.equals(Positions.PROFESSOR) | user.position.equals(Positions.EDUCATIONAL_ASSISTANT) | user.position.equals(Positions.BOSS_OF_DEPARTMENT)) {
                Teachers consideredTeacher = FilesAndGsonBuilderMethods.getClassJson().fromJson(userInformation,Teachers.class);
                if (!consideredTeacher.isRemoved) {
                    if (consideredTeacher.departmentName.equals(teacher.departmentName)) {
                        teachersNames.add(consideredTeacher.completeName);
                    }
                }
            }
        }
        return teachersNames.toArray(new String[0]);
    }

    public static String[] seeStudentNumbersOfADepartment (String educationalAssistantUsername) {
        Teachers teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(educationalAssistantUsername);
        ArrayList<String> studentsNumbers = new ArrayList<>();
        File[] usersFiles = new File("src/UserFiles").listFiles();
        for (int i = 0; i < usersFiles.length; i++) {
            String userInformation = FilesAndGsonBuilderMethods.getStringJson(usersFiles[i]);
            Users user = FilesAndGsonBuilderMethods.getClassJson().fromJson(userInformation,Users.class);
            if (user.position.equals(Positions.MASTER) | user.position.equals(Positions.MSC) | user.position.equals(Positions.PHD)) {
                Students student = FilesAndGsonBuilderMethods.getClassJson().fromJson(userInformation,Students.class);
                if (student.departmentName.equals(teacher.departmentName)) {
                    studentsNumbers.add(student.studentNumber);
                }
            }
        }
        return studentsNumbers.toArray(new String[0]);
    }

    public static String[] getStudentUsernameAndPassword (String studentNameOrStudentNumber) {
        Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentNameOrStudentNumber);
        String[] usernameAndPassword = new String[2];
        usernameAndPassword[0] = student.username;
        usernameAndPassword[1] = student.hashedPassword;
        return usernameAndPassword;
    }

    public static void changeEmailAndPhoneNumber (String username,String newEmailAddress,String newPhoneNumber) {
        Users.changeEmail(username,newEmailAddress);
        Users.changePhoneNumber(username,newPhoneNumber);
    }

    public static void saveLastEntryTime (String username,String lastEntryTime) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        File userFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles",username);
        if (user.position.equals(Positions.MASTER) | user.position.equals(Positions.MSC) | user.position.equals(Positions.PHD)) {
            Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
            student.lastEntryTime = lastEntryTime;
            String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(student);
            FilesAndGsonBuilderMethods.updateFile(userFile,newInformation);
        } else {
            Teachers teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(username);
            teacher.lastEntryTime = lastEntryTime;
            String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
            FilesAndGsonBuilderMethods.updateFile(userFile,newInformation);
        }
    }

    public static boolean shouldChangePassword (String username) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        return Login.calculateUserOfflineTime(user.lastEntryTime);
    }

    public static int correctChangePassword (String username, String oldPassword, String newPassword) {
        return Users.changePassword(username,oldPassword,newPassword);
    }

    public static String getTimeOfNow () {
        return DatesAndTimes.getTimeOfNow();
    }

    public static String getLastTimeEntry (String username) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        if (user.lastEntryTime == null) {
            return DatesAndTimes.getTimeOfNow();
        } else {
            return user.lastEntryTime;
        }
    }

    public static boolean firstEntry (String username) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        if (user.lastEntryTime == null) {
            return true;
        } else {
            return false;
        }
    }
}
