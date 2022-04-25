package logic;

import Log.LogInformation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Requests {
    String studentName;
    String teacherName;
    TypeOfRequest typeOfRequest;
    String responseText;
    Positions studentPosition;


    static int addARecommendationRequest (String studentUsername, String teacherName, TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        if (!existARequest(student.completeName,teacherName,typeOfRequest)) {
            if (Teachers.findTeacherFromCompleteName(teacherName) != null) {
                RecommendationRequest recommendationRequest = new RecommendationRequest(student.completeName, teacherName, typeOfRequest, student.position);
                createANewFile(recommendationRequest);
                LogInformation.createLogStatement("Requests","addARecommendationRequest","the request have been added","info");
                return 1;
            } else {
                LogInformation.createLogStatement("Requests","addARecommendationRequest","the considered teacher is not exist","error");
                return 2;
            }
        } else {
            LogInformation.createLogStatement("Requests","addARecommendationRequest","the request is already exist","error");
            return 3;
        }
    }

    static boolean addACertificateStudentRequest (String studentUsername,TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        if (!existARequest(student.completeName,"headOfEducationalAssistants",typeOfRequest)) {
            CertificateStudentRequest certificateStudentRequest = new CertificateStudentRequest("headOfEducationalAssistants", typeOfRequest, student);
            createANewFile(certificateStudentRequest);
            LogInformation.createLogStatement("Requests","addACertificateStudentRequest","the request have been added","info");
            return true;
        } else {
            LogInformation.createLogStatement("Requests","addACertificateStudentRequest","the request is already exist","error");
            return false;
        }
    }

    static boolean addAMinorRequest (String studentUsername,String destinationDepartmentName,TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        Department originDepartment = FilesAndGsonBuilderMethods.convertFileToDepartment(student.departmentName);
        Department destinationDepartment = Department.findDepartmentFromName(destinationDepartmentName);
        if (!existARequest(student.completeName,originDepartment.educationalAssistantName,typeOfRequest)) {
            MinorRequest minorRequest = new MinorRequest(student,originDepartment,destinationDepartment,typeOfRequest);
            createANewFile(minorRequest);
            LogInformation.createLogStatement("Requests","addAMinorRequest","the request have been added","info");
            return true;
        } else {
            LogInformation.createLogStatement("Requests","addAMinorRequest","the request is already exist","error");
            return false;
        }
    }

    static boolean addAWithdrawalFromEducationRequest (String studentUsername,TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        Department department = FilesAndGsonBuilderMethods.convertFileToDepartment(student.departmentName);
        if (!existARequest(student.completeName,department.educationalAssistantName,typeOfRequest)) {
            WithdrawalFromEducationRequest withdrawalFromEducationRequest = new WithdrawalFromEducationRequest(student,department.educationalAssistantName,typeOfRequest);
            createANewFile(withdrawalFromEducationRequest);
            LogInformation.createLogStatement("Requests","addAWithdrawalFromEducationRequest","the request have been added","info");
            return true;
        } else {
            LogInformation.createLogStatement("Requests","addAWithdrawalFromEducationRequest","the request is already exist","error");
            return false;
        }
    }

    static boolean addADormRequest (String studentUsername,TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        if (!existARequest(student.completeName,"headOfEducationalAssistants",typeOfRequest)) {
            DormRequest dormRequest = new DormRequest("headOfEducationalAssistants", typeOfRequest, student);
            createANewFile(dormRequest);
            LogInformation.createLogStatement("Requests","addADormRequest","the request have been added","info");
            return true;
        } else {
            LogInformation.createLogStatement("Requests","addADormRequest","the request is already exist","error");
            return false;
        }
    }

    static boolean addAThesisDefenceRequest (String studentUsername,TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        Department department = FilesAndGsonBuilderMethods.convertFileToDepartment(student.departmentName);
        if (!existARequest(student.completeName,department.educationalAssistantName,typeOfRequest)) {
            ThesisDefenceRequest thesisDefenceRequest = new ThesisDefenceRequest(student,department.educationalAssistantName,typeOfRequest);
            createANewFile(thesisDefenceRequest);
            LogInformation.createLogStatement("Requests","addAThesisDefenceRequest","the request have been added","info");
            return true;
        } else {
            LogInformation.createLogStatement("Requests","addAThesisDefenceRequest","the request is already exist","error");
            return false;
        }
    }

    static boolean addAnObjection (String studentUsername,String lessonName,String objection,TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        if (!existARequest(student.completeName,lesson.teacherName,typeOfRequest)) {
            ObjectionToTheTemporaryScore objectionToTheTemporaryScore = new ObjectionToTheTemporaryScore(student,lesson,typeOfRequest,objection);
            createANewFile(objectionToTheTemporaryScore);
            LogInformation.createLogStatement("Requests","addAnObjection","the request have been added","info");
            return true;
        } else {
            LogInformation.createLogStatement("Requests","addAnObjection","the request is already exist","error");
            return false;
        }
    }

    static void createANewFile (Requests request) {
        String information = FilesAndGsonBuilderMethods.getClassJson().toJson(request);
        String pathOfFile = "src/RequestsFiles/" + request.typeOfRequest.toString() + "." + request.studentName + "." + request.teacherName + ".txt";
        File file = new File(pathOfFile);
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(information);
            fileWriter.close();
            LogInformation.createLogStatement("Requests","createANewFile","the file of request have been created successfully","info");
        } catch (IOException e) {
            LogInformation.createLogStatement("Requests","createANewFile","can't create a file for request","info");
            e.printStackTrace();
        }
    }

    private static boolean existARequest (String studentName,String teacherName,TypeOfRequest typeOfRequest) {
        String fileName = typeOfRequest.toString() + "." + studentName + "." + teacherName;
        if (FilesAndGsonBuilderMethods.findFileWithName("src/RequestsFiles",fileName) == null) {
            return false;
        }
        return true;
    }
}
