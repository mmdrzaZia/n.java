package graphic;

import Log.LogInformation;
import logic.ScoresAndReportCardController;
import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentEducationalStatus implements ActionListener {
    static String password;
    static String username;
    static JFrame frame;
    static JPanel topOfThePageInformation;
    static JPanel panelOfEducationalStatus;
    static JLabel numberOfPassedUnits;
    static JLabel totalAverageScore;
    static JTable lessons;
    static JScrollPane scrollPane;
    static JComboBox listOfStudent;
    static JButton filterByStudentName;
    static JComboBox listOfStudentNumber;
    static JButton filterByStudentNumber;
    static int typeOfUser;

    public StudentEducationalStatus(int determineTypeOfUser,String userUsername,String userPassword) {
        password = userPassword;
        username = userUsername;
        typeOfUser = determineTypeOfUser;
        numberOfPassedUnits = new JLabel();
        totalAverageScore = new JLabel();
        topOfThePageInformation = new JPanel();
        panelOfEducationalStatus = new JPanel();
        filterByStudentName = new JButton("Filter");
        filterByStudentNumber = new JButton("Filter");
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,750);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        setButtonFeatures();
        setPanelsFeatures();
        if (typeOfUser == 1 | typeOfUser == 2 | typeOfUser == 3) {
            setLabelsForStudents();
            setTableOfLessons();
        } else if (typeOfUser == 5) {
            setComboBoxesAndButtonsForEducationalAssistant();
        }
        frame.add(panelOfEducationalStatus);
        frame.setVisible(true);
    }

    private void setButtonFeatures () {
        GeneralFormOfPag.backToMainPage.setVisible(true);
        GeneralFormOfPag.backToMainPage.addActionListener(this);
    }

    private void setPanelsFeatures () {
        frame.add(GeneralFormOfPag.topOfPage,BorderLayout.NORTH);

        panelOfEducationalStatus.setPreferredSize(new Dimension(750,639));
        panelOfEducationalStatus.setLayout(null);
    }

    private void setComboBoxesAndButtonsForEducationalAssistant () {
        String[] listOfDepartmentStudents = UserController.seeStudentOfADepartment(username);
        listOfStudent = new JComboBox(listOfDepartmentStudents);
        listOfStudent.setBounds(550,100,150,25);
        panelOfEducationalStatus.add(listOfStudent);

        filterByStudentName.setBounds(325,100,150,25);
        filterByStudentName.setBackground(Color.GRAY);
        filterByStudentName.addActionListener(this);
        panelOfEducationalStatus.add(filterByStudentName);

        String[] listOfDepartmentStudentNumbers = UserController.seeStudentNumbersOfADepartment(username);
        listOfStudentNumber = new JComboBox(listOfDepartmentStudentNumbers);
        listOfStudentNumber.setBounds(550,150,150,25);
        panelOfEducationalStatus.add(listOfStudentNumber);

        filterByStudentNumber.setBounds(325,150,150,25);
        filterByStudentNumber.setBackground(Color.GRAY);
        filterByStudentNumber.addActionListener(this);
        panelOfEducationalStatus.add(filterByStudentNumber);
    }

    private void setLabelsForStudents () {
        int numberOfUnits = ScoresAndReportCardController.getNumberOfPassedUnits(username);
        numberOfPassedUnits.setBounds(50,100,400,25);
        numberOfPassedUnits.setText("Number of passed units : " + numberOfUnits);
        panelOfEducationalStatus.add(numberOfPassedUnits);

        double totalAverage = ScoresAndReportCardController.getTotalAverageScore(username);
        totalAverageScore.setBounds(50,150,400,25);
        totalAverageScore.setText("Total average score : " + totalAverage);
        panelOfEducationalStatus.add(totalAverageScore);
    }

    private void setTableOfLessons () {
        String[][] data = ScoresAndReportCardController.seeFinalScores(username);
        String[] column = {"Lesson name","Final scores"};
        lessons = new JTable(data,column);
        scrollPane = new JScrollPane(lessons);
        scrollPane.setBounds(0,200,730,100);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfEducationalStatus.add(scrollPane);
        panelOfEducationalStatus.repaint();
        panelOfEducationalStatus.revalidate();
        frame.repaint();
        frame.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GeneralFormOfPag.backToMainPage) {
            frame.dispose();
            LogInformation.createLogStatement("StudentEducationalStatus","clickOnBackButton","Back to main page","info");
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
        } else if (e.getSource() == filterByStudentName) {
            String[] studentUsernameAndPassword = UserController.getStudentUsernameAndPassword(listOfStudent.getSelectedItem().toString());
            frame.dispose();
            StudentEducationalStatus studentEducationalStatus = new StudentEducationalStatus(1,studentUsernameAndPassword[0],studentUsernameAndPassword[1]);
        } else if (e.getSource() == filterByStudentNumber) {
            String[] studentUsernameAndPassword = UserController.getStudentUsernameAndPassword(listOfStudentNumber.getSelectedItem().toString());
            frame.dispose();
            StudentEducationalStatus studentEducationalStatus = new StudentEducationalStatus(1,studentUsernameAndPassword[0],studentUsernameAndPassword[1]);
        }
    }
}
