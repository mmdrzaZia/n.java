package logic;

import Log.LogInformation;

import java.io.File;

public class ThesisDefenceRequest extends Requests{
    boolean hasBeenAnswered;

    public ThesisDefenceRequest(Students student,String teacherName,TypeOfRequest typeOfRequest) {
        this.studentName = student.completeName;
        this.studentPosition = student.position;
        this.typeOfRequest = typeOfRequest;
        this.teacherName = teacherName;
        responseText = "Your request has not been answered yet!";
    }

    static void giveADate (String educationalAssistantUsername,String studentName,String date) {
        EducationalAssistant educationalAssistant = FilesAndGsonBuilderMethods.convertFileToEducationalAssistant(educationalAssistantUsername);
        String requestFileName = "THESIS_DEFENCE" + "." + studentName + "." + educationalAssistant.completeName;
        File requestFile = FilesAndGsonBuilderMethods.findFileWithName("src/RequestsFiles",requestFileName);
        ThesisDefenceRequest thesisDefenceRequest = FilesAndGsonBuilderMethods.convertFileToThesisDefenceRequest(requestFileName);
        thesisDefenceRequest.hasBeenAnswered = true;
        thesisDefenceRequest.responseText = "On " + "'" + date + "'" + " you can defend your dissertation";
        String newRequestInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(thesisDefenceRequest);
        FilesAndGsonBuilderMethods.updateFile(requestFile,newRequestInformation);
        LogInformation.createLogStatement("ThesisDefenceRequest","giveADate","the condition of thesis defense request have been updated successfully","info");
    }
}
