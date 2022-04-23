package graphic;

import logic.RequestsController;
import logic.ScoresAndReportCardController;
import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TemporaryScoresPage implements ActionListener {
    static String password;
    static String username;
    static JFrame frame;
    static JPanel topOfThePageInformation;
    static JPanel panelOfTemporaryScores;
    static JLabel message;
    static JLabel titleOfTable;
    static JButton filterLessons;
    static JButton registerAsFinalScore;
    static JTable temporaryScoresTable;
    static JScrollPane scrollPane;
    static JComboBox listOfTeacherLessons;
    static int typeOfUser;

    public TemporaryScoresPage(int determineTypeOfUser,String userUsername,String userPassword) {
        password = userPassword;
        username = userUsername;
        typeOfUser = determineTypeOfUser;
        topOfThePageInformation = new JPanel();
        panelOfTemporaryScores = new JPanel();
        message = new JLabel();
        titleOfTable = new JLabel();
        filterLessons = new JButton("Filter");
        registerAsFinalScore = new JButton("Register as final scores");
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,750);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        setButtonFeatures();
        setPanelsFeatures();
        setLabelsFeatures();
        if (typeOfUser == 1 | typeOfUser == 2 | typeOfUser == 3) {
            setTableOfLessonsScoresForStudent();
        } else if (typeOfUser == 4 | typeOfUser == 6) {
            setComboBoxAndFilterButton();
        } else {
            //TODO
        }
        frame.add(panelOfTemporaryScores);
        frame.setVisible(true);
    }

    private void setButtonFeatures () {
        GeneralFormOfPag.backToMainPage.setVisible(true);
        GeneralFormOfPag.backToMainPage.addActionListener(this);

        registerAsFinalScore.setBounds(50,350,200,25);
        registerAsFinalScore.setBackground(Color.GRAY);
        registerAsFinalScore.addActionListener(this);
        registerAsFinalScore.setVisible(false);
        panelOfTemporaryScores.add(registerAsFinalScore);
    }

    private void setLabelsFeatures () {
        message.setBounds(100,500,600,50);
        panelOfTemporaryScores.add(message);

        titleOfTable.setBounds(300,150,400,25);
        panelOfTemporaryScores.add(titleOfTable);
    }

    private void setPanelsFeatures () {
        frame.add(GeneralFormOfPag.topOfPage,BorderLayout.NORTH);

        panelOfTemporaryScores.setPreferredSize(new Dimension(750,639));
        panelOfTemporaryScores.setLayout(null);
    }

    private void setComboBoxAndFilterButton () {
        String[] listOfLessons = UserController.getLessonsOfATeacher();
        listOfTeacherLessons = new JComboBox(listOfLessons);
        listOfTeacherLessons.setBounds(550,25,150,25);
        panelOfTemporaryScores.add(listOfTeacherLessons);

        filterLessons.setBounds(375,25,100,25);
        filterLessons.setBackground(Color.gray);
        filterLessons.addActionListener(this);
        panelOfTemporaryScores.add(filterLessons);
    }

    private void determineTemporaryOrFinalScoreTable (String lessonName) {
        if (ScoresAndReportCardController.isTemporaryOrFinal(lessonName)) {
            setTableOfTemporaryScoreForTeachers(lessonName);
        } else {
            setTableForFinalScoresForTeachers(lessonName);
        }
    }

    private void setTableOfTemporaryScoreForTeachers (String lessonName) {
        registerAsFinalScore.setVisible(true);
        titleOfTable.setText("Temporary scores of " + lessonName);
        String[][] data = ScoresAndReportCardController.seeTemporaryScoresByTeachers(lessonName);
        String[] column = {"Student name","Student number","Temporary score","Objection","Answer of Objection","Register answer","Register score"};
        temporaryScoresTable = new JTable(data,column);
        temporaryScoresTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    String studentName = (String) temporaryScoresTable.getModel().getValueAt(row,0);
                    if (column == 5) {
                        String answerOfObjection = (String) temporaryScoresTable.getModel().getValueAt(row, 4);
                        if (answerOfObjection != null && !answerOfObjection.equals("")) {
                            RequestsController.answerToAObjection(username,studentName,lessonName,answerOfObjection);
                            message.setText("Your answered have been registered successfully");
                        }
                    } else if (column == 6) {
                        double entryScore = Double.parseDouble((String) temporaryScoresTable.getModel().getValueAt(row, 2));
                        if (entryScore != -1) {
                            double rondScore = ScoresAndReportCardController.setTemporaryScoreForAStudent(studentName, lessonName, entryScore);
                            temporaryScoresTable.setValueAt(String.valueOf(rondScore), row, 2);
                            message.setText("You have been registered score successfully");
                        } else {
                            message.setText("Period of your entry score is incorrect");
                        }
                    }
                }
            }
        });
        scrollPane = new JScrollPane(temporaryScoresTable);
        scrollPane.setBounds(0,200,730,100);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfTemporaryScores.add(scrollPane);
        panelOfTemporaryScores.repaint();
        panelOfTemporaryScores.revalidate();
        frame.repaint();
        frame.revalidate();
    }

    private void setTableForFinalScoresForTeachers (String lessonName) {
        titleOfTable.setText("Final scores of " + lessonName);
        String[][] data = ScoresAndReportCardController.seeFinalScoresByTeachers(lessonName);
        String[] column = {"Student name","Student number","Temporary score","Objection","Answer of Objection"};
        temporaryScoresTable = new JTable(data,column);
        scrollPane = new JScrollPane(temporaryScoresTable);
        scrollPane.setBounds(0,200,730,100);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfTemporaryScores.add(scrollPane);
        panelOfTemporaryScores.repaint();
        panelOfTemporaryScores.revalidate();
        frame.repaint();
        frame.revalidate();
    }

    private void setTableOfLessonsScoresForStudent () {
        titleOfTable.setText("Temporary scores");
        String[][] data = ScoresAndReportCardController.seeTemporaryScoresByStudents(username);
        String[] column = {"Lesson name","Temporary score","Objection","Answer of Objection","Register objection"};
        temporaryScoresTable = new JTable(data,column);
        temporaryScoresTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    if (column == 4) {
                        String objection = (String) temporaryScoresTable.getModel().getValueAt(row, 2);
                        String lessonName = (String) temporaryScoresTable.getModel().getValueAt(row,0);
                        if (objection != null && !objection.equals("")) {
                            if (RequestsController.addObjection(username,lessonName,objection)) {
                                message.setText("You have objected successfully");
                            } else {
                                message.setText("You have already objected");
                            }
                        }
                    }
                }
            }
        });
        scrollPane = new JScrollPane(temporaryScoresTable);
        scrollPane.setBounds(0,200,730,100);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfTemporaryScores.add(scrollPane);
        panelOfTemporaryScores.repaint();
        panelOfTemporaryScores.revalidate();
        frame.repaint();
        frame.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GeneralFormOfPag.backToMainPage) {
            frame.dispose();
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
        } else if (e.getSource() == filterLessons) {
            determineTemporaryOrFinalScoreTable(listOfTeacherLessons.getSelectedItem().toString());
        } else if (e.getSource() == registerAsFinalScore) {
            if (ScoresAndReportCardController.registerFinalScoresForALesson(listOfTeacherLessons.getSelectedItem().toString())) {
                message.setText("You have been registered scores as final scores successfully");
            } else {
                message.setText("You don't complete the temporary scores");
            }
        }
    }
}
