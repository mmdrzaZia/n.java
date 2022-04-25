package graphic;

import Log.LogInformation;
import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudent implements ActionListener {
    static String username;
    static String password;
    static JFrame frame;
    static JPanel topOfPageInformation;
    static JPanel panelOfEditPage;
    static JLabel completeName;
    static JTextField userCompleteName;
    static JLabel nationalCode;
    static JTextField userNationalCode;
    static JLabel phoneNumber;
    static JTextField userPhoneNumber;
    static JLabel emailAddress;
    static JTextField userEmailAddress;
    static JLabel departmentName;
    static JLabel studentNumber;
    static JTextField userStudentNumber;
    static JLabel studentPosition;
    static JLabel studentCondition;
    static JLabel userUsername;
    static JLabel userPassword;
    static JTextField entryUsername;
    static JTextField entryPassword;
    static JLabel supervisorName;
    static JTextField entrySupervisorName;
    static JLabel entryYear;
    static JTextField entryEntryYear;
    static JLabel lessons;
    static JTextField entryLessons;
    static JLabel message;
    static JComboBox entryStudentPosition;
    static JComboBox entryStudentCondition;
    static JButton registerNewInformation;

    private void setGeneralFeaturesOfPage () {
        topOfPageInformation = new JPanel();
        panelOfEditPage = new JPanel();
        completeName = new JLabel();
        nationalCode = new JLabel();
        phoneNumber = new JLabel();
        emailAddress = new JLabel();
        departmentName = new JLabel();
        studentNumber = new JLabel();
        studentPosition = new JLabel();
        studentCondition = new JLabel();
        supervisorName = new JLabel();
        entryYear = new JLabel();
        lessons = new JLabel();
        entrySupervisorName = new JTextField();
        entryLessons = new JTextField();
        entryEntryYear = new JTextField();
        userCompleteName = new JTextField();
        userNationalCode = new JTextField();
        userPhoneNumber = new JTextField();
        userEmailAddress = new JTextField();
        userStudentNumber = new JTextField();
        userUsername = new JLabel();
        userPassword = new JLabel();
        entryUsername = new JTextField();
        entryPassword = new JTextField();
        registerNewInformation = new JButton("Register");
        message = new JLabel();
        message.setBounds(100,500,600,50);
        panelOfEditPage.add(message);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 750);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        setButtonsFeatures();
        setPanelsFeatures();
    }

    private void setButtonsFeatures() {
        GeneralFormOfPag.backToMainPage.setVisible(true);
        GeneralFormOfPag.backToMainPage.addActionListener(this);

        registerNewInformation.setBounds(500,450,100,25);
        registerNewInformation.setBackground(Color.GRAY);
        registerNewInformation.addActionListener(this);
        panelOfEditPage.add(registerNewInformation);
    }

    void setPageForAddAStudent (String userUsername, String userPassword) {
        username = userUsername;
        password = userPassword;
        setGeneralFeaturesOfPage();
        setLabelsAndTextFieldsFeaturesForAddAStudent();
        frame.add(panelOfEditPage);
        frame.setVisible(true);
    }

    private void setPanelsFeatures() {
        topOfPageInformation.setPreferredSize(new Dimension(750, 127));
        topOfPageInformation.setLayout(new BorderLayout());
        topOfPageInformation.add(GeneralFormOfPag.topOfPage, BorderLayout.NORTH);
        frame.add(topOfPageInformation, BorderLayout.NORTH);

        panelOfEditPage.setPreferredSize(new Dimension(750, 623));
        panelOfEditPage.setLayout(null);
    }

    private void setLabelsAndTextFieldsFeaturesForAddAStudent() {
        completeName.setBounds(25, 100, 200, 25);
        completeName.setText("Complete name : ");
        userCompleteName.setBounds(175,100,200,25);
        panelOfEditPage.add(userCompleteName);
        panelOfEditPage.add(completeName);

        nationalCode.setBounds(25, 160, 200, 25);
        nationalCode.setText("National code : ");
        userNationalCode.setBounds(175,160,200,25);
        panelOfEditPage.add(userNationalCode);
        panelOfEditPage.add(nationalCode);

        phoneNumber.setBounds(25, 220, 200, 25);
        phoneNumber.setText("Phone number : ");
        userPhoneNumber.setBounds(175,220,200,25);
        panelOfEditPage.add(userPhoneNumber);
        panelOfEditPage.add(phoneNumber);

        emailAddress.setBounds(25, 280, 200, 25);
        emailAddress.setText("Email address : ");
        userEmailAddress.setBounds(175,280,200,25);
        panelOfEditPage.add(userEmailAddress);
        panelOfEditPage.add(emailAddress);

        departmentName.setBounds(25, 340, 400, 25);
        departmentName.setText("Department name : " + UserController.getUserDepartmentName());
        panelOfEditPage.add(departmentName);

        userPassword.setBounds(25,400,200,25);
        userPassword.setText("Password : ");
        entryPassword.setBounds(175,400,200,25);
        panelOfEditPage.add(entryPassword);
        panelOfEditPage.add(userPassword);

        lessons.setBounds(25,460,200,25);
        lessons.setText("Lessons : ");
        entryLessons.setBounds(175,460,200,25);
        panelOfEditPage.add(lessons);
        panelOfEditPage.add(entryLessons);

        studentNumber.setBounds(400, 100, 160, 25);
        studentNumber.setText("Student number : ");
        userStudentNumber.setBounds(560,100,130,25);
        panelOfEditPage.add(userStudentNumber);
        panelOfEditPage.add(studentNumber);

        supervisorName.setBounds(400, 160, 160, 25);
        supervisorName.setText("Supervisor name : ");
        entrySupervisorName.setBounds(560, 160, 130, 25);
        panelOfEditPage.add(entrySupervisorName);
        panelOfEditPage.add(supervisorName);

        studentPosition.setBounds(400, 220, 160, 25);
        studentPosition.setText("Educational degree : ");
        String[] positions = {"MASTER", "MSC", "PHD"};
        entryStudentPosition = new JComboBox(positions);
        entryStudentPosition.setBounds(560, 220, 130, 25);
        panelOfEditPage.add(entryStudentPosition);
        panelOfEditPage.add(studentPosition);

        studentCondition.setBounds(400, 280, 160, 25);
        studentCondition.setText("General position : ");
        String[] generalPositions = {"STUDYING", "GRADUATED"};
        entryStudentCondition = new JComboBox(generalPositions);
        entryStudentCondition.setBounds(560, 280, 130, 25);
        panelOfEditPage.add(entryStudentCondition);
        panelOfEditPage.add(studentCondition);

        userUsername.setBounds(400,340,160,25);
        userUsername.setText("Username : ");
        entryUsername.setBounds(560,340,130,25);
        panelOfEditPage.add(entryUsername);
        panelOfEditPage.add(userUsername);

        entryYear.setBounds(400,400,160,25);
        entryYear.setText("Entry year : ");
        entryEntryYear.setBounds(560,400,130,25);
        panelOfEditPage.add(entryYear);
        panelOfEditPage.add(entryEntryYear);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GeneralFormOfPag.backToMainPage) {
            frame.dispose();
            LogInformation.createLogStatement("AddStudent","clickOnBackButton","Back to main page","info");
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
        } else if (e.getSource() == registerNewInformation) {
            if (UserController.addAStudent(entryUsername.getText(),entryPassword.getText(),entryStudentPosition.getSelectedItem().toString(),userCompleteName.getText(),userEmailAddress.getText(),entryLessons.getText(),UserController.getUserDepartmentName(),userNationalCode.getText(),userPhoneNumber.getText(),entrySupervisorName.getText(),userStudentNumber.getText(),entryEntryYear.getText(),entryStudentCondition.getSelectedItem().toString())) {
                message.setText("User added successfully");
                LogInformation.createLogStatement("AddStudent","clickOnRegisterStudentButton","User added successfully","info");
            } else {
                message.setText("A user with this username exist");
                LogInformation.createLogStatement("AddStudent","clickOnRegisterStudentButton","A user with this username exist","error");
            }
        }
    }
}
