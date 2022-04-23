package logic;

import java.io.File;

public class MinorRequest extends Requests{
    MinorRequestResult minorRequestResult;
    String educationalAssistantOfOriginDepartment;
    String educationalAssistantOfDestinationDepartment;
    int originSituation = 0; // not respond = 0 , accept = 1 , reject = -1
    int destinationSituation = 0; //not respond = 0 , accept = 1 , reject = -1
    boolean hasBeenAnswered;

    public MinorRequest(Students student,Department originDepartment,Department destinationDepartment,TypeOfRequest typeOfRequest) {
        this.studentName = student.completeName;
        this.studentPosition = student.position;
        this.typeOfRequest = typeOfRequest;
        if (student.calculateTotalAverage() >= 18) {
            this.minorRequestResult = MinorRequestResult.REGISTERED;
            this.responseText = minorRequestResult.toString();
            this.educationalAssistantOfOriginDepartment = originDepartment.educationalAssistantName;
            this.educationalAssistantOfDestinationDepartment = destinationDepartment.educationalAssistantName;
        } else {
            this.minorRequestResult = MinorRequestResult.REJECTED;
            this.responseText = minorRequestResult.toString();
            this.hasBeenAnswered = true;
            //TODO
            //ADD AN ERROR
            this.educationalAssistantOfDestinationDepartment = "-";
            this.educationalAssistantOfOriginDepartment = "-";
        }
    }

    static void acceptOrReject (String studentName,String educationalAssistantUsername,boolean isAccepted) {
        Students student = Students.findStudentFromCompleteNameAndStudentNumber(studentName);
        Department originDepartment = FilesAndGsonBuilderMethods.convertFileToDepartment(student.departmentName);
        EducationalAssistant educationalAssistant = FilesAndGsonBuilderMethods.convertFileToEducationalAssistant(educationalAssistantUsername);
        String requestFileName = "MINOR" + "." + studentName + "." + originDepartment.educationalAssistantName;
        File minorRequestFile = FilesAndGsonBuilderMethods.findFileWithName("src/RequestsFiles",requestFileName);
        MinorRequest minorRequest = FilesAndGsonBuilderMethods.convertFileToMinorRequest(requestFileName);
        if (originDepartment.educationalAssistantName.equals(educationalAssistant.completeName)) {
            if (isAccepted) {
                minorRequest.originSituation = 1;
                if (minorRequest.destinationSituation == 1) {
                    minorRequest.minorRequestResult = MinorRequestResult.Accepted;
                    minorRequest.hasBeenAnswered = true;
                } else if (minorRequest.destinationSituation == -1) {
                    minorRequest.minorRequestResult = MinorRequestResult.REJECTED;
                    minorRequest.hasBeenAnswered = true;
                }
            } else {
                minorRequest.originSituation = -1;
                minorRequest.minorRequestResult = MinorRequestResult.REJECTED;
                if (minorRequest.destinationSituation != 0) {
                    minorRequest.hasBeenAnswered = true;
                }
            }
        } else {
            if (isAccepted) {
                minorRequest.destinationSituation = 1;
                if (minorRequest.originSituation == 1) {
                    minorRequest.minorRequestResult = MinorRequestResult.Accepted;
                    minorRequest.hasBeenAnswered = true;
                } else if (minorRequest.originSituation == -1) {
                    minorRequest.minorRequestResult = MinorRequestResult.REJECTED;
                    minorRequest.hasBeenAnswered = true;
                }
            } else {
                minorRequest.destinationSituation = -1;
                minorRequest.minorRequestResult = MinorRequestResult.REJECTED;
                if (minorRequest.originSituation != 0) {
                    minorRequest.hasBeenAnswered = true;
                }
            }
        }
        minorRequest.responseText = minorRequest.minorRequestResult.toString();
        String newInformation = FilesAndGsonBuilderMethods.getClassJson().toJson(minorRequest);
        FilesAndGsonBuilderMethods.updateFile(minorRequestFile,newInformation);
    }
}
