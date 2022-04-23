package graphic;

import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePage implements ActionListener {
    static String password;
    static String username;
    static JFrame frame;
    static JPanel topOfThePageInformation;
    static JPanel panelOfProfile;
    static JLabel completeName;
    static JLabel nationalCode;
    static JLabel phoneNumber;
    static JLabel emailAddress;
    static JLabel departmentName;
    static JLabel studentNumber;
    static JLabel teacherNumber;
    static JLabel totalAverageScore;
    static JLabel supervisorName;
    static JLabel entryYear;
    static JLabel educationalDegree;
    static JLabel studentCondition;
    static JLabel teacherPosition;
    static JLabel roomNumber;
    static int typeOfUser;

    public ProfilePage (int determineTypeOfUser,String userUsername,String userPassword) {
        username = userUsername;
        password = userPassword;
        typeOfUser = determineTypeOfUser;
        frame = new JFrame();
        topOfThePageInformation = new JPanel();
        panelOfProfile = new JPanel();
        completeName = new JLabel();
        nationalCode = new JLabel();
        phoneNumber = new JLabel();
        emailAddress = new JLabel();
        departmentName = new JLabel();
        studentNumber = new JLabel();
        teacherNumber = new JLabel();
        totalAverageScore = new JLabel();
        supervisorName = new JLabel();
        entryYear = new JLabel();
        educationalDegree = new JLabel();
        studentCondition = new JLabel();
        teacherPosition = new JLabel();
        roomNumber = new JLabel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,750);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        setButtonFeatures();
        setPanelsFeatures();
        setCommonLabelsFeatures();
        if (typeOfUser == 1 | typeOfUser == 2 | typeOfUser == 3) {
            setLabelsForStudentProfile();
        } else {
            setLabelsForTeacherProfile();
        }
        frame.add(panelOfProfile,BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void setButtonFeatures () {
        GeneralFormOfPag.backToMainPage.setVisible(true);
        GeneralFormOfPag.backToMainPage.addActionListener(this);
    }

    private void setPanelsFeatures () {
        frame.add(GeneralFormOfPag.topOfPage,BorderLayout.NORTH);

        panelOfProfile.setPreferredSize(new Dimension(750,639));
        panelOfProfile.setLayout(null);
    }

    private void setCommonLabelsFeatures () {
        completeName.setBounds(25,100,400,25);
        completeName.setText("Complete name : " + UserController.getUserCompleteName());
        panelOfProfile.add(completeName);

        nationalCode.setBounds(25,160,400,25);
        nationalCode.setText("National code : " + UserController.getUserNationalCode());
        panelOfProfile.add(nationalCode);

        phoneNumber.setBounds(25,220,400,25);
        phoneNumber.setText("Phone number : " + UserController.getUserPhoneNumber());
        panelOfProfile.add(phoneNumber);

        emailAddress.setBounds(25,280,400,25);
        emailAddress.setText("Email address : " + UserController.getUserEmail());
        panelOfProfile.add(emailAddress);

        departmentName.setBounds(25,340,400,25);
        departmentName.setText("Department name : " + UserController.getUserDepartmentName());
        panelOfProfile.add(departmentName);
    }

    private void setLabelsForStudentProfile () {
        studentNumber.setBounds(450,100,290,25);
        studentNumber.setText("Student number : " + UserController.getStudentNumber());
        panelOfProfile.add(studentNumber);

        entryYear.setBounds(450,160,290,25);
        entryYear.setText("Entry year : " + UserController.getUserEntryYear());
        panelOfProfile.add(entryYear);

        supervisorName.setBounds(450,220,290,25);
        supervisorName.setText("Supervisor name : " + UserController.getUserSupervisorName());
        panelOfProfile.add(supervisorName);

        educationalDegree.setBounds(450,280,290,25);
        educationalDegree.setText("Educational degree : " + UserController.getUserEducationalDegree());
        panelOfProfile.add(educationalDegree);

        studentCondition.setBounds(450,340,290,25);
        studentCondition.setText("Student condition : " + UserController.getStudentCondition());
        panelOfProfile.add(studentCondition);

        totalAverageScore.setBounds(25,400,400,25);
        totalAverageScore.setText("Total average score : " + UserController.getStudentTotalAverageScore());
        panelOfProfile.add(totalAverageScore);
    }

    private void setLabelsForTeacherProfile () {
        teacherNumber.setBounds(450,100,290,25);
        teacherNumber.setText("Teacher number : " + UserController.getTeacherNumber());
        panelOfProfile.add(teacherNumber);

        roomNumber.setBounds(450,160,290,25);
        roomNumber.setText("Room number : " + UserController.getTeacherRoomNumber());
        panelOfProfile.add(roomNumber);

        teacherPosition.setBounds(450,220,290,25);
        teacherPosition.setText("Teacher position : " + UserController.getTeacherPosition());
        panelOfProfile.add(teacherPosition);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GeneralFormOfPag.backToMainPage) {
            frame.dispose();
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
        }
    }
}
