package graphic;

import logic.LessonController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeeWeeklyAndExamsSchedule implements ActionListener {
    static String username;
    static String password;
    static JFrame list;
    static JTable information;
    static JPanel topOfPageInformation = new JPanel();
    static JPanel panelOfTable = new JPanel();
    static JScrollPane scrollPane;

    public SeeWeeklyAndExamsSchedule(boolean isStudent,boolean seeWeeklySchedule, String userUsername, String userPassword) {
        username = userUsername;
        password = userPassword;
        list = new JFrame();
        list.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        list.setSize(750,750);
        list.setLayout(new BorderLayout());
        list.setLocationRelativeTo(null);
        panelOfTable.removeAll();
        setButtonsFeatures();
        setPanelsFeatures();
        if (seeWeeklySchedule) {
            seeWeeklyScheduleByUser(isStudent);
        } else {

        }
        panelOfTable.repaint();
        panelOfTable.revalidate();
        list.setVisible(true);
        list.repaint();
        list.revalidate();
    }

    private static void seeWeeklyScheduleByUser (boolean isStudent) {
        setTableOfWeeklySchedule(isStudent);
    }

    private static void setTableOfWeeklySchedule (boolean isStudent) {
        String[][] data = null;
        String[] column = null;
        if (isStudent) {
            data = LessonController.seeWeeklyScheduleByStudents(username);
            column = new String[]{"Name", "Lesson number", "Number of units", "Level of education", "Teacher name", "Department name", "Class time", "Exam time"};
        } else {
            data = LessonController.seeWeeklyScheduleByTeachers(username);
            column = new String[]{"Name", "Lesson number", "Number of units", "Level of education", "Department name", "Class time", "Exam time"};
        }
        information = new JTable(data,column);
        scrollPane = new JScrollPane(information);
        scrollPane.setBounds(0,100,730,300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfTable.add(scrollPane);
        list.add(panelOfTable,BorderLayout.CENTER);
    }

    private void setPanelsFeatures () {
        topOfPageInformation.setPreferredSize(new Dimension(750,127));
        topOfPageInformation.setLayout(new BorderLayout());
        topOfPageInformation.add(GeneralFormOfPag.topOfPage,BorderLayout.NORTH);
        list.add(topOfPageInformation,BorderLayout.NORTH);

        panelOfTable.setPreferredSize(new Dimension(750,623));
        panelOfTable.setLayout(null);
    }

    private void setButtonsFeatures () {
        GeneralFormOfPag.backToMainPage.setVisible(true);
        GeneralFormOfPag.backToMainPage.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GeneralFormOfPag.backToMainPage) {
            list.dispose();
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
        }
    }
}
