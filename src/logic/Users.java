package logic;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Users {
    String username;
    String hashedPassword;
    Positions position;
    String completeName;
    String email;
    ArrayList<String> lessons;
    String departmentName;
    String nationalCode;
    String phoneNumber;
    String lastEntryTime;
    boolean isRemoved = false;

    public Users(String username, String password, Positions position, String completeName, String email, String departmentName, String nationalCode, String phoneNumber) throws NoSuchAlgorithmException {
        this.username = username;
        this.hashedPassword = hashPassword(password);
        this.position = position;
        this.completeName = completeName;
        this.email = email;
        this.departmentName = departmentName;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        lessons = new ArrayList<>();
    }

    public static boolean addAStudent (String username, String password, Positions position, String completeName, String email, ArrayList<String> lessons, String departmentName, String nationalCode, String phoneNumber, String supervisorName, String studentNumber, int entryYear, StudentCondition studentCondition) {
        Students student = null;
        try {
            student = new Students(username,password,position,completeName,email,lessons,departmentName,nationalCode,phoneNumber,supervisorName,studentNumber,entryYear,studentCondition);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return addAUser(student);
    }

    static boolean addATeacher (String username, String password, Positions position, String completeName, String email, String departmentName, String nationalCode, String phoneNumber, TeacherPosition teacherPosition, int roomNumber, String teacherNumber) {
        if (position.equals(Positions.EDUCATIONAL_ASSISTANT)) {
            EducationalAssistant educationalAssistant = null;
            try {
                educationalAssistant = new EducationalAssistant(username,password,position,completeName,email,departmentName,nationalCode,phoneNumber,teacherPosition,roomNumber,teacherNumber);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return addAUser(educationalAssistant);
        } else if (position.equals(Positions.PROFESSOR)) {
            Professors professors = null;
            try {
                professors = new Professors(username,password,position,completeName,email,departmentName,nationalCode,phoneNumber,teacherPosition,roomNumber,teacherNumber);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return addAUser(professors);
        } else if (position.equals(Positions.BOSS_OF_DEPARTMENT)) {
            BossOfDepartment bossOfDepartment = null;
            try {
                bossOfDepartment = new BossOfDepartment(username,password,position,completeName,email,departmentName,nationalCode,phoneNumber,teacherPosition,roomNumber,teacherNumber);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return addAUser(bossOfDepartment);
        }
        return false;
    }

    private static boolean addAUser(Users user) {
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
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkUsernameForAddUser (String username) {
        File userFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles",username);
        if (userFile == null) {
            return true;
        }
        return false;
    }

    static void changeEmail (String username,String newEmail) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        File userFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles",username);
        if (user.position.equals(Positions.PROFESSOR) | user.position.equals(Positions.EDUCATIONAL_ASSISTANT) | user.position.equals(Positions.BOSS_OF_DEPARTMENT)) {
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
        File userFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles",username);
        if (user.position.equals(Positions.PROFESSOR) | user.position.equals(Positions.EDUCATIONAL_ASSISTANT) | user.position.equals(Positions.BOSS_OF_DEPARTMENT)) {
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

    static int changePassword (String username,String oldPassword,String newPassword) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        try {
            if (user.hashedPassword.equals(hashPassword(oldPassword))) {
                if (!newPassword.equals(oldPassword)) {
                    File userFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles", username);
                    if (user.position.equals(Positions.PROFESSOR) | user.position.equals(Positions.EDUCATIONAL_ASSISTANT) | user.position.equals(Positions.BOSS_OF_DEPARTMENT)) {
                        Teachers teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(username);
                        teacher.hashedPassword = hashPassword(newPassword);
                        String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
                        FilesAndGsonBuilderMethods.updateFile(userFile, newInformation);
                    } else {
                        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
                        student.hashedPassword = hashPassword(newPassword);
                        String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(student);
                        FilesAndGsonBuilderMethods.updateFile(userFile, newInformation);
                    }
                    return 1;
                } else {
                    return 2;
                }
            } else {
                return 3;
                //TODO
                //ADD AN ERROR
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0;
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

    public static String hashPassword (String password) throws NoSuchAlgorithmException {
        MessageDigest digest =MessageDigest.getInstance("SHA-256");
        byte[] encodeHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodeHash);
    }

    private static String bytesToHex (byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
