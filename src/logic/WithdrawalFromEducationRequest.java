package logic;

import Log.LogInformation;

import java.io.File;

public class WithdrawalFromEducationRequest extends Requests{
    boolean hasBeenAnswered;


    public WithdrawalFromEducationRequest(Students student,String teacherName,TypeOfRequest typeOfRequest) {
        this.studentName = student.completeName;
        this.typeOfRequest = typeOfRequest;
        this.studentPosition = student.position;
        this.teacherName = teacherName;
        responseText = "Your request has not been answered yet!";
    }

    static void acceptOrReject (String studentName,String teacherUsername,boolean isAccepted) {
        Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentName);
        File studentFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles",student.username);
        EducationalAssistant educationalAssistant = FilesAndGsonBuilderMethods.convertFileToEducationalAssistant(teacherUsername);
        String fileName = "WITHDRAWAL_FROM_EDUCATION" + "." + studentName + "." + educationalAssistant.completeName;
        File requestFile = FilesAndGsonBuilderMethods.findFileWithName("src/RequestsFiles",fileName);
        WithdrawalFromEducationRequest withdrawalFromEducationRequest = FilesAndGsonBuilderMethods.convertFileToWithdrawalFromEducationRequest(fileName);
        withdrawalFromEducationRequest.hasBeenAnswered = true;
        if (isAccepted) {
            student.studentCondition = StudentCondition.WITHDRAWAL_FROM_EDUCATION;
        } else {
            withdrawalFromEducationRequest.responseText = "Your request has not been approved!";
        }
        String newInformationOfStudent = FilesAndGsonBuilderMethods.getClassJson().toJson(student);
        String newInformationOfRequest = FilesAndGsonBuilderMethods.getClassJson().toJson(withdrawalFromEducationRequest);
        FilesAndGsonBuilderMethods.updateFile(studentFile,newInformationOfStudent);
        LogInformation.createLogStatement("WithdrawalFromEducationRequest","acceptOrReject","the situation of student was updated","info");
        FilesAndGsonBuilderMethods.updateFile(requestFile,newInformationOfRequest);
        LogInformation.createLogStatement("WithdrawalFromEducationRequest","acceptOrReject","the situation of withdrawal from education request was updated","info");
    }
}
