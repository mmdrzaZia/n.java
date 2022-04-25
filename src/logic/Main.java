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
        //ArrayList<String> lessons = new ArrayList<>();
        //lessons.add("riazi2");
        //lessons.add("physic2");
        //Users.addAStudent("mammad","1123", Positions.MASTER,"mammadrza zia","zia.mohammadreza.mz@gmail.com",lessons,"Mathematica","1273897315","09139342159","mir","400108871",1400,StudentCondition.STUDYING);
        Users.addATeacher("dr.Ardeshir","1111",Positions.BOSS_OF_DEPARTMENT,"mohammad ardeshir","Ardeshir@gmail.com","Mathematics","1273567843","09126573548",TeacherPosition.FULL_PROFESSOR,1111,"3232");
        Users.addATeacher("dr.Zarabi","2222",Positions.BOSS_OF_DEPARTMENT,"ali zarabizade","Zarabi@gmail.com","Computer Engineering","1277863845","09128725920",TeacherPosition.FULL_PROFESSOR,2222,"5656");
        Users.addATeacher("dr.Abam","3333",Positions.BOSS_OF_DEPARTMENT,"mohammad abam","Abam@gmail.com","Electrical Engineering","1270938475","09121842046",TeacherPosition.FULL_PROFESSOR,3333,"9087");
        Users.addATeacher("dr.bahmanAbadi","4444",Positions.BOSS_OF_DEPARTMENT,"mahmood bahmanAbadi","BahmanAbadi@gmail.com","Physics","1275784723","091292664583",TeacherPosition.FULL_PROFESSOR,4444,"7635");
        Users.addATeacher("dr.taheri","5555",Positions.BOSS_OF_DEPARTMENT,"ebrahim taheri","Taheri@gmail.com","Mechanical Engineering","1278193724","09121847265",TeacherPosition.FULL_PROFESSOR,5555,"1748");
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
