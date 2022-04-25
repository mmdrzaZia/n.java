package logic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesAndTimes {

    public static String getTimeOfNow () {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timeOfNow = formatter.format(date);
        return timeOfNow;
    }
}
