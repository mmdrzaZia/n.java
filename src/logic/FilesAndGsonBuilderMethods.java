package logic;

import Log.LogInformation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Scanner;

public class FilesAndGsonBuilderMethods {

    public static String getStringJson (File file) {
        Scanner scanner = null;
        LogInformation.createLogStatement("FilesAndGsonBuilderMethods","getStringJson", file.getName() + " opened","info");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","getStringJson","can't reed "+ file.getName(),"error");
            e.printStackTrace();
        }
        String teacherJson = "";
        while (scanner.hasNext()) {
            teacherJson += scanner.nextLine();
        }
        LogInformation.createLogStatement("FilesAndGsonBuilderMethods","getStringJson", file.getName() + " closed","info");
        return teacherJson;
    }

    static Gson getClassJson () {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson;
    }

    static File findFileWithName(String folderPath, String name) {
        File[] files = new File(folderPath).listFiles();
        if (files == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","findFileWithName", "Folder with address " + "'" + folderPath + "'" + "is empty(null)","error");
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().equals(name + ".txt")) {
                return files[i];
            }
        }
        return null;
    }

    static void updateFile (File file,String stringJson) {
        LogInformation.createLogStatement("FilesAndGsonBuilderMethods","updateFile", file.getName() + " opened","info");
        FileWriter fwOb = null;
        try {
            fwOb = new FileWriter(file, false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (IOException e) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","updateFile","can't to empty "+ file.getName(),"error");
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(stringJson);
            fileWriter.close();
        } catch (IOException e) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","getStringJson","can't write in "+ file.getName(),"error");
            e.printStackTrace();
        }
        LogInformation.createLogStatement("FilesAndGsonBuilderMethods","updateFile", file.getName() + " closed","info");
    }

    static Lessons convertFileToLesson (String name) {
        File lessonFile = findFileWithName("src/LessonsFiles",name);
        String lessonInformation = "";
        if (lessonFile == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","convertFileToLesson", "'lessonFile'" + "is empty(null)","error");
        }
        lessonInformation = getStringJson(lessonFile);
        return getClassJson().fromJson(lessonInformation,Lessons.class);
    }

    static Users convertFileToUsers (String name) {
        File userFile = findFileWithName("src/UserFiles",name);
        String userInformation = "";
        if (userFile == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","convertFileToUsers", "'userFile'" + "is empty(null)","error");
        }
        userInformation = getStringJson(userFile);
        return getClassJson().fromJson(userInformation,Users.class);
    }

    static Students convertFileToStudent(String name) {
        File studentFile = findFileWithName("src/UserFiles",name);
        String studentInformation = "";
        if (studentFile == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","convertFileToStudent", "'studentFile'" + "is empty(null)","error");
        }
        studentInformation = getStringJson(studentFile);
        return getClassJson().fromJson(studentInformation,Students.class);
    }

    static Teachers convertFileToTeachers (String name) {
        File teacherFile = findFileWithName("src/UserFiles",name);
        String teacherInformation = "";
        if (teacherFile == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","convertFileToTeachers", "'teacherFile'" + "is empty(null)","error");
        }
        teacherInformation = getStringJson(teacherFile);
        return getClassJson().fromJson(teacherInformation,Teachers.class);
    }

    static EducationalAssistant convertFileToEducationalAssistant (String name) {
        File newEducationalAssistantFile = findFileWithName("src/UserFiles",name);
        String newEducationalAssistantInformation = "";
        if (newEducationalAssistantFile == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","convertFileToEducationalAssistant", "'newEducationalAssistantFile'" + "is empty(null)","error");
        }
        newEducationalAssistantInformation = getStringJson(newEducationalAssistantFile);
        return getClassJson().fromJson(newEducationalAssistantInformation,EducationalAssistant.class);
    }

    static BossOfDepartment convertFileToBossOfDepartment (String name) {
        File newBossOfDepartmentFile = findFileWithName("src/UserFiles",name);
        String newBossOfDepartmentInformation = "";
        if (newBossOfDepartmentFile == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","convertFileToBossOfDepartment", "'newBossOfDepartmentFile'" + "is empty(null)","error");
        }
        newBossOfDepartmentInformation = getStringJson(newBossOfDepartmentFile);
        return getClassJson().fromJson(newBossOfDepartmentInformation,BossOfDepartment.class);
    }

    static Department convertFileToDepartment (String name) {
        File departmentFile = findFileWithName("src/DepartmentsFiles",name);
        String departmentInformation = "";
        if (departmentFile == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","convertFileToDepartment", "'departmentFile'" + "is empty(null)","error");
        }
        departmentInformation = getStringJson(departmentFile);
        return getClassJson().fromJson(departmentInformation,Department.class);
    }

    static RecommendationRequest convertFileToRecommendationRequest (String fileName) {
        File requestFile = findFileWithName("src/RequestsFiles",fileName);
        String requestInformation = "";
        if (requestFile == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","convertFileToRecommendationRequest", "'requestFile'" + "is empty(null)","error");
        }
        requestInformation = getStringJson(requestFile);
        return getClassJson().fromJson(requestInformation,RecommendationRequest.class);
    }

    static MinorRequest convertFileToMinorRequest (String fileName) {
        File requestFile = findFileWithName("src/RequestsFiles",fileName);
        String requestInformation = "";
        if (requestFile == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","convertFileToMinorRequest", "'requestFile'" + "is empty(null)","error");
        }
        requestInformation = getStringJson(requestFile);
        return getClassJson().fromJson(requestInformation,MinorRequest.class);
    }

    static WithdrawalFromEducationRequest convertFileToWithdrawalFromEducationRequest (String fileName) {
        File requestFile = findFileWithName("src/RequestsFiles",fileName);
        String requestInformation = "";
        if (requestFile == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","convertFileToWithdrawalFromEducationRequest", "'requestFile'" + "is empty(null)","error");
        }
        requestInformation = getStringJson(requestFile);
        return getClassJson().fromJson(requestInformation,WithdrawalFromEducationRequest.class);
    }

    static ThesisDefenceRequest convertFileToThesisDefenceRequest (String fileName) {
        File requestFile = findFileWithName("src/RequestsFiles",fileName);
        String requestInformation = "";
        if (requestFile == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","convertFileToThesisDefenceRequest", "'requestFile'" + "is empty(null)","error");
        }
        requestInformation = getStringJson(requestFile);
        return getClassJson().fromJson(requestInformation,ThesisDefenceRequest.class);
    }

    static ObjectionToTheTemporaryScore convertFileToObjectionToTheTemporaryScore (String fileName) {
        File requestFile = findFileWithName("src/RequestsFiles",fileName);
        String requestInformation = "";
        if (requestFile == null) {
            LogInformation.createLogStatement("FilesAndGsonBuilderMethods","convertFileToObjectionToTheTemporaryScore", "'requestFile'" + "is empty(null)","error");
        }
        requestInformation = getStringJson(requestFile);
        return getClassJson().fromJson(requestInformation,ObjectionToTheTemporaryScore.class);
    }
}
