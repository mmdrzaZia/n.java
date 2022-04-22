package logic;

import java.util.Map;

public class ScoresAndReportCardController {

    public static int getNumberOfPassedUnits (String username) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
        return Students.seeNumberOfPassedUnits(student.completeName);
    }

    public static double getTotalAverageScore (String username) {
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
        return student.calculateTotalAverage();
    }

    public static String[][] seeFinalScores (String username) {
        boolean haveFinalScore;
        Students student = FilesAndGsonBuilderMethods.convertFileToStudent(username);
        String[][] finalScores = new String[student.lessons.size()][2];
        for (int i = 0; i < student.lessons.size(); i++) {
            haveFinalScore = false;
            finalScores[i][0] = student.lessons.get(i);
            for (Map.Entry<String, Double> entry : student.scores.entrySet()) {
                if (entry.getKey().equals(student.lessons.get(i))) {
                    finalScores[i][1] = String.valueOf(entry.getValue());
                    haveFinalScore = true;
                }
            }
            if (!haveFinalScore) {
                finalScores[i][1] = "N/A";
            }
        }
        return finalScores;
    }
}
