package graphic;

import logic.LessonController;
import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeeLessonsAndTeachers implements ActionListener {
    static String username;
    static String password;
    static JFrame listFrame;
    static JTable information;
    static JPanel topOfPageInformation;
    static JPanel panelOfTable;
    static JScrollPane scrollPane;
    static JTextField consideredFilter;
    static JButton filterButton;
    static JComboBox listOfFilters;
    boolean wantListOfLessons;


    public SeeLessonsAndTeachers(String userUsername, String userPassword, boolean wantListOfLessons, boolean isStudent) {
        username = userUsername;
        password = userPassword;
        topOfPageInformation = new JPanel();
        panelOfTable = new JPanel();
        consideredFilter = new JTextField();
        filterButton = new JButton("Filter");
        listFrame = new JFrame();
        listFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listFrame.setSize(750,750);
        listFrame.setLayout(new BorderLayout());
        listFrame.setLocationRelativeTo(null);
        this.wantListOfLessons = wantListOfLessons;
        panelOfTable.removeAll();
        setButtonsFeatures();
        setFilterTextBox();
        setPanelsFeatures();
        if (wantListOfLessons & isStudent) {
            setComboBoxForLessons();
            seeListOfLessonsByStudent();
        } else if (!wantListOfLessons & isStudent) {
            setComboBoxForTeachers();
            seeListOfTeachersByStudent();
        }
        panelOfTable.repaint();
        panelOfTable.revalidate();
        listFrame.setVisible(true);
        listFrame.repaint();
        listFrame.revalidate();
    }

    private void seeListOfLessonsByStudent () {
        setTableOfLessonsInformation(null);
    }

    private void seeListOfTeachersByStudent () {
        setTableOfTeachersInformation(null);
    }

    private void setPanelsFeatures () {
        topOfPageInformation.setPreferredSize(new Dimension(750,127));
        topOfPageInformation.setLayout(new BorderLayout());
        topOfPageInformation.add(GeneralFormOfPag.topOfPage,BorderLayout.NORTH);
        listFrame.add(topOfPageInformation,BorderLayout.NORTH);

        panelOfTable.setPreferredSize(new Dimension(750,623));
        panelOfTable.setLayout(null);
    }

    private void setTableOfLessonsInformation (String filter) {
        String[][] data = null;
        if (filter == null) {
            data = LessonController.seeLessonsByStudent();
        } else {
            data = LessonController.seeLessonsByStudent(filter);
            panelOfTable.remove(scrollPane);
        }
        String[] column = {"Name","Lesson number","Number of units","Level of education","Teacher name","Department name","Class time","Exam time"};
        information = new JTable(data,column);
        scrollPane = new JScrollPane(information);
        scrollPane.setBounds(0,100,730,300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfTable.add(scrollPane);
        panelOfTable.repaint();
        panelOfTable.revalidate();
        listFrame.add(panelOfTable,BorderLayout.CENTER);
        listFrame.repaint();
        listFrame.revalidate();
    }

    private void setTableOfTeachersInformation (String filter) {
        String[][] data = null;
        if (filter == null) {
            data = UserController.seeListOfTeachersByStudent();
        } else {
            data = UserController.seeListOfTeachersByStudent(filter);
            panelOfTable.remove(scrollPane);
        }
        String[] column = {"Name","Email","Department name","Room number","Position","Lessons"};
        information = new JTable(data,column);
        scrollPane = new JScrollPane(information);
        scrollPane.setBounds(0,100,730,300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfTable.add(scrollPane);
        panelOfTable.repaint();
        panelOfTable.revalidate();
        listFrame.add(panelOfTable,BorderLayout.CENTER);
        listFrame.repaint();
        listFrame.revalidate();
    }

    private void setButtonsFeatures () {
        GeneralFormOfPag.backToMainPage.setVisible(true);
        GeneralFormOfPag.backToMainPage.addActionListener(this);

        filterButton.setBounds(200,25,100,25);
        filterButton.setForeground(Color.DARK_GRAY);
        filterButton.addActionListener(this);
        panelOfTable.add(filterButton);
    }

    private void setFilterTextBox () {
        consideredFilter.setBounds(375,25,150,25);
        panelOfTable.add(consideredFilter);
    }

    private void setComboBoxForLessons () {
        String[] listOfChoices = {"Department name", "Teacher name", "Class day", "Class time", "Exam day", "Exam time", "Number of units", "Level of education"};
        listOfFilters = new JComboBox(listOfChoices);
        listOfFilters.setBounds(550,25,150,25);
        panelOfTable.add(listOfFilters);
    }

    private void setComboBoxForTeachers () {
        String[] listOfChoices = {"Department name", "Teacher name", "Teacher position"};
        listOfFilters = new JComboBox(listOfChoices);
        listOfFilters.setBounds(550,25,150,25);
        panelOfTable.add(listOfFilters);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GeneralFormOfPag.backToMainPage) {
            listFrame.dispose();
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username,password);
            consideredFilter.setText("");
        } else if (e.getSource() == filterButton) {
            if (wantListOfLessons) {
                if (!consideredFilter.getText().equals("")) {
                    setTableOfLessonsInformation(consideredFilter.getText());
                }
            } else {
                if (!consideredFilter.getText().equals("")) {
                    setTableOfTeachersInformation(consideredFilter.getText());
                }
            }
        }
    }
}
