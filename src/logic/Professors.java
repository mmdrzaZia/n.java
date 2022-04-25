package logic;

import java.security.NoSuchAlgorithmException;

public class Professors extends Teachers{


    public Professors(String username, String password, Positions position, String completeName, String email, String departmentName, String nationalCode, String phoneNumber, TeacherPosition teacherPosition, int roomNumber, String teacherNumber) throws NoSuchAlgorithmException {
        super(username, password, position, completeName, email,departmentName, nationalCode, phoneNumber, teacherPosition, roomNumber, teacherNumber);
    }
}
