package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Masters extends Students{


    public Masters(String username, String password, positions position, String completeName, String email, ArrayList<String> lessons, String departmentName, String nationalCode, String phoneNumber, String universityName, String supervisorName, String studentNumber, int entryYear, StudentCondition studentCondition) {
        super(username, password, position, completeName, email, lessons, departmentName, nationalCode, phoneNumber, universityName, supervisorName, studentNumber, entryYear, studentCondition);
    }
}
