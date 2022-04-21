package graphic;

import javax.swing.*;
import java.text.*;
import java.util.*;

public class ShowCurrentTime implements Runnable {
    Thread t = null;
    int hours = 0;
    int minutes = 0;
    int seconds = 0;
    String timeString = "";
    JButton button;

    public ShowCurrentTime(JButton button) {
        t = new Thread(this);
        t.start();
        this.button = button;
    }

    public void run() {
        try {
            while (true) {
                Calendar calender = Calendar.getInstance();
                hours = calender.get(Calendar.HOUR_OF_DAY);
                if (hours > 12) hours -= 12;
                minutes = calender.get(Calendar.MINUTE);
                seconds = calender.get(Calendar.SECOND);
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date date = calender.getTime();
                timeString = formatter.format(date);
                printTime();
                t.sleep(1000);
            }
        } catch (Exception e) {
        }
    }

    public void printTime() {
        button.setText(timeString);
    }
}


