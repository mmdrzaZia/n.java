package graphic;

import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMainPage extends GeneralFormOfPag{
    static JLabel studyingCondition;
    static JLabel supervisorName;
    static JLabel registrationLicense;
    static JLabel registrationTime;
    static JMenu registrationMatters;
    static JMenu educationalServices;
    static JMenu recordAffairs;
    static JMenuItem profile;
    static JMenu setting;
    static JMenuItem lessonsList;
    static JMenuItem teachersList;
    static JMenuItem weeklySchedule;
    static JMenuItem examsList;
    static JMenu requests;
    static JMenuItem temporaryScores;
    static JMenuItem educationalStatus;
    static JMenuItem recommendationRequest;
    static JMenuItem certificateStudentRequest;
    static JMenuItem minorRequest;
    static JMenuItem withdrawalFromEducationRequest;
    static JMenuItem dormRequest;
    static JMenuItem thesisDefenceRequest;
    static JMenu registerAUser;
    static JMenuItem registerAStudent;
    static JMenuItem registerATeacher;
    static JMenuItem changeEmailOrPhoneNumber;
    static int typeOfUser;

    public UserMainPage(String userUsername, String userPassword) {
        super(userUsername, userPassword);
    }

    static void fillMainPage () {
        studyingCondition = new JLabel();
        supervisorName = new JLabel();
        registrationLicense = new JLabel();
        registrationTime = new JLabel();
        registrationMatters = new JMenu("Registration matters");
        educationalServices = new JMenu("Educational services");
        recordAffairs = new JMenu("Record affairs");
        profile = new JMenuItem("Profile");
        setting = new JMenu("Setting");
        lessonsList = new JMenuItem("List of lessons");
        teachersList = new JMenuItem("List of teachers");
        weeklySchedule = new JMenuItem("Weekly schedule");
        examsList = new JMenuItem("List of exams");
        requests = new JMenu("Requests");
        temporaryScores = new JMenuItem("Temporary scores");
        educationalStatus = new JMenuItem("Educational status");
        recommendationRequest = new JMenuItem("Recommendation request");
        certificateStudentRequest = new JMenuItem("Certificate student request");
        minorRequest = new JMenuItem("Minor request");
        withdrawalFromEducationRequest = new JMenuItem("Withdrawal from education request");
        dormRequest = new JMenuItem("Dorm request");
        thesisDefenceRequest = new JMenuItem("Thesis defence request");
        registerAUser = new JMenu("Register a user");
        registerATeacher = new JMenuItem("Register a teacher");
        registerAStudent = new JMenuItem("Register a student");
        changeEmailOrPhoneNumber = new JMenuItem("Edit information");
        typeOfUser = UserController.determineTheTypeOfUser();
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
            professorAndBossOfDepartmentMenu();
        } else if (UserController.determineTheTypeOfUser() == 5) {
            educationalAssistantMenu();
        } else if (UserController.determineTheTypeOfUser() == 6) {
            professorAndBossOfDepartmentMenu();
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

    private static void professorAndBossOfDepartmentMenu() {
        addJMenusForTeachers();
    }

    private static void educationalAssistantMenu () {
        registrationMatters.add(registerAUser);
        registerAUser.add(registerAStudent);
        registerAUser.add(registerATeacher);
        requests.add(minorRequest);
        requests.add(withdrawalFromEducationRequest);
        requests.add(thesisDefenceRequest);
        recordAffairs.add(educationalStatus);
        addJMenusForTeachers();
    }

    private static void addJMenusForTeachers () {
        registrationMatters.add(lessonsList);
        registrationMatters.add(teachersList);

        educationalServices.add(weeklySchedule);
        educationalServices.add(examsList);
        requests.add(recommendationRequest);
        educationalServices.add(requests);

        recordAffairs.add(temporaryScores);

        setting.add(profile);
        setting.add(changeEmailOrPhoneNumber);

        menuBar.add(registrationMatters);
        menuBar.add(educationalServices);
        menuBar.add(recordAffairs);
        menuBar.add(setting);
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

        setting.add(profile);
        setting.add(changeEmailOrPhoneNumber);

        menuBar.add(registrationMatters);
        menuBar.add(educationalServices);
        menuBar.add(recordAffairs);
        menuBar.add(setting);
    }

    private static void addActionListeners () {
        lessonsList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                SeeLessonsAndTeachers seeLessonsAndTeachers = new SeeLessonsAndTeachers(username,password,true,typeOfUser);
            }
        });
        teachersList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                SeeLessonsAndTeachers seeLessonsAndTeachers = new SeeLessonsAndTeachers(username,password,false,typeOfUser);
            }
        });
        weeklySchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                SeeWeeklyAndExamsSchedule seeWeeklyAndExamsSchedule = new SeeWeeklyAndExamsSchedule(typeOfUser,true,username,password);
            }
        });
        examsList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                SeeWeeklyAndExamsSchedule seeWeeklyAndExamsSchedule = new SeeWeeklyAndExamsSchedule(typeOfUser,false,username,password);
            }
        });
        recommendationRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                RequestsPages requestsPages = new RequestsPages(1,typeOfUser,username,password);
            }
        });
        certificateStudentRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                RequestsPages requestsPages = new RequestsPages(2,typeOfUser,username,password);
            }
        });
        minorRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                RequestsPages requestsPages = new RequestsPages(3,typeOfUser,username,password);
            }
        });
        withdrawalFromEducationRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                RequestsPages requestsPages = new RequestsPages(4,typeOfUser,username,password);
            }
        });
        dormRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                RequestsPages requestsPages = new RequestsPages(5,typeOfUser,username,password);
            }
        });
        thesisDefenceRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                RequestsPages requestsPages = new RequestsPages(6,typeOfUser,username,password);
            }
        });
        educationalStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                StudentEducationalStatus studentEducationalStatus = new StudentEducationalStatus(typeOfUser,username,password);
            }
        });
        temporaryScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                TemporaryScoresPage temporaryScoresPage = new TemporaryScoresPage(typeOfUser,username,password);
            }
        });
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                ProfilePage profilePage = new ProfilePage(typeOfUser,username,password);
            }
        });
        registerATeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                EditTeachersPage editTeachersPage = new EditTeachersPage();
                editTeachersPage.setPageForAddATeacher(username,password);
            }
        });
        registerAStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                AddStudent addStudent = new AddStudent();
                addStudent.setPageForAddAStudent(username,password);
            }
        });
        changeEmailOrPhoneNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                ChangingEmailAndPhoneNumber changingEmailAndPhoneNumber = new ChangingEmailAndPhoneNumber(username,password);
            }
        });
    }
}
