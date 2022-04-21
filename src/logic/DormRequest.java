package logic;

import java.util.Random;

public class DormRequest extends Requests{

    public DormRequest(String teacherName,TypeOfRequest typeOfRequest,Students student) {
        this.studentName = student.completeName;
        this.studentPosition = student.position;
        this.typeOfRequest = typeOfRequest;
        this.teacherName = teacherName;
        Random random = new Random();
        int randomInt = random.nextInt(50);
        if ((randomInt % 2) == 0) {
            this.responseText = "Fortunately,Your request for dorm has been approved!";
        } else {
            this.responseText = "Unfortunately, your request for dorm has been rejected!";
        }
    }
}
