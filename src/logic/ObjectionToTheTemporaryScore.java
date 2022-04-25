package logic;

import Log.LogInformation;

import java.io.File;

public class ObjectionToTheTemporaryScore extends Requests{
    String lessonName;
    String objection;
    boolean hasBeenAnswered;

    public ObjectionToTheTemporaryScore(Students student,Lessons lesson,TypeOfRequest typeOfRequest,String objection) {
        this.objection = objection;
        this.teacherName = lesson.teacherName;
        this.lessonName = lesson.name;
        this.typeOfRequest = typeOfRequest;
        this.studentName = student.completeName;
        this.studentPosition = student.position;
    }

    static void answerToObjection (String teacherUsername,String studentName,String lessonName,String answer,TypeOfRequest typeOfRequest) {
        Teachers teacher = FilesAndGsonBuilderMethods.convertFileToTeachers(teacherUsername);
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        String fileName = typeOfRequest.toString() + "." + studentName + "." + teacher.completeName;
        File objectionFile = FilesAndGsonBuilderMethods.findFileWithName("src/RequestsFiles",fileName);
        ObjectionToTheTemporaryScore objectionToTheTemporaryScore = FilesAndGsonBuilderMethods.convertFileToObjectionToTheTemporaryScore(fileName);
        objectionToTheTemporaryScore.hasBeenAnswered = true;
        objectionToTheTemporaryScore.responseText = answer;
        String newObjectionInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(objectionToTheTemporaryScore);
        FilesAndGsonBuilderMethods.updateFile(objectionFile,newObjectionInformation);
        LogInformation.createLogStatement("ObjectionToTheTemporaryScore","answerToObjection","the condition of objection by " + studentName + " to " + lessonName + "have been updated","info");
    }
}
