package Log;

import logic.DatesAndTimes;

import java.io.*;

public class LogInformation {
    static String log = "";

    public static void createLogStatement (String className,String methodeName,String logStatement,String typeOfInformation) {
        TypeOfInformation type = null;
        if (typeOfInformation.equals("info")) {
            type = TypeOfInformation.INFO;
        } else if (typeOfInformation.equals("error")) {
            type = TypeOfInformation.Error;
        }
        log = DatesAndTimes.getTimeOfNow() + " " + "[" + className + "] " + type.toString() + " " + "[" + methodeName + "]" + " - " + logStatement;
        writeInLogFile(log);
    }

    private static void writeInLogFile (String logStatement) {
        String path = "src/Log/log";
        File file = new File(path);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file,true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(logStatement);
    }
}
