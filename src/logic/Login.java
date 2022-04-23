package logic;

import java.io.File;

public class Login {

    static boolean checkUsernameForLogin (String username,String password) {
        File userFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles", username);
        if (userFile != null) {
            if (canEnter(username)) {
                if (userFile != null) {
                    if (checkPassword(username, password)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkPassword (String username,String password) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        if (user.password.equals(password)) {
            return true;
        }
        return false;
    }

    private static boolean canEnter (String username) {
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        if (user.position.equals(Positions.MASTER) | user.position.equals(Positions.PHD) | user.position.equals(Positions.MSC)) {
            Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
            if (student.studentCondition.equals(StudentCondition.WITHDRAWAL_FROM_EDUCATION)) {
                return false;
            }
        }
        return true;
    }
}
