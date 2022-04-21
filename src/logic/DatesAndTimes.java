package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class DatesAndTimes {

    /*static ArrayList<Lessons> sortExamsDates (ArrayList<String> lessonsName) {
        ArrayList<Lessons> lessons = new ArrayList<>();
        int[] examsDays = new int[lessonsName.size()];
        for (int i = 0; i < examsDays.length; i++) {
            File lessonFile = FilesAndGsonBuilderMethods.findFileWhitName("src/LessonsFiles",lessonsName.get(i));
            String lessonInformation = FilesAndGsonBuilderMethods.getStringJson(lessonFile);
            Lessons lesson = FilesAndGsonBuilderMethods.getClassJson().fromJson(lessonInformation,Lessons.class);
            examsDays[i] = Integer.parseInt(lesson.examDate.substring(8));
        }
        Arrays.sort(examsDays);
        for (int i = 0; i < examsDays.length; i++) {
            for (int j = 0; j < lessonsName.size(); j++) {
                File lessonFile = FilesAndGsonBuilderMethods.findFileWhitName("src/LessonsFiles",lessonsName.get(j));
                String lessonInformation = FilesAndGsonBuilderMethods.getStringJson(lessonFile);
                Lessons lesson = FilesAndGsonBuilderMethods.getClassJson().fromJson(lessonInformation,Lessons.class);
                if (Integer.parseInt(lesson.examDate.substring(8)) == examsDays[i]) {

                }
            }
        }

    }*/
}
