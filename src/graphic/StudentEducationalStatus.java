package graphic;

import logic.RequestsController;
import logic.ScoresAndReportCardController;

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
    static boolean isStudent;

    public StudentEducationalStatus(boolean userIsStudent,String userUsername,String userPassword) {
        password = userPassword;
        username = userUsername;
        isStudent = userIsStudent;
        numberOfPassedUnits = new JLabel();
        totalAverageScore = new JLabel();
        topOfThePageInformation = new JPanel();
        panelOfEducationalStatus = new JPanel();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,750);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        setButtonFeatures();
        setPanelsFeatures();
        if (isStudent) {
            setLabelsForStudents();
            setTableOfLessons();
        } else {
            //TODO
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
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
        }
    }
}
