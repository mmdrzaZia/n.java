package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Requests {
    String studentName;
    String teacherName;
    TypeOfRequest typeOfRequest;
    String responseText;
    positions studentPosition;


    static int addARecommendationRequest (String studentUsername, String teacherName, TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        if (!existARequest(student.completeName,teacherName,typeOfRequest)) {
            if (Teachers.findTeacherFromCompleteName(teacherName) != null) {
                RecommendationRequest recommendationRequest = new RecommendationRequest(student.completeName, teacherName, typeOfRequest, student.position);
                createANewFile(recommendationRequest);
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
            //TODO
            //ADD AN ERROR
        }
    }

    static boolean addACertificateStudentRequest (String studentUsername,TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        if (!existARequest(student.completeName,"headOfEducationalAssistants",typeOfRequest)) {
            CertificateStudentRequest certificateStudentRequest = new CertificateStudentRequest("headOfEducationalAssistants", typeOfRequest, student);
            createANewFile(certificateStudentRequest);
            return true;
        } else {
            return false;
            //TODO
            //ADD AN ERROR
        }
    }

    static boolean addAMinorRequest (String studentUsername,String destinationDepartmentName,TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        Department originDepartment = FilesAndGsonBuilderMethods.convertFileToDepartment(student.departmentName);
        Department destinationDepartment = FilesAndGsonBuilderMethods.convertFileToDepartment(destinationDepartmentName);
        if (!existARequest(student.completeName,originDepartment.educationalAssistantName,typeOfRequest)) {
            MinorRequest minorRequest = new MinorRequest(student,originDepartment,destinationDepartment,typeOfRequest);
            createANewFile(minorRequest);
            return true;
        } else {
            return false;
            //TODO
            //ADD AN ERROR
        }
    }

    static boolean addAWithdrawalFromEducationRequest (String studentUsername,TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        Department department = FilesAndGsonBuilderMethods.convertFileToDepartment(student.departmentName);
        if (!existARequest(student.completeName,department.educationalAssistantName,typeOfRequest)) {
            WithdrawalFromEducationRequest withdrawalFromEducationRequest = new WithdrawalFromEducationRequest(student,department.educationalAssistantName,typeOfRequest);
            createANewFile(withdrawalFromEducationRequest);
            return true;
        } else {
            return false;
            //TODO
            //ADD AN ERROR
        }
    }

    static boolean addADormRequest (String studentUsername,TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        if (!existARequest(student.completeName,"headOfEducationalAssistants",typeOfRequest)) {
            DormRequest dormRequest = new DormRequest("headOfEducationalAssistants", typeOfRequest, student);
            createANewFile(dormRequest);
            return true;
        } else {
            return false;
            //TODO
            //ADD AN ERROR
        }
    }

    static boolean addAThesisDefenceRequest (String studentUsername,TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        Department department = FilesAndGsonBuilderMethods.convertFileToDepartment(student.departmentName);
        if (!existARequest(student.completeName,department.educationalAssistantName,typeOfRequest)) {
            ThesisDefenceRequest thesisDefenceRequest = new ThesisDefenceRequest(student,department.educationalAssistantName,typeOfRequest);
            createANewFile(thesisDefenceRequest);
            return true;
        } else {
            return false;
            //TODO
            //ADD AN ERROR
        }
    }

    static void addAnObjection (String studentUsername,String lessonName,String objection,TypeOfRequest typeOfRequest) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(studentUsername);
        Lessons lesson = FilesAndGsonBuilderMethods.convertFileToLesson(lessonName);
        if (!existARequest(student.completeName,lesson.teacherName,typeOfRequest)) {
            ObjectionToTheTemporaryScore objectionToTheTemporaryScore = new ObjectionToTheTemporaryScore(student,lesson,typeOfRequest,objection);
            createANewFile(objectionToTheTemporaryScore);
        } else {
            //TODO
            //ADD AN ERROR
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean existARequest (String studentName,String teacherName,TypeOfRequest typeOfRequest) {
        String fileName = typeOfRequest.toString() + "." + studentName + "." + teacherName;
        if (FilesAndGsonBuilderMethods.findFileWhitName("src/RequestsFiles",fileName) == null) {
            return false;
        }
        return true;
    }
}
