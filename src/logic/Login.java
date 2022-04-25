package logic;

import Log.LogInformation;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Login {

    static boolean checkUsernameForLogin (String username,String password) {
        File userFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles", username);
        if (userFile != null) {
            if (canEnter(username)) {
                if (checkPassword(username, password)) {
                    LogInformation.createLogStatement("Login","checkUsernameForLogin","user with username " + "'" + username + "' " + "enter","info");
                    return true;
                }
                LogInformation.createLogStatement("Login","checkUsernameForLogin","the password is wrong","error");
            }
            LogInformation.createLogStatement("Login","checkUsernameForLogin","the user can't enter","error");
        }
        LogInformation.createLogStatement("Login","checkUsernameForLogin","the user is not exist","error");
        return false;
    }

    private static boolean checkPassword (String username,String password) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        try {
            if (user.hashedPassword.equals(Users.hashPassword(password))) {
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean canEnter (String username) {
        File[] userFiles = new File("src/UserFiles").listFiles();
        for (int i = 0; i < userFiles.length; i++) {
            String information = FilesAndGsonBuilderMethods.getStringJson(userFiles[i]);
            Users user = FilesAndGsonBuilderMethods.getClassJson().fromJson(information,Users.class);
            if (user.username.equals(username)) {
                if (user.position.equals(Positions.MASTER) | user.position.equals(Positions.PHD) | user.position.equals(Positions.MSC)) {
                    Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
                    if (student.studentCondition.equals(StudentCondition.WITHDRAWAL_FROM_EDUCATION)) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    Teachers teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(username);
                    if (teacher.isRemoved) {
                        continue;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean calculateUserOfflineTime(String lastEntryTime) {
        if (lastEntryTime != null) {
            String currentTime = DatesAndTimes.getTimeOfNow();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime timeOfLastEntry = LocalDateTime.parse(lastEntryTime, formatter);
            LocalDateTime timeOfNow = LocalDateTime.parse(currentTime, formatter);
            long diffInMinutes = java.time.Duration.between(timeOfLastEntry, timeOfNow).toMinutes();
            if (diffInMinutes >= 10) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
