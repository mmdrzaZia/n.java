package graphic;

import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTeachersPage implements ActionListener {
    static String username;
    static String password;
    static String teacherName;
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
    static JLabel teacherNumber;
    static JTextField userTeacherNumber;
    static JLabel teacherPosition;
    static JLabel roomNumber;
    static JLabel generalPosition;
    static JLabel userUsername;
    static JLabel userPassword;
    static JTextField entryUsername;
    static JTextField entryPassword;
    static JLabel message;
    static JComboBox entryTeacherPosition;
    static JTextField entryRoomNumber;
    static JComboBox entryGeneralPosition;
    static JButton registerNewInformation;
    static boolean isEdit;

    void setPageOfEditTeachers(String userUsername, String userPassword, String consideredTeacherName) {
        username = userUsername;
        password = userPassword;
        teacherName = consideredTeacherName;
        isEdit = true;
        setGeneralFeaturesOfPage();
        setLabelsAndTextFieldsFeaturesForEdit();
        frame.add(panelOfEditPage);
        frame.setVisible(true);
    }

    private void setGeneralFeaturesOfPage () {
        topOfPageInformation = new JPanel();
        panelOfEditPage = new JPanel();
        completeName = new JLabel();
        nationalCode = new JLabel();
        phoneNumber = new JLabel();
        emailAddress = new JLabel();
        departmentName = new JLabel();
        teacherNumber = new JLabel();
        teacherPosition = new JLabel();
        roomNumber = new JLabel();
        generalPosition = new JLabel();
        entryRoomNumber = new JTextField();
        userCompleteName = new JTextField();
        userNationalCode = new JTextField();
        userPhoneNumber = new JTextField();
        userEmailAddress = new JTextField();
        userTeacherNumber = new JTextField();
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

    void setPageForAddATeacher() {
        setGeneralFeaturesOfPage();
        setLabelsAndTextFieldsFeaturesForAddATeacher();
        isEdit = false;
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

    private void setLabelsAndTextFieldsFeaturesForEdit () {
        completeName.setBounds(25,100,400,25);
        completeName.setText("Complete name : " + teacherName);
        panelOfEditPage.add(completeName);

        nationalCode.setBounds(25,160,400,25);
        nationalCode.setText("National code : " + UserController.getTeacherNationalCode(teacherName));
        panelOfEditPage.add(nationalCode);

        phoneNumber.setBounds(25,220,400,25);
        phoneNumber.setText("Phone number : " + UserController.getTeacherPhoneNumber(teacherName));
        panelOfEditPage.add(phoneNumber);

        emailAddress.setBounds(25,280,400,25);
        emailAddress.setText("Email address : " + UserController.getTeacherEmail(teacherName));
        panelOfEditPage.add(emailAddress);

        departmentName.setBounds(25,340,400,25);
        departmentName.setText("Department name : " + UserController.getTeacherDepartmentName(teacherName));
        panelOfEditPage.add(departmentName);

        teacherNumber.setBounds(400,100,290,25);
        teacherNumber.setText("Teacher number : " + UserController.getTeacherNumber(teacherName));
        panelOfEditPage.add(teacherNumber);

        roomNumber.setBounds(400,160,160,25);
        roomNumber.setText("Room number : ");
        entryRoomNumber.setBounds(560,160,130,25);
        entryRoomNumber.setText(UserController.getTeacherRoomNumber(teacherName));
        panelOfEditPage.add(entryRoomNumber);
        panelOfEditPage.add(roomNumber);

        teacherPosition.setBounds(400,220,160,25);
        teacherPosition.setText("Teacher position : ");
        String[] positions = {"ASSISTANT_PROFESSOR","ASSOCIATE_PROFESSOR","FULL_PROFESSOR"};
        entryTeacherPosition = new JComboBox(positions);
        entryTeacherPosition.setBounds(560,220,130,25);
        panelOfEditPage.add(entryTeacherPosition);
        panelOfEditPage.add(teacherPosition);

        generalPosition.setBounds(400,280,160,25);
        generalPosition.setText("General position : ");
        String[] generalPositions = {"PROFESSOR","EDUCATIONAL_ASSISTANT"};
        entryGeneralPosition = new JComboBox(generalPositions);
        entryGeneralPosition.setBounds(560,280,130,25);
        panelOfEditPage.add(entryGeneralPosition);
        panelOfEditPage.add(generalPosition);
    }

    private void setLabelsAndTextFieldsFeaturesForAddATeacher () {
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

        teacherNumber.setBounds(400, 100, 160, 25);
        teacherNumber.setText("Teacher number : ");
        userTeacherNumber.setBounds(560,100,130,25);
        panelOfEditPage.add(userTeacherNumber);
        panelOfEditPage.add(teacherNumber);

        roomNumber.setBounds(400, 160, 160, 25);
        roomNumber.setText("Room number : ");
        entryRoomNumber.setBounds(560, 160, 130, 25);
        panelOfEditPage.add(entryRoomNumber);
        panelOfEditPage.add(roomNumber);

        teacherPosition.setBounds(400, 220, 160, 25);
        teacherPosition.setText("Teacher position : ");
        String[] positions = {"ASSISTANT_PROFESSOR", "ASSOCIATE_PROFESSOR", "FULL_PROFESSOR"};
        entryTeacherPosition = new JComboBox(positions);
        entryTeacherPosition.setBounds(560, 220, 130, 25);
        panelOfEditPage.add(entryTeacherPosition);
        panelOfEditPage.add(teacherPosition);

        generalPosition.setBounds(400, 280, 160, 25);
        generalPosition.setText("General position : ");
        String[] generalPositions = {"PROFESSOR", "EDUCATIONAL_ASSISTANT"};
        entryGeneralPosition = new JComboBox(generalPositions);
        entryGeneralPosition.setBounds(560, 280, 130, 25);
        panelOfEditPage.add(entryGeneralPosition);
        panelOfEditPage.add(generalPosition);

        userUsername.setBounds(400,340,160,25);
        userUsername.setText("Username : ");
        entryUsername.setBounds(560,340,130,25);
        panelOfEditPage.add(entryUsername);
        panelOfEditPage.add(userUsername);
    }

    private void setButtonsFeatures() {
        GeneralFormOfPag.backToMainPage.setVisible(true);
        GeneralFormOfPag.backToMainPage.addActionListener(this);

        registerNewInformation.setBounds(300,450,100,25);
        registerNewInformation.setBackground(Color.GRAY);
        registerNewInformation.addActionListener(this);
        panelOfEditPage.add(registerNewInformation);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GeneralFormOfPag.backToMainPage) {
            frame.dispose();
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
        } else if (e.getSource() == registerNewInformation) {
            if (isEdit) {
                UserController.editATeacher(teacherName, entryRoomNumber.getText(), entryTeacherPosition.getSelectedItem().toString(), entryGeneralPosition.getSelectedItem().toString());
                message.setText("You edited successfully");
            } else {
                if (UserController.addATeacher(entryUsername.getText(),entryPassword.getText(), entryGeneralPosition.getSelectedItem().toString(),userCompleteName.getText(),userEmailAddress.getText(),UserController.getUserDepartmentName(),userNationalCode.getText(),userPhoneNumber.getText(), entryTeacherPosition.getSelectedItem().toString(),Integer.parseInt(entryRoomNumber.getText()),userTeacherNumber.getText())) {
                    message.setText("User added successfully");
                } else {
                    message.setText("A user with this username exist");
                }
            }
        }
    }
}