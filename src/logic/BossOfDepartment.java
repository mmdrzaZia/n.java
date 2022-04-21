package logic;

import java.io.File;
import java.util.ArrayList;

public class BossOfDepartment extends Teachers{


    public BossOfDepartment(String username, String password, positions position, String completeName, String email, ArrayList<String> lessons, String departmentName, String nationalCode, String phoneNumber, String universityName, TeacherPosition teacherPosition, int roomNumber, String teacherNumber) {
        super(username, password, position, completeName, email, lessons, departmentName, nationalCode, phoneNumber, universityName, teacherPosition, roomNumber, teacherNumber);
    }

    public static void removeATeacher (String teacherUsername) {
        File teacherFile = FilesAndGsonBuilderMethods.findFileWhitName("src/UserFiles",teacherUsername);
        teacherFile.delete();
    }

    static void putEducationalAssistant (String username) {
        File newEducationalAssistantFile = FilesAndGsonBuilderMethods.findFileWhitName("src/UserFiles",username);
        EducationalAssistant newEducationalAssistant = FilesAndGsonBuilderMethods.convertFileToEducationalAssistant(username);
        File departmentFile = FilesAndGsonBuilderMethods.findFileWhitName("src/DepartmentsFiles",newEducationalAssistant.departmentName);
        Department department = FilesAndGsonBuilderMethods.convertFileToDepartment(newEducationalAssistant.departmentName);
        File previousEducationalAssistantFile = null;
        if (department.haveAnEducationalAssistant) {
            File[] users = new File("src/UserFiles").listFiles();
            EducationalAssistant previousEducationalAssistant = null;
            for (int i = 0; i < users.length; i++) {
                String information = FilesAndGsonBuilderMethods.getStringJson(users[i]);
                previousEducationalAssistant = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, EducationalAssistant.class);
                if (previousEducationalAssistant.position.equals(positions.EDUCATIONAL_ASSISTANT)) {
                    if (previousEducationalAssistant.departmentName.equals(department.name)) {
                        previousEducationalAssistant.position = positions.PROFESSOR;
                        previousEducationalAssistantFile = users[i];
                        break;
                    }
                }
            }
            String previousEducationalAssistantInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(previousEducationalAssistant);
            FilesAndGsonBuilderMethods.updateFile(previousEducationalAssistantFile,previousEducationalAssistantInformation);
        } else {
            department.haveAnEducationalAssistant = true;
        }
        newEducationalAssistant.position = positions.EDUCATIONAL_ASSISTANT;
        department.educationalAssistantName = newEducationalAssistant.completeName;
        String departmentInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(department);
        FilesAndGsonBuilderMethods.updateFile(departmentFile,departmentInformation);
        String newEducationalAssistantInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(newEducationalAssistant);
        FilesAndGsonBuilderMethods.updateFile(newEducationalAssistantFile, newEducationalAssistantInformation);
    }

    static void editTeacherInformation (int roomNumber,String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        File teacherFile = FilesAndGsonBuilderMethods.findFileWhitName("src/UserFiles",teacher.username);
        teacher.roomNumber = roomNumber;
        String newTeacherInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
        FilesAndGsonBuilderMethods.updateFile(teacherFile,newTeacherInformation);
    }

    static void editTeacherInformation (TeacherPosition teacherPosition,String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        File teacherFile = FilesAndGsonBuilderMethods.findFileWhitName("src/UserFiles",teacher.username);
        teacher.teacherPosition = teacherPosition;
        String newTeacherInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
        FilesAndGsonBuilderMethods.updateFile(teacherFile,newTeacherInformation);
    }

    static boolean canEditATeacher (String bossOfDepartmentUsername,String teacherName) {
        BossOfDepartment bossOfDepartment = FilesAndGsonBuilderMethods.convertFileToBossOfDepartment(bossOfDepartmentUsername);
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        if (bossOfDepartment.departmentName.equals(teacher.departmentName)) {
            return true;
        }
        return false;
    }
}
