package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Department {
    String name;
    boolean haveAnEducationalAssistant;
    String bossOfDepartmentName;
    String educationalAssistantName;
    ArrayList<String> lessons = new ArrayList<>();

    static Department findDepartmentFromName (String name) {
        File[] departmentsFiles = new File("src/DepartmentsFiles").listFiles();
        for (int i = 0; i < departmentsFiles.length; i++) {
            String information = FilesAndGsonBuilderMethods.getStringJson(departmentsFiles[i]);
            Department department = FilesAndGsonBuilderMethods.getClassJson().fromJson(information,Department.class);
            if (department.name.equalsIgnoreCase(name)) {
                return department;
            }
        }
        return null;
    }


}
