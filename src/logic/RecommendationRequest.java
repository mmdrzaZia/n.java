package logic;

import Log.LogInformation;

import java.io.File;

public class RecommendationRequest extends Requests {
    boolean hasBeenAnswered;


    public RecommendationRequest(String studentName, String teacherName, TypeOfRequest typeOfRequest, Positions position) {
        this.studentName = studentName;
        this.teacherName = teacherName;
        this.typeOfRequest = typeOfRequest;
        this.studentPosition = position;
        responseText = String.format("Your request of recommendation to the %s has not been answered yet!",teacherName);
    }

    static void acceptOrReject (String studentName, String teacherName, TypeOfRequest typeOfRequest,boolean isAccepted) {
        String fileName = typeOfRequest.toString() + "." + studentName + "." + teacherName;
        File requestFile = FilesAndGsonBuilderMethods.findFileWithName("src/RequestsFiles",fileName);
        RecommendationRequest recommendationRequest = FilesAndGsonBuilderMethods.convertFileToRecommendationRequest(fileName);
        Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentName);
        if (isAccepted) {
            recommendationRequest.responseText = "I " + teacherName + " certify that Mr/Mrs " + studentName + " with student number " + student.studentNumber + " have passed " + "'" + student.findPassedLessons() + "'" + " with a grade " + "'" + student.findGradesOfPassedLessons() + "'";
        } else {
            recommendationRequest.responseText = teacherName + " reject your recommendation unfortunately!";
        }
        recommendationRequest.hasBeenAnswered = true;
        String requestInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(recommendationRequest);
        FilesAndGsonBuilderMethods.updateFile(requestFile,requestInformation);
        LogInformation.createLogStatement("RecommendationRequest","acceptOrReject","the condition of recommendation request have been updated","info");
    }
}
