package logic;

import Log.LogInformation;

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
        if (teacher == null) {
            LogInformation.createLogStatement("BossOfDepartment","removeATeacher","the considered teacher have not exist","error");
        } else {
            for (int i = 0; i < teacher.lessons.size(); i++) {
                lessonFile = FilesAndGsonBuilderMethods.findFileWithName("src/LessonsFiles", teacher.lessons.get(i));
                lesson = FilesAndGsonBuilderMethods.convertFileToLesson(teacher.lessons.get(i));
                lesson.teacherName = "have not teacher";
                lesson.haveTeacher = false;
                String newInformationOfLesson = FilesAndGsonBuilderMethods.getClassJson().toJson(lesson);
                if (lessonFile == null) {
                    LogInformation.createLogStatement("BossOfDepartment", "removeATeacher", "the considered lessonFile have not exist", "error");
                }
                FilesAndGsonBuilderMethods.updateFile(lessonFile, newInformationOfLesson);
                LogInformation.createLogStatement("BossOfDepartment","removeATeacher","lesson with name " + "'" + lesson.name + "'" + " have updated." ,"info");
            }
            if (teacher.position.equals(Positions.EDUCATIONAL_ASSISTANT)) {
                Department department = FilesAndGsonBuilderMethods.convertFileToDepartment(teacher.departmentName);
                department.educationalAssistantName = null;
                department.haveAnEducationalAssistant = false;
                File departmentFile = FilesAndGsonBuilderMethods.findFileWithName("src/DepartmentsFiles", department.name);
                if (departmentFile == null) {
                    LogInformation.createLogStatement("BossOfDepartment","removeATeacher","the considered departmentFile have not exist","error");
                }
                String newDepartmentInformation = FilesAndGsonBuilderMethods.getStringJson(departmentFile);
                FilesAndGsonBuilderMethods.updateFile(departmentFile, newDepartmentInformation);
                LogInformation.createLogStatement("BossOfDepartment","removeATeacher","department with name " + "'" + department.name + "'" + " have updated." ,"info");
            }
            File teacherFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles", teacher.username);
            teacher.isRemoved = true;
            String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
            if (teacherFile == null) {
                LogInformation.createLogStatement("BossOfDepartment","removeATeacher","the considered teacherFile have not exist","error");
            }
            FilesAndGsonBuilderMethods.updateFile(teacherFile, newInformation);
            LogInformation.createLogStatement("BossOfDepartment","removeATeacher","teacher with name " + "'" + teacher.completeName + "'" + " have updated." ,"info");
        }
    }

    static void putOrRemoveEducationalAssistant (String teacherName,boolean wantToPut) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        if (teacher == null) {
            LogInformation.createLogStatement("BossOfDepartment","putOrRemoveEducationalAssistant","the considered teacher have not exist","error");
        }
        if (!teacher.isRemoved) {
            File teacherFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles", teacher.username);
            if (wantToPut) {
                File departmentFile = FilesAndGsonBuilderMethods.findFileWithName("src/DepartmentsFiles", teacher.departmentName);
                Department department = FilesAndGsonBuilderMethods.convertFileToDepartment(teacher.departmentName);
                File previousEducationalAssistantFile = null;
                if (department.haveAnEducationalAssistant) {
                    File[] users = new File("src/UserFiles").listFiles();
                    EducationalAssistant previousEducationalAssistant = null;
                    if (users == null) {
                        LogInformation.createLogStatement("BossOfDepartment","putOrRemoveEducationalAssistant","UserFolder is empty!","error");
                    }
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
                    if (previousEducationalAssistantFile == null) {
                        LogInformation.createLogStatement("BossOfDepartment","putOrRemoveEducationalAssistant","the considered teacher have not exist","error");
                    }
                    FilesAndGsonBuilderMethods.updateFile(previousEducationalAssistantFile, previousEducationalAssistantInformation);
                    LogInformation.createLogStatement("BossOfDepartment","putOrRemoveEducationalAssistant","the previous educational assistant have been updated","info");
                } else {
                    department.haveAnEducationalAssistant = true;
                }
                teacher.position = Positions.EDUCATIONAL_ASSISTANT;
                department.educationalAssistantName = teacher.completeName;
                String departmentInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(department);
                if (departmentFile == null) {
                    LogInformation.createLogStatement("BossOfDepartment","putOrRemoveEducationalAssistant","departmentFile have not exist","error");
                }
                FilesAndGsonBuilderMethods.updateFile(departmentFile, departmentInformation);
                LogInformation.createLogStatement("BossOfDepartment","putOrRemoveEducationalAssistant","the department have been updated","info");
                String newEducationalAssistantInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
                if (teacherFile == null) {
                    LogInformation.createLogStatement("BossOfDepartment","putOrRemoveEducationalAssistant","teacherFile have not exist","error");
                }
                FilesAndGsonBuilderMethods.updateFile(teacherFile, newEducationalAssistantInformation);
                LogInformation.createLogStatement("BossOfDepartment","putOrRemoveEducationalAssistant","the new educational assistant have been updated","info");
            } else {
                File departmentFile = FilesAndGsonBuilderMethods.findFileWithName("src/DepartmentsFiles", teacher.departmentName);
                Department department = FilesAndGsonBuilderMethods.convertFileToDepartment(teacher.departmentName);
                teacher.position = Positions.PROFESSOR;
                department.educationalAssistantName = null;
                department.haveAnEducationalAssistant = false;
                String departmentInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(department);
                if (departmentFile == null) {
                    LogInformation.createLogStatement("BossOfDepartment","putOrRemoveEducationalAssistant","departmentFile have not exist","error");
                }
                FilesAndGsonBuilderMethods.updateFile(departmentFile, departmentInformation);
                LogInformation.createLogStatement("BossOfDepartment","putOrRemoveEducationalAssistant","the department have been updated","info");
                String newEducationalAssistantInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
                if (teacherFile == null) {
                    LogInformation.createLogStatement("BossOfDepartment","putOrRemoveEducationalAssistant","teacherFile have not exist","error");
                }
                FilesAndGsonBuilderMethods.updateFile(teacherFile, newEducationalAssistantInformation);
                LogInformation.createLogStatement("BossOfDepartment","putOrRemoveEducationalAssistant","the new educational assistant have been updated","info");
            }
        }
    }

    static void editTeacherInformation (int roomNumber,String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        if (teacher == null) {
            LogInformation.createLogStatement("BossOfDepartment","editTeacherInformation","the considered teacher have not exist","error");
        }
        File teacherFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles", teacher.username);
        teacher.roomNumber = roomNumber;
        String newTeacherInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
        if (teacherFile == null) {
            LogInformation.createLogStatement("BossOfDepartment","editTeacherInformation","teacherFile have not exist","error");
        }
        FilesAndGsonBuilderMethods.updateFile(teacherFile, newTeacherInformation);
        LogInformation.createLogStatement("BossOfDepartment","editTeacherInformation","the teacher have been updated","info");
    }

    static void editTeacherInformation (TeacherPosition teacherPosition,String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        if (teacher == null) {
            LogInformation.createLogStatement("BossOfDepartment","editTeacherInformation","the considered teacher have not exist","error");
        }
        File teacherFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles", teacher.username);
        teacher.teacherPosition = teacherPosition;
        String newTeacherInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
        if (teacherFile == null) {
            LogInformation.createLogStatement("BossOfDepartment","editTeacherInformation","teacherFile have not exist","error");
        }
        FilesAndGsonBuilderMethods.updateFile(teacherFile, newTeacherInformation);
        LogInformation.createLogStatement("BossOfDepartment","editTeacherInformation","the teacher have been updated","info");
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
