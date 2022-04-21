package logic;

import java.util.ArrayList;

public class MSc extends Students{


    public MSc(String username, String password, positions position, String completeName, String email, ArrayList<String> lessons, String departmentName, String nationalCode, String phoneNumber, String universityName, String supervisorName, String studentNumber, int entryYear, StudentCondition studentCondition) {
        super(username, password, position, completeName, email, lessons, departmentName, nationalCode, phoneNumber, universityName, supervisorName, studentNumber, entryYear, studentCondition);
    }
}
