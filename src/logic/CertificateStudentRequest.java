package logic;

import java.util.Date;

public class CertificateStudentRequest extends Requests{

    public CertificateStudentRequest(String teacherName, TypeOfRequest typeOfRequest,Students student) {
        this.studentName = student.completeName;
        this.teacherName = teacherName;
        this.typeOfRequest = typeOfRequest;
        this.studentPosition = student.position;
        if (student.studentCondition.equals(StudentCondition.STUDYING)) {
            responseText = "It is certified that Mr/Mrs " + student.completeName + " with student number " + student.studentNumber + " is studying in the field " + student.departmentName + " At Sharif university " + "." + " This certificate was issued on " + getCurrentTime() + " and is valid for up to 6 months!";
        } else {
            responseText = "you can't have a certificate student!";
        }
    }

    private static String getCurrentTime () {
        Date date = new Date();
        String str = String.format("%tc", date );
        return str;
    }
}
