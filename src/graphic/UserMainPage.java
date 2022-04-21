package graphic;

import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMainPage extends GeneralFormOfPag{
    static JLabel studyingCondition = new JLabel();
    static JLabel supervisorName = new JLabel();
    static JLabel registrationLicense = new JLabel();
    static JLabel registrationTime = new JLabel();
    static JMenu registrationMatters = new JMenu("Registration matters");
    static JMenu educationalServices = new JMenu("Educational services");
    static JMenu recordAffairs = new JMenu("Record affairs");
    static JMenu profile = new JMenu("Profile");
    static JMenuItem lessonsList = new JMenuItem("List of lessons");
    static JMenuItem teachersList = new JMenuItem("List of teachers");
    static JMenuItem weeklySchedule = new JMenuItem("Weekly schedule");
    static JMenuItem examsList = new JMenuItem("List of exams");
    static JMenu requests = new JMenu("Requests");
    static JMenuItem temporaryScores = new JMenuItem("Temporary scores");
    static JMenuItem educationalStatus = new JMenuItem("Educational status");
    static JMenuItem recommendationRequest = new JMenuItem("Recommendation request");
    static JMenuItem certificateStudentRequest = new JMenuItem("Certificate student request");
    static JMenuItem minorRequest = new JMenuItem("Minor request");
    static JMenuItem withdrawalFromEducationRequest = new JMenuItem("Withdrawal from education request");
    static JMenuItem dormRequest = new JMenuItem("Dorm request");
    static JMenuItem thesisDefenceRequest = new JMenuItem("Thesis defence request");

    public UserMainPage(String userUsername, String userPassword) {
        super(userUsername, userPassword);
    }

    static void fillMainPage () {
        if (UserController.determineTheTypeOfUser() == 1) {
            setLabelsFeatures(username);
            masterStudentMenu();
        } else if (UserController.determineTheTypeOfUser() == 2) {
            setLabelsFeatures(username);
            mscStudentMenu();
        } else if (UserController.determineTheTypeOfUser() == 3) {
            setLabelsFeatures(username);
            phdStudentMenu();
        } else if (UserController.determineTheTypeOfUser() == 4) {

        } else if (UserController.determineTheTypeOfUser() == 5) {

        } else if (UserController.determineTheTypeOfUser() == 6) {

        }
        informationPanel.add(centerInformationPanel,BorderLayout.CENTER);
        addActionListeners();
    }

    private static void setLabelsFeatures (String username) {
        studyingCondition.setBounds(200,200,400,50);
        studyingCondition.setText("Studying condition : " + "         " + UserController.getStudyingCondition());
        studyingCondition.setForeground(Color.BLUE);
        centerInformationPanel.add(studyingCondition);

        supervisorName.setBounds(200,250,400,50);
        supervisorName.setText("Supervisor name : " + "         " + UserController.getSupervisorName());
        supervisorName.setForeground(Color.BLUE);
        centerInformationPanel.add(supervisorName);

        registrationLicense.setBounds(200,300,400,50);
        registrationLicense.setText("Registration license : " + "         " + "A registration permit has been issued!");
        registrationLicense.setForeground(Color.BLUE);
        centerInformationPanel.add(registrationLicense);

        registrationTime.setBounds(200,350,400,50);
        registrationTime.setText("Registration time : " + "         " + "Not yet specified!");
        registrationTime.setForeground(Color.BLUE);
        centerInformationPanel.add(registrationTime);
    }

    private static void masterStudentMenu () {
        requests.add(recommendationRequest);
        requests.add(minorRequest);
        addJMenusForStudents();
    }

    private static void mscStudentMenu () {
        requests.add(recommendationRequest);
        requests.add(dormRequest);
        addJMenusForStudents();
    }

    private static void phdStudentMenu () {
        requests.add(thesisDefenceRequest);
        addJMenusForStudents();
    }

    private static void addJMenusForStudents () {
        registrationMatters.add(lessonsList);
        registrationMatters.add(teachersList);

        educationalServices.add(weeklySchedule);
        educationalServices.add(examsList);
        requests.add(certificateStudentRequest);
        requests.add(withdrawalFromEducationRequest);
        educationalServices.add(requests);

        recordAffairs.add(temporaryScores);
        recordAffairs.add(educationalStatus);

        menuBar.add(registrationMatters);
        menuBar.add(educationalServices);
        menuBar.add(recordAffairs);
        menuBar.add(profile);
    }

    private static void addActionListeners () {
        lessonsList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                SeeLessonsAndTeachers seeLessonsAndTeachers = new SeeLessonsAndTeachers(username,password,true,true);
            }
        });
        teachersList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                SeeLessonsAndTeachers seeLessonsAndTeachers = new SeeLessonsAndTeachers(username,password,false,true);
            }
        });
        weeklySchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                SeeWeeklyAndExamsSchedule seeWeeklyAndExamsSchedule = new SeeWeeklyAndExamsSchedule(true,true,username,password);
            }
        });
    }
}
