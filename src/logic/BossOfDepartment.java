package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

public class BossOfDepartment extends Teachers{


    public BossOfDepartment(String username, String password, Positions position, String completeName, String email, String departmentName, String nationalCode, String phoneNumber, TeacherPosition teacherPosition, int roomNumber, String teacherNumber) throws NoSuchAlgorithmException {
        super(username, password, position, completeName, email, departmentName, nationalCode, phoneNumber, teacherPosition, roomNumber, teacherNumber);
    }

    public static void removeATeacher (String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        Lessons lesson = null;
        File lessonFile = null;
        for (int i = 0; i < teacher.lessons.size(); i++) {
            lessonFile = FilesAndGsonBuilderMethods.findFileWithName("src/LessonsFiles",teacher.lessons.get(i));
            lesson = FilesAndGsonBuilderMethods.convertFileToLesson(teacher.lessons.get(i));
            lesson.teacherName = "have not teacher";
            lesson.haveTeacher = false;
        }
        if (teacher.lessons.size() != 0) {
            String newInformationOfLesson = FilesAndGsonBuilderMethods.getClassJson().toJson(lesson);
            FilesAndGsonBuilderMethods.updateFile(lessonFile, newInformationOfLesson);
        }
        if (teacher.position.equals(Positions.EDUCATIONAL_ASSISTANT)) {
            Department department = FilesAndGsonBuilderMethods.convertFileToDepartment(teacher.departmentName);
            department.educationalAssistantName = null;
            department.haveAnEducationalAssistant = false;
            File departmentFile = FilesAndGsonBuilderMethods.findFileWithName("src/DepartmentsFiles",department.name);
            String newDepartmentInformation = FilesAndGsonBuilderMethods.getStringJson(departmentFile);
            FilesAndGsonBuilderMethods.updateFile(departmentFile,newDepartmentInformation);
        }
        File teacherFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles", teacher.username);
        teacher.isRemoved = true;
        String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
        FilesAndGsonBuilderMethods.updateFile(teacherFile,newInformation);
    }

    static void putOrRemoveEducationalAssistant (String teacherName,boolean wantToPut) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        if (!teacher.isRemoved) {
            File teacherFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles", teacher.username);
            if (wantToPut) {
                File departmentFile = FilesAndGsonBuilderMethods.findFileWithName("src/DepartmentsFiles", teacher.departmentName);
                Department department = FilesAndGsonBuilderMethods.convertFileToDepartment(teacher.departmentName);
                File previousEducationalAssistantFile = null;
                if (department.haveAnEducationalAssistant) {
                    File[] users = new File("src/UserFiles").listFiles();
                    EducationalAssistant previousEducationalAssistant = null;
                    for (int i = 0; i < users.length; i++) {
                        String information = FilesAndGsonBuilderMethods.getStringJson(users[i]);
                        previousEducationalAssistant = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, EducationalAssistant.class);
                        if (previousEducationalAssistant.position.equals(Positions.EDUCATIONAL_ASSISTANT)) {
                            if (previousEducationalAssistant.departmentName.equals(department.name)) {
                                previousEducationalAssistant.position = Positions.PROFESSOR;
                                previousEducationalAssistantFile = users[i];
                                break;
                            }
                        }
                    }
                    String previousEducationalAssistantInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(previousEducationalAssistant);
                    FilesAndGsonBuilderMethods.updateFile(previousEducationalAssistantFile, previousEducationalAssistantInformation);
                } else {
                    department.haveAnEducationalAssistant = true;
                }
                teacher.position = Positions.EDUCATIONAL_ASSISTANT;
                department.educationalAssistantName = teacher.completeName;
                String departmentInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(department);
                FilesAndGsonBuilderMethods.updateFile(departmentFile, departmentInformation);
                String newEducationalAssistantInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
                FilesAndGsonBuilderMethods.updateFile(teacherFile, newEducationalAssistantInformation);
            } else {
                File departmentFile = FilesAndGsonBuilderMethods.findFileWithName("src/DepartmentsFiles", teacher.departmentName);
                Department department = FilesAndGsonBuilderMethods.convertFileToDepartment(teacher.departmentName);
                teacher.position = Positions.PROFESSOR;
                department.educationalAssistantName = null;
                department.haveAnEducationalAssistant = false;
                String departmentInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(department);
                FilesAndGsonBuilderMethods.updateFile(departmentFile, departmentInformation);
                String newEducationalAssistantInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
                FilesAndGsonBuilderMethods.updateFile(teacherFile, newEducationalAssistantInformation);
            }
        }
    }

    static void editTeacherInformation (int roomNumber,String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        File teacherFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles",teacher.username);
        teacher.roomNumber = roomNumber;
        String newTeacherInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
        FilesAndGsonBuilderMethods.updateFile(teacherFile,newTeacherInformation);
    }

    static void editTeacherInformation (TeacherPosition teacherPosition,String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        File teacherFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles",teacher.username);
        teacher.teacherPosition = teacherPosition;
        String newTeacherInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
        FilesAndGsonBuilderMethods.updateFile(teacherFile,newTeacherInformation);
    }

    static boolean canEditATeacher (String bossOfDepartmentUsername,String teacherName) {
        BossOfDepartment bossOfDepartment = FilesAndGsonBuilderMethods.convertFileToBossOfDepartment(bossOfDepartmentUsername);
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        if (bossOfDepartmentUsername.equals(teacher.username)) {
            return false;
        }
        if (bossOfDepartment.departmentName.equalsIgnoreCase(teacher.departmentName)) {
            return true;
        }
        return false;
    }
}
