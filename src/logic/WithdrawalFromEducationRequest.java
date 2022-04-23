package logic;

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

    static void acceptOrReject (String studentName,String teacherUsername,TypeOfRequest typeOfRequest,boolean isAccepted) {
        Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentName);
        File studentFile = FilesAndGsonBuilderMethods.findFileWithName("src/UserFiles",student.username);
        EducationalAssistant educationalAssistant = FilesAndGsonBuilderMethods.convertFileToEducationalAssistant(teacherUsername);
        String fileName = typeOfRequest.toString() + "." + studentName + "." + educationalAssistant.completeName;
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
        FilesAndGsonBuilderMethods.updateFile(requestFile,newInformationOfRequest);
    }
}
