package graphic;

public class kapchaHandler {

    static String findAddressOfKapchaPictures (int number) {
        if (number == 1) {
            return "src/kapchasPictures/kapcha1.jpg";
        } else if (number == 2) {
            return "src/kapchasPictures/kapcha2.jpg";
        } else if (number == 3) {
            return "src/kapchasPictures/kapcha3.jpg";
        } else if (number == 4) {
            return "src/kapchasPictures/kapcha4.jpg";
        } else {
            return "src/kapchasPictures/kapcha5.jpg";
        }
    }

    static int correctEntry (int number) {
        if (number == 1) {
            return 1436;
        } else if (number == 2) {
            return 6404;
        } else if (number == 3) {
            return 6044;
        } else if (number == 4) {
            return 6949;
        } else {
            return 3050;
        }
    }
}
