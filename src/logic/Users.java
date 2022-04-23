package logic;

import java.io.*;
import java.util.ArrayList;

public class Users {
    String username;
    String password;
    positions position;
    String completeName;
    String email;
    ArrayList<String> lessons;
    String departmentName;
    String nationalCode;
    String phoneNumber;
    String universityName;

    public Users(String username, String password, positions position, String completeName, String email, ArrayList<String> lessons, String departmentName, String nationalCode, String phoneNumber, String universityName) {
        this.username = username;
        this.password = password;
        this.position = position;
        this.completeName = completeName;
        this.email = email;
        this.lessons = lessons;
        this.departmentName = departmentName;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        this.universityName = universityName;
        lessons = new ArrayList<>();
    }

    public static void addAStudent (String username, String password, positions position, String completeName, String email, ArrayList<String> lessons, String departmentName, String nationalCode, String phoneNumber, String universityName, String supervisorName, String studentNumber, int entryYear, StudentCondition studentCondition) {
        Students student = new Students(username,password,position,completeName,email,lessons,departmentName,nationalCode,phoneNumber,universityName,supervisorName,studentNumber,entryYear,studentCondition);
        addAUser(student);
    }

    static void addATeacher (String username, String password, positions position, String completeName, String email, ArrayList<String> lessons, String departmentName, String nationalCode, String phoneNumber, String universityName, TeacherPosition teacherPosition, int roomNumber, String teacherNumber) {
        if (position.equals(positions.EDUCATIONAL_ASSISTANT)) {
            EducationalAssistant educationalAssistant = new EducationalAssistant(username,password,position,completeName,email,lessons,departmentName,nationalCode,phoneNumber,universityName,teacherPosition,roomNumber,teacherNumber);
            addAUser(educationalAssistant);
        } else if (position.equals(positions.PROFESSOR)) {
            Professors professors = new Professors(username,password,position,completeName,email,lessons,departmentName,nationalCode,phoneNumber,universityName,teacherPosition,roomNumber,teacherNumber);
            addAUser(professors);
        } else if (position.equals(positions.BOSS_OF_DEPARTMENT)) {
            BossOfDepartment bossOfDepartment = new BossOfDepartment(username,password,position,completeName,email,lessons,departmentName,nationalCode,phoneNumber,universityName,teacherPosition,roomNumber,teacherNumber);
            addAUser(bossOfDepartment);
        }
    }

    private static void addAUser(Users user) {
        if (checkUsernameForAddUser(user.username)) {
            String information = FilesAndGsonBuilderMethods.getClassJson().toJson(user);
            String pathOfFile = "src/UserFiles/" + user.username + ".txt";
            File file = new File(pathOfFile);
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(information);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean checkUsernameForAddUser (String username) {
        File userFile = FilesAndGsonBuilderMethods.findFileWhitName("src/UserFiles",username);
        if (userFile == null) {
            return true;
        }
        return false;
    }

    static void changeEmail (String username,String newEmail) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        File userFile = FilesAndGsonBuilderMethods.findFileWhitName("src/UserFiles",username);
        if (user.position.equals(positions.PROFESSOR) | user.position.equals(positions.EDUCATIONAL_ASSISTANT) | user.position.equals(positions.BOSS_OF_DEPARTMENT)) {
            Teachers teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(username);
            teacher.email = newEmail;
            String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
            FilesAndGsonBuilderMethods.updateFile(userFile,newInformation);
        } else {
            Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
            student.email = newEmail;
            String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(student);
            FilesAndGsonBuilderMethods.updateFile(userFile,newInformation);
        }
    }

    static void changePhoneNumber (String username,String newPhoneNumber) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        File userFile = FilesAndGsonBuilderMethods.findFileWhitName("src/UserFiles",username);
        if (user.position.equals(positions.PROFESSOR) | user.position.equals(positions.EDUCATIONAL_ASSISTANT) | user.position.equals(positions.BOSS_OF_DEPARTMENT)) {
            Teachers teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(username);
            teacher.phoneNumber = newPhoneNumber;
            String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
            FilesAndGsonBuilderMethods.updateFile(userFile,newInformation);
        } else {
            Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
            student.phoneNumber = newPhoneNumber;
            String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(student);
            FilesAndGsonBuilderMethods.updateFile(userFile,newInformation);
        }
    }

    static void changePassword (String username,String oldPassword,String newPassword) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        if (user.password.equals(oldPassword)) {
            File userFile = FilesAndGsonBuilderMethods.findFileWhitName("src/UserFiles", username);
            if (user.position.equals(positions.PROFESSOR) | user.position.equals(positions.EDUCATIONAL_ASSISTANT) | user.position.equals(positions.BOSS_OF_DEPARTMENT)) {
                Teachers teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(username);
                teacher.password = newPassword;
                String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
                FilesAndGsonBuilderMethods.updateFile(userFile, newInformation);
            } else {
                Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
                student.password = newPassword;
                String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(student);
                FilesAndGsonBuilderMethods.updateFile(userFile, newInformation);
            }
        } else {
            //TODO
            //ADD AN ERROR
        }
    }

    static ArrayList<Lessons> seeExamsSchedule (String userUsername) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(userUsername);
        ArrayList<Lessons> sortedListOfExams = new ArrayList<>();
        String[] userLessons = user.lessons.toArray(new String[0]);
        for (int j = 0; j < userLessons.length; j++) {
            for (int i = 0; i < userLessons.length; i++) {
                String helpingToSort;
                if (i != user.lessons.size() - 1) {
                    Lessons lessons1 = FilesAndGsonBuilderMethods.convertFileToLesson(user.lessons.get(i));
                    Lessons lessons2 = FilesAndGsonBuilderMethods.convertFileToLesson(user.lessons.get(i + 1));
                    if (Integer.parseInt(lessons1.examDate.substring(5, 7)) > Integer.parseInt(lessons2.examDate.substring(5, 7))) {
                        helpingToSort = user.lessons.get(i);
                        userLessons[i] = user.lessons.get(i + 1);
                        userLessons[i + 1] = helpingToSort;
                    } else if (Integer.parseInt(lessons1.examDate.substring(5, 7)) == Integer.parseInt(lessons2.examDate.substring(5, 7))) {
                        if (Integer.parseInt(lessons1.examDate.substring(8)) > Integer.parseInt(lessons2.examDate.substring(8))) {
                            helpingToSort = user.lessons.get(i);
                            userLessons[i] = user.lessons.get(i + 1);
                            userLessons[i + 1] = helpingToSort;
                        } else if (Integer.parseInt(lessons1.examDate.substring(8)) == Integer.parseInt(lessons2.examDate.substring(8))) {
                            if (Integer.parseInt(lessons1.examTime.substring(0,2)) > Integer.parseInt(lessons2.examTime.substring(0,2))) {
                                helpingToSort = user.lessons.get(i);
                                userLessons[i] = user.lessons.get(i + 1);
                                userLessons[i + 1] = helpingToSort;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < userLessons.length; i++) {
            sortedListOfExams.add(FilesAndGsonBuilderMethods.convertFileToLesson(userLessons[i]));
        }
        return sortedListOfExams;
    }
}
