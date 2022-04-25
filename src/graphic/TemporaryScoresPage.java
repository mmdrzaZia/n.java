package graphic;

import Log.LogInformation;
import logic.LessonController;
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
    static String lessonNameForProfile;
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
    static JComboBox listOfLessonsForEducationalAssistant;
    static JButton seeScoresOfALesson;
    static JComboBox listOfStudentForEducationalAssistant;
    static JButton seeTemporaryScoresOfAStudent;
    static JComboBox listOfTeachersForEducationalAssistant;
    static JButton seeRegisteredScoresByTeacher;
    static JComboBox listOfLessonsThatIsNotTemporaryRegistration;
    static JButton seeInformationOfALesson;
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
        seeScoresOfALesson = new JButton("See scores");
        seeTemporaryScoresOfAStudent = new JButton("See temporary scores");
        seeRegisteredScoresByTeacher = new JButton("See registered scores");
        seeInformationOfALesson = new JButton("See information");
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
            setComboBoxesAndButtonsForEducationalAssistant();
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

    private void setComboBoxesAndButtonsForEducationalAssistant () {
        String[] listOfLessons = LessonController.seeLessonsOfADepartment(username);
        listOfLessonsForEducationalAssistant = new JComboBox(listOfLessons);
        listOfLessonsForEducationalAssistant.setBounds(550,100,150,25);
        panelOfTemporaryScores.add(listOfLessonsForEducationalAssistant);

        seeScoresOfALesson.setBounds(300,100,175,25);
        seeScoresOfALesson.setBackground(Color.GRAY);
        seeScoresOfALesson.addActionListener(this);
        panelOfTemporaryScores.add(seeScoresOfALesson);

        String[] listOfStudents = UserController.seeStudentOfADepartment(username);
        listOfStudentForEducationalAssistant = new JComboBox(listOfStudents);
        listOfStudentForEducationalAssistant.setBounds(550,175,150,25);
        panelOfTemporaryScores.add(listOfStudentForEducationalAssistant);

        seeTemporaryScoresOfAStudent.setBounds(300,175,175,25);
        seeTemporaryScoresOfAStudent.setBackground(Color.GRAY);
        seeTemporaryScoresOfAStudent.addActionListener(this);
        panelOfTemporaryScores.add(seeTemporaryScoresOfAStudent);

        String[] listOfTeachers = UserController.seeTeachersOfADepartment(username);
        listOfTeachersForEducationalAssistant = new JComboBox(listOfTeachers);
        listOfTeachersForEducationalAssistant.setBounds(550,250,150,25);
        panelOfTemporaryScores.add(listOfTeachersForEducationalAssistant);

        seeRegisteredScoresByTeacher.setBounds(300,250,175,25);
        seeRegisteredScoresByTeacher.setBackground(Color.GRAY);
        seeRegisteredScoresByTeacher.addActionListener(this);
        panelOfTemporaryScores.add(seeRegisteredScoresByTeacher);

        String[] listOfLessonsNotTemporaryRegistration = LessonController.seeLessonsOfADepartmentThatIsNotTemporaryRegistration(username);
        listOfLessonsThatIsNotTemporaryRegistration = new JComboBox(listOfLessonsNotTemporaryRegistration);
        listOfLessonsThatIsNotTemporaryRegistration.setBounds(550,325,150,25);
        panelOfTemporaryScores.add(listOfLessonsThatIsNotTemporaryRegistration);

        seeInformationOfALesson.setBounds(300,325,175,25);
        seeInformationOfALesson.setBackground(Color.GRAY);
        seeInformationOfALesson.addActionListener(this);
        panelOfTemporaryScores.add(seeInformationOfALesson);
    }

    private void determineTemporaryOrFinalScoreTableForTeachers (String lessonName) {
        if (ScoresAndReportCardController.isTemporaryOrFinal(lessonName) & typeOfUser != 5) {
            setTableOfTemporaryScoreForTeachers(lessonName);
        } else if (ScoresAndReportCardController.isTemporaryOrFinal(lessonName) & typeOfUser == 5) {
            setTableOfTemporaryScoreForEducationalAssistant(lessonName);
        } else {
            setTableForFinalScoresForTeachers(lessonName);
        }
    }

    private void setTableOfTemporaryScoreForEducationalAssistant (String lessonName) {
        titleOfTable.setText("Temporary scores of " + lessonName);
        String[][] data = ScoresAndReportCardController.seeTemporaryScoresOfALessonByEducationalAssistant(lessonName);
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
                            LogInformation.createLogStatement("TemporaryScoresPage","setTableOfTemporaryScoreForTeachers",username + " answered recommendation request to " + studentName,"info");
                        }
                    } else if (column == 6) {
                        double entryScore = Double.parseDouble((String) temporaryScoresTable.getModel().getValueAt(row, 2));
                        if (entryScore != -1) {
                            double rondScore = ScoresAndReportCardController.setTemporaryScoreForAStudent(studentName, lessonName, entryScore);
                            temporaryScoresTable.setValueAt(String.valueOf(rondScore), row, 2);
                            message.setText("You have been registered score successfully");
                            LogInformation.createLogStatement("TemporaryScoresPage","setTableOfTemporaryScoreForTeachers",username + " have been registered score successfully" ,"info");
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
        String[] column = {"Student name","Student number","Final score","Objection","Answer of Objection"};
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
                                message.setText("You have been objected successfully");
                                LogInformation.createLogStatement("TemporaryScoresPage","setTableOfLessonsScoresForStudent",username + " have been objected successfully for " + lessonName,"info");
                            } else {
                                message.setText("You have already objected");
                                LogInformation.createLogStatement("TemporaryScoresPage","setTableOfLessonsScoresForStudent",username + " have already objected for " + lessonName,"error");
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

    private void setTableOfTemporaryScoresOfAStudentForEducationalAssistant (String studentName) {
        titleOfTable.setText("Temporary scores");
        String[][] data = ScoresAndReportCardController.seeTemporaryScoresOfAStudentsByEducationalAssistant(studentName);
        String[] column = {"Lesson name", "Temporary score", "Objection", "Answer of Objection"};
        temporaryScoresTable = new JTable(data, column);
        scrollPane = new JScrollPane(temporaryScoresTable);
        scrollPane.setBounds(0, 200, 730, 100);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfTemporaryScores.add(scrollPane);
        panelOfTemporaryScores.repaint();
        panelOfTemporaryScores.revalidate();
        frame.repaint();
        frame.revalidate();
    }

    private static void hideComboBoxesAndButtons () {
        listOfLessonsForEducationalAssistant.setVisible(false);
        seeScoresOfALesson.setVisible(false);
        listOfStudentForEducationalAssistant.setVisible(false);
        seeTemporaryScoresOfAStudent.setVisible(false);
        listOfTeachersForEducationalAssistant.setVisible(false);
        seeRegisteredScoresByTeacher.setVisible(false);
        listOfLessonsThatIsNotTemporaryRegistration.setVisible(false);
        seeInformationOfALesson.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GeneralFormOfPag.backToMainPage) {
            frame.dispose();
            LogInformation.createLogStatement("TemporaryScoresPage","clickOnBackButton","Back to main page","info");
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
        } else if (e.getSource() == filterLessons) {
            determineTemporaryOrFinalScoreTableForTeachers(listOfTeacherLessons.getSelectedItem().toString());
        } else if (e.getSource() == registerAsFinalScore) {
            if (ScoresAndReportCardController.registerFinalScoresForALesson(listOfTeacherLessons.getSelectedItem().toString())) {
                message.setText("You have been registered scores as final scores successfully");
                LogInformation.createLogStatement("TemporaryScoresPage","clickOnRegisterScoreAsFinalScore",username + " have been registered scores as final scores successfully for " + listOfTeacherLessons.getSelectedItem().toString(),"info");
            } else {
                message.setText("You don't complete the temporary scores");
                LogInformation.createLogStatement("TemporaryScoresPage","clickOnRegisterScoreAsFinalScore",username + " don't complete the temporary scores for " + listOfTeacherLessons.getSelectedItem().toString(),"error");
            }
        } else if (e.getSource() == seeScoresOfALesson) {
            hideComboBoxesAndButtons();
            determineTemporaryOrFinalScoreTableForTeachers(listOfLessonsForEducationalAssistant.getSelectedItem().toString());
        } else if (e.getSource() == seeTemporaryScoresOfAStudent) {
            hideComboBoxesAndButtons();
            setTableOfTemporaryScoresOfAStudentForEducationalAssistant(listOfStudentForEducationalAssistant.getSelectedItem().toString());
        } else if (e.getSource() == seeRegisteredScoresByTeacher) {
            hideComboBoxesAndButtons();
            setComboBoxAndFilterButton();
        } else if (e.getSource() == seeInformationOfALesson) {
            hideComboBoxesAndButtons();
            lessonNameForProfile = listOfLessonsThatIsNotTemporaryRegistration.getSelectedItem().toString();
            ProfilePage profilePage = new ProfilePage(-1,username,password);
        }
    }
}
