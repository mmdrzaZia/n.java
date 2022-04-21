package logic;

import java.util.ArrayList;

public class Professors extends Teachers{


    public Professors(String username, String password, positions position, String completeName, String email, ArrayList<String> lessons, String departmentName, String nationalCode, String phoneNumber, String universityName, TeacherPosition teacherPosition, int roomNumber, String teacherNumber) {
        super(username, password, position, completeName, email, lessons, departmentName, nationalCode, phoneNumber, universityName, teacherPosition, roomNumber, teacherNumber);
    }
}
