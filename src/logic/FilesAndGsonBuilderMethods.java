package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Scanner;

public class FilesAndGsonBuilderMethods {

    public static String getStringJson (File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String teacherJson = "";
        while (scanner.hasNext()) {
            teacherJson += scanner.nextLine();
        }
        return teacherJson;
    }

    static Gson getClassJson () {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson;
    }

    static File findFileWhitName (String folderPath,String name) {
        File[] files = new File(folderPath).listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().equals(name + ".txt")) {
                return files[i];
            }
        }
        return null;
    }

    static void updateFile (File file,String stringJson) {
        FileWriter fwOb = null;
        try {
            fwOb = new FileWriter(file, false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(stringJson);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Lessons convertFileToLesson (String name) {
        File lessonFile = findFileWhitName("src/LessonsFiles",name);
        String lessonInformation = getStringJson(lessonFile);
        return getClassJson().fromJson(lessonInformation,Lessons.class);
    }

    static Users convertFileToUsers (String name) {
        File userFile = findFileWhitName("src/UserFiles",name);
        String userInformation = getStringJson(userFile);
        return getClassJson().fromJson(userInformation,Users.class);
    }

    static Students convertFileToStudent(String name) {
        File studentFile = findFileWhitName("src/UserFiles",name);
        String studentInformation = getStringJson(studentFile);
        return getClassJson().fromJson(studentInformation,Students.class);
    }

    static Teachers convertFileToTeachers (String name) {
        File teacherFile = findFileWhitName("src/UserFiles",name);
        String teacherInformation = getStringJson(teacherFile);
        return getClassJson().fromJson(teacherInformation,Teachers.class);
    }

    static EducationalAssistant convertFileToEducationalAssistant (String name) {
        File newEducationalAssistantFile = findFileWhitName("src/UserFiles",name);
        String newEducationalAssistantInformation = getStringJson(newEducationalAssistantFile);
        return getClassJson().fromJson(newEducationalAssistantInformation,EducationalAssistant.class);
    }

    static BossOfDepartment convertFileToBossOfDepartment (String name) {
        File newBossOfDepartmentFile = findFileWhitName("src/UserFiles",name);
        String newBossOfDepartmentInformation = getStringJson(newBossOfDepartmentFile);
        return getClassJson().fromJson(newBossOfDepartmentInformation,BossOfDepartment.class);
    }

    static Department convertFileToDepartment (String name) {
        File departmentFile = findFileWhitName("src/DepartmentsFiles",name);
        String departmentInformation = getStringJson(departmentFile);
        return getClassJson().fromJson(departmentInformation,Department.class);
    }

    static RecommendationRequest convertFileToRecommendationRequest (String fileName) {
        File requestFile = findFileWhitName("src/RequestsFiles",fileName);
        String requestInformation = getStringJson(requestFile);
        return getClassJson().fromJson(requestInformation,RecommendationRequest.class);
    }

    static MinorRequest convertFileToMinorRequest (String fileName) {
        File requestFile = findFileWhitName("src/RequestsFiles",fileName);
        String requestInformation = getStringJson(requestFile);
        return getClassJson().fromJson(requestInformation,MinorRequest.class);
    }

    static WithdrawalFromEducationRequest convertFileToWithdrawalFromEducationRequest (String fileName) {
        File requestFile = findFileWhitName("src/RequestsFiles",fileName);
        String requestInformation = getStringJson(requestFile);
        return getClassJson().fromJson(requestInformation,WithdrawalFromEducationRequest.class);
    }

    static ThesisDefenceRequest convertFileToThesisDefenceRequest (String fileName) {
        File requestFile = findFileWhitName("src/RequestsFiles",fileName);
        String requestInformation = getStringJson(requestFile);
        return getClassJson().fromJson(requestInformation,ThesisDefenceRequest.class);
    }

    static ObjectionToTheTemporaryScore convertFileToObjectionToTheTemporaryScore (String fileName) {
        File requestFile = findFileWhitName("src/RequestsFiles",fileName);
        String requestInformation = getStringJson(requestFile);
        return getClassJson().fromJson(requestInformation,ObjectionToTheTemporaryScore.class);
    }
}
