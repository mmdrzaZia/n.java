package logic;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Teachers extends Users {
    TeacherPosition teacherPosition;
    int roomNumber;
    String teacherNumber;

    public Teachers(String username, String password, Positions position, String completeName, String email, String departmentName, String nationalCode, String phoneNumber, TeacherPosition teacherPosition, int roomNumber, String teacherNumber) throws NoSuchAlgorithmException {
        super(username, password, position, completeName, email,departmentName, nationalCode, phoneNumber);
        this.teacherPosition = teacherPosition;
        this.roomNumber = roomNumber;
        this.teacherNumber = teacherNumber;
    }

    static ArrayList<Teachers> seeTeachers() {
        ArrayList<Teachers> teachersInformation = new ArrayList<>();
        File[] teachers = new File("src/UserFiles").listFiles();
        for (int i = 0; i < teachers.length; i++) {
            String teacherJson = FilesAndGsonBuilderMethods.getStringJson(teachers[i]);
            Teachers teacher = FilesAndGsonBuilderMethods.getClassJson().fromJson(teacherJson,Teachers.class);
            if (!teacher.isRemoved) {
                if (teacher.position.equals(Positions.PROFESSOR) | teacher.position.equals(Positions.EDUCATIONAL_ASSISTANT) | teacher.position.equals(Positions.BOSS_OF_DEPARTMENT)) {
                    teachersInformation.add(teacher);
                }
            }
        }
        return teachersInformation;
    }

    static ArrayList<Teachers> seeTeachers (String filter) {
        ArrayList<Teachers> teachersInformation = new ArrayList<>();
        File[] teachers = new File("src/UserFiles").listFiles();
        for (int i = 0; i < teachers.length; i++) {
            String teacherJson = FilesAndGsonBuilderMethods.getStringJson(teachers[i]);
            Teachers teacher = FilesAndGsonBuilderMethods.getClassJson().fromJson(teacherJson,Teachers.class);
            if (!teacher.isRemoved) {
                if (teacher.position.equals(Positions.PROFESSOR) | teacher.position.equals(Positions.EDUCATIONAL_ASSISTANT) | teacher.position.equals(Positions.BOSS_OF_DEPARTMENT)) {
                    if (teacher.completeName.equalsIgnoreCase(filter) | teacher.departmentName.equalsIgnoreCase(filter) | teacher.teacherPosition.toString().equalsIgnoreCase(filter)) {
                        teachersInformation.add(teacher);
                    }
                }
            }
        }
        return teachersInformation;
    }

    static void removeALesson (String lessonName,String teacherName) {
        File teacherFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles", teacherName);
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        for (int i = 0; i < teacher.lessons.size(); i++) {
            if (teacher.lessons.get(i).equals(lessonName)) {
                teacher.lessons.remove(i);
                break;
            }
        }
        String newTeacherInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
        FilesAndGsonBuilderMethods.updateFile(teacherFile,newTeacherInformation);
    }

    static void addALesson (String lessonName,String teacherName) {
        Teachers teacher = Teachers.findTeacherFromCompleteName(teacherName);
        File teacherFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles", teacher.username);
        teacher.lessons.add(lessonName);
        String newTeacherInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(teacher);
        FilesAndGsonBuilderMethods.updateFile(teacherFile,newTeacherInformation);
    }

    static Teachers findTeacherFromCompleteName (String name) {
        File[] teacherFiles = new File("src/UserFiles").listFiles();
        for (int i = 0; i < teacherFiles.length; i++) {
            String information = FilesAndGsonBuilderMethods.getStringJson(teacherFiles[i]);
            Users user = FilesAndGsonBuilderMethods.getClassJson().fromJson(information,Users.class);
            if (user.position.equals(Positions.PROFESSOR) | user.position.equals(Positions.EDUCATIONAL_ASSISTANT) | user.position.equals(Positions.BOSS_OF_DEPARTMENT)) {
                Teachers teacher = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, Teachers.class);
                if (!teacher.isRemoved) {
                    if (user.completeName.equalsIgnoreCase(name)) {
                        return teacher;
                    }
                }
            }
        }
        return null;
    }

    String ATeacherLessons () {
        String teacherLessons = "";
        for (int i = 0; i < lessons.size(); i++) {
            teacherLessons += "-" + lessons.get(i);
        }
        if (teacherLessons.equals("")) {
            return "";
        } else {
            return teacherLessons.substring(1);
        }
    }
}
