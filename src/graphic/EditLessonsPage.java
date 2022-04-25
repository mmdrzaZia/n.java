package graphic;

import Log.LogInformation;
import logic.LessonController;
import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditLessonsPage implements ActionListener {
    static String username;
    static String password;
    static String lessonName;
    static JFrame frame;
    static JPanel topOfPageInformation;
    static JPanel panelOfEditPage;
    static JLabel completeName;
    static JTextField entryCompleteName;
    static JLabel numberOfLesson;
    static JTextField entryNumberOfLesson;
    static JLabel numberOfUnitsOfLesson;
    static JTextField entryNumberOfUnitsOfLesson;
    static JLabel departmentName;
    static JLabel teacherName;
    static JTextField entryTeacherName;
    static JLabel levelOfEducation;
    static JComboBox entryLevelOfEducation;
    static JLabel classDay;
    static JTextField entryClassDay;
    static JLabel classTime;
    static JTextField entryClassTime;
    static JLabel examDate;
    static JTextField entryExamDate;
    static JLabel examTime;
    static JTextField entryExamTime;
    static JLabel message;
    static JButton registerNewInformation;
    static boolean isEdit;

    private void setGeneralFeaturesOfPage () {
        topOfPageInformation = new JPanel();
        panelOfEditPage = new JPanel();
        completeName = new JLabel();
        numberOfLesson = new JLabel();
        numberOfUnitsOfLesson = new JLabel();
        teacherName = new JLabel();
        departmentName = new JLabel();
        levelOfEducation = new JLabel();
        classDay = new JLabel();
        classTime = new JLabel();
        examDate = new JLabel();
        examTime = new JLabel();
        entryCompleteName = new JTextField();
        entryNumberOfLesson = new JTextField();
        entryNumberOfUnitsOfLesson = new JTextField();
        entryTeacherName = new JTextField();
        entryClassDay = new JTextField();
        entryClassTime = new JTextField();
        entryExamDate = new JTextField();
        entryExamTime = new JTextField();
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

    void setPageOfEditLessons(String userUsername, String userPassword, String consideredLessonName) {
        username = userUsername;
        password = userPassword;
        lessonName = consideredLessonName;
        isEdit = true;
        setGeneralFeaturesOfPage();
        setLabelsAndTextFieldsFeaturesForEdit();
        frame.add(panelOfEditPage);
        frame.setVisible(true);
    }

    void setPageForAddALesson(String userUsername) {
        username = userUsername;
        setGeneralFeaturesOfPage();
        setLabelsAndTextFieldsFeaturesForAddALesson();
        isEdit = false;
        frame.add(panelOfEditPage);
        frame.setVisible(true);
    }

    private void setLabelsAndTextFieldsFeaturesForEdit () {
        completeName.setBounds(25,100,400,25);
        completeName.setText("Complete name : " + lessonName);
        panelOfEditPage.add(completeName);

        numberOfLesson.setBounds(25,160,400,25);
        numberOfLesson.setText("Lesson number : " + LessonController.getNumberOfLesson(lessonName));
        panelOfEditPage.add(numberOfLesson);

        numberOfUnitsOfLesson.setBounds(25,220,200,25);
        numberOfUnitsOfLesson.setText("Number of units of lesson : ");
        entryNumberOfUnitsOfLesson.setBounds(180,220,200,25);
        entryNumberOfUnitsOfLesson.setText(LessonController.getNumberOfUnitsOfLesson(lessonName));
        panelOfEditPage.add(entryNumberOfUnitsOfLesson);
        panelOfEditPage.add(numberOfUnitsOfLesson);

        teacherName.setBounds(25,280,200,25);
        teacherName.setText("Teacher name : ");
        entryTeacherName.setBounds(175,280,200,25);
        entryTeacherName.setText(LessonController.getTeacherName(lessonName));
        panelOfEditPage.add(entryTeacherName);
        panelOfEditPage.add(teacherName);

        departmentName.setBounds(25,340,400,25);
        departmentName.setText("Department name : " + LessonController.getDepartmentName(lessonName));
        panelOfEditPage.add(departmentName);

        classDay.setBounds(400,100,160,25);
        classDay.setText("Class day : ");
        entryClassDay.setBounds(560,100,130,25);
        entryClassDay.setText(LessonController.getClassDay(lessonName));
        panelOfEditPage.add(entryClassDay);
        panelOfEditPage.add(classDay);

        classTime.setBounds(400,160,160,25);
        classTime.setText("Class time : ");
        entryClassTime.setBounds(560,160,130,25);
        entryClassTime.setText(LessonController.getClassTime(lessonName));
        panelOfEditPage.add(entryClassTime);
        panelOfEditPage.add(classTime);

        examDate.setBounds(400,220,160,25);
        examDate.setText("Exam day : ");
        entryExamDate.setBounds(560,220,130,25);
        entryExamDate.setText(LessonController.getExamDay(lessonName));
        panelOfEditPage.add(entryExamDate);
        panelOfEditPage.add(examDate);

        examTime.setBounds(400,280,160,25);
        examTime.setText("Exam time : ");
        entryExamTime.setBounds(560,280,130,25);
        entryExamTime.setText(LessonController.getExamTime(lessonName));
        panelOfEditPage.add(entryExamTime);
        panelOfEditPage.add(examTime);
    }


    private void setLabelsAndTextFieldsFeaturesForAddALesson () {
        completeName.setBounds(25,100,200,25);
        completeName.setText("Complete name : ");
        entryCompleteName.setBounds(175,100,200,25);
        panelOfEditPage.add(entryCompleteName);
        panelOfEditPage.add(completeName);

        numberOfLesson.setBounds(25,160,200,25);
        numberOfLesson.setText("Lesson number : ");
        entryNumberOfLesson.setBounds(175,160,200,25);
        panelOfEditPage.add(entryNumberOfLesson);
        panelOfEditPage.add(numberOfLesson);

        numberOfUnitsOfLesson.setBounds(25,220,200,25);
        numberOfUnitsOfLesson.setText("Number of units of lesson : ");
        entryNumberOfUnitsOfLesson.setBounds(175,220,200,25);
        panelOfEditPage.add(entryNumberOfUnitsOfLesson);
        panelOfEditPage.add(numberOfUnitsOfLesson);

        teacherName.setBounds(25,280,200,25);
        teacherName.setText("Teacher name : ");
        entryTeacherName.setBounds(175,280,200,25);
        panelOfEditPage.add(entryTeacherName);
        panelOfEditPage.add(teacherName);

        departmentName.setBounds(25,340,400,25);
        departmentName.setText("Department name : " + UserController.getUserDepartmentName());
        panelOfEditPage.add(departmentName);

        classDay.setBounds(400,100,160,25);
        classDay.setText("Class day : ");
        entryClassDay.setBounds(560,100,130,25);
        panelOfEditPage.add(entryClassDay);
        panelOfEditPage.add(classDay);

        classTime.setBounds(400,160,160,25);
        classTime.setText("Class time : ");
        entryClassTime.setBounds(560,160,130,25);
        panelOfEditPage.add(entryClassTime);
        panelOfEditPage.add(classTime);

        examDate.setBounds(400,220,160,25);
        examDate.setText("Exam day : ");
        entryExamDate.setBounds(560,220,130,25);
        panelOfEditPage.add(entryExamDate);
        panelOfEditPage.add(examDate);

        examTime.setBounds(400,280,160,25);
        examTime.setText("Exam time : ");
        entryExamTime.setBounds(560,280,130,25);
        panelOfEditPage.add(entryExamTime);
        panelOfEditPage.add(examTime);

        levelOfEducation.setBounds(400, 340, 160, 25);
        levelOfEducation.setText("Level of education : ");
        String[] typeOfLevelsOfEducation = {"MASTER", "MSC","PHD"};
        entryLevelOfEducation = new JComboBox(typeOfLevelsOfEducation);
        entryLevelOfEducation.setBounds(560, 340, 130, 25);
        panelOfEditPage.add(entryLevelOfEducation);
        panelOfEditPage.add(levelOfEducation);
    }

    private void setPanelsFeatures() {
        topOfPageInformation.setPreferredSize(new Dimension(750, 127));
        topOfPageInformation.setLayout(new BorderLayout());
        topOfPageInformation.add(GeneralFormOfPag.topOfPage, BorderLayout.NORTH);
        frame.add(topOfPageInformation, BorderLayout.NORTH);

        panelOfEditPage.setPreferredSize(new Dimension(750, 623));
        panelOfEditPage.setLayout(null);
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
            LogInformation.createLogStatement("EditLessonPage","clickOnBackButton","Back to main page","info");
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
        } else if (e.getSource() == registerNewInformation) {
            if (isEdit) {
                LessonController.editLessonInformation(lessonName,entryNumberOfUnitsOfLesson.getText(),entryTeacherName.getText(),entryClassDay.getText(),entryClassTime.getText(),entryExamDate.getText(),examTime.getText());
                message.setText("You edited successfully");
                LogInformation.createLogStatement("EditLessonPage","clickOnchangeButton",username + "edited the lesson successfully","info");
            } else {
                if (LessonController.addALesson(entryCompleteName.getText(),entryNumberOfLesson.getText(),entryNumberOfUnitsOfLesson.getText(),entryTeacherName.getText(),UserController.getUserDepartmentName(),entryLevelOfEducation.getSelectedItem().toString(),entryClassDay.getText(),entryClassTime.getText(),entryExamDate.getText(),entryExamTime.getText())) {
                    message.setText("Lesson added successfully");
                    LogInformation.createLogStatement("EditLessonPage","clickOnAddButton",username + "added the lesson successfully","info");
                } else {
                    message.setText("A lesson with this name exist");
                    LogInformation.createLogStatement("EditLessonPage","clickOnchangeButton","A lesson with this name exist","error");
                }
            }
        }
    }
}
