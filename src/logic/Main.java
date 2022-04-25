package logic;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Users.addEachUser("mammad","1123",positions.MASTER,"mammadzia","vio");
        //Users.addEachUser("BossOfMthematics","3333",positions.EDUCATIONAL_ASSISTANT,"mammadzia","vio");
        //Lessons lessons = new Lessons("riazi 2");
        //Users.vfbf();
        //System.out.println(Users.checkUsernameForLogin("mammad","1123"));
        //System.out.println(Users.checkUsernameForLogin("BossOfComputerEngineering","1111"));
        //BossOfDepartment.removeATeacher("mammad");
        //EducationalAssistant.addALesson("BP",432,4,"tefagh","mathematics",LevelOfEducation.MASTER);
        //EducationalAssistant.removeALesson("BP");
        /*File file = new File("src/UserFiles/BossOfComputerEngineering.txt");
        String str = FilesAndGsonBuilderMethods.getStringJson(file);
        System.out.println(str);*/
        /*for (int i = 0; i < Teachers.seeTeachers().size(); i++) {
            System.out.println(Teachers.seeTeachers().get(i));
        }*/
        //EducationalAssistant.editLessonInformation(1,"AP");
        ArrayList<String> lessons = new ArrayList<>();
        lessons.add("riazi2");
        lessons.add("physic2");
        Users.addAStudent("mammad","1123", Positions.MASTER,"mammadrza zia","zia.mohammadreza.mz@gmail.com",lessons,"Mathematica","1273897315","09139342159","mir","400108871",1400,StudentCondition.STUDYING);
        //System.out.println(Users.checkUsernameForLogin("BossOfComputerEngineering","1111"));
        //System.out.println(19.5 > 18);
        //System.out.println(TypeOfRequest.CERTIFICATE_STUDENT.toString());
        //Students student = FilesAndGsonBuilderMethods.convertFileToStudent("mammad");
        //System.out.println(student.username);
        //System.out.println("Sa" + "\n" + "dk");
        //File user = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles","mani");
        //user.delete();
    }
}
