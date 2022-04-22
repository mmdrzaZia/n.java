package logic;


import java.io.File;
import java.util.ArrayList;

public class RequestsController {

    public static String[][] getAnswersOfRecommendationRequests (String username) {
        ArrayList<String> answers = new ArrayList<>();
        Users user = FilesAndGsonBuilderMethods.convertFileToUsers(username);
        File[] requestsFiles = new File("src/RequestsFiles").listFiles();
        for (int i = 0; i < requestsFiles.length; i++) {
            String information = FilesAndGsonBuilderMethods.getStringJson(requestsFiles[i]);
            Requests request = FilesAndGsonBuilderMethods.getClassJson().fromJson(information,Requests.class);
            if (request.typeOfRequest.equals(TypeOfRequest.RECOMMENDATION)) {
                answers.add(request.responseText);
            }
        }
        String[][] answersOfRequests = new String[answers.size()][1];
        for (int i = 0; i < answers.size(); i++) {
            answersOfRequests[i][0] = answers.get(i);
        }
        return answersOfRequests;
    }

    public static int addRecommendationRequest (String username,String teacherName) {
        return Requests.addARecommendationRequest(username,teacherName,TypeOfRequest.RECOMMENDATION);
    }

    public static String[][] getAnswerOfCertificateStudentRequest (String username) {
        String[][] answer = new String[1][1];
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
        File[] requestsFiles = new File("src/RequestsFiles").listFiles();
        for (int i = 0; i < requestsFiles.length; i++) {
            String information = FilesAndGsonBuilderMethods.getStringJson(requestsFiles[i]);
            Requests request = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, Requests.class);
            if (request.typeOfRequest.equals(TypeOfRequest.CERTIFICATE_STUDENT) && request.studentName.equals(student.completeName)) {
                answer[0][0] = request.responseText;
            }
        }
        return answer;
    }

    public static boolean addCertificateRequest (String username) {
        return Requests.addACertificateStudentRequest(username,TypeOfRequest.CERTIFICATE_STUDENT);
    }

    public static String getAnswerOfMinorRequest (String username) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
        File[] requestsFiles = new File("src/RequestsFiles").listFiles();
        for (int i = 0; i < requestsFiles.length; i++) {
            String information = FilesAndGsonBuilderMethods.getStringJson(requestsFiles[i]);
            Requests request = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, Requests.class);
            if (request.typeOfRequest.equals(TypeOfRequest.MINOR) && request.studentName.equals(student.completeName)) {
                MinorRequest minorRequest = FilesAndGsonBuilderMethods.getClassJson().fromJson(information,MinorRequest.class);
                return minorRequest.minorRequestResult.toString();
            }
        }
        return "You have not requested yet";
    }

    public static boolean addMinorRequest (String username,String departmentName) {
        return Requests.addAMinorRequest(username,departmentName,TypeOfRequest.MINOR);
    }

    public static String getAnswerOfWithdrawalFromEducationRequest (String username) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
        File[] requestsFiles = new File("src/RequestsFiles").listFiles();
        for (int i = 0; i < requestsFiles.length; i++) {
            String information = FilesAndGsonBuilderMethods.getStringJson(requestsFiles[i]);
            Requests request = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, Requests.class);
            if (request.typeOfRequest.equals(TypeOfRequest.WITHDRAWAL_FROM_EDUCATION) && request.studentName.equals(student.completeName)) {
                return request.responseText;
            }
        }
        return "You have not requested yet";
    }

    public static boolean addWithdrawalFromEducationRequest (String username) {
        return Requests.addAWithdrawalFromEducationRequest(username,TypeOfRequest.WITHDRAWAL_FROM_EDUCATION);
    }

    public static String getAnswerOfDormRequest (String username) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
        File[] requestsFiles = new File("src/RequestsFiles").listFiles();
        for (int i = 0; i < requestsFiles.length; i++) {
            String information = FilesAndGsonBuilderMethods.getStringJson(requestsFiles[i]);
            Requests request = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, Requests.class);
            if (request.typeOfRequest.equals(TypeOfRequest.DORM) && request.studentName.equals(student.completeName)) {
                return request.responseText;
            }
        }
        return "You have not requested yet";
    }

    public static boolean addDormRequest (String username) {
        return Requests.addADormRequest(username,TypeOfRequest.DORM);
    }

    public static String getAnswerOfThesisDefenceRequest (String username) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
        File[] requestsFiles = new File("src/RequestsFiles").listFiles();
        for (int i = 0; i < requestsFiles.length; i++) {
            String information = FilesAndGsonBuilderMethods.getStringJson(requestsFiles[i]);
            Requests request = FilesAndGsonBuilderMethods.getClassJson().fromJson(information, Requests.class);
            if (request.typeOfRequest.equals(TypeOfRequest.THESIS_DEFENCE) && request.studentName.equals(student.completeName)) {
                return request.responseText;
            }
        }
        return "You have not requested yet";
    }

    public static boolean addThesisDefenceRequest (String username) {
        return Requests.addAThesisDefenceRequest(username,TypeOfRequest.THESIS_DEFENCE);
    }
}
