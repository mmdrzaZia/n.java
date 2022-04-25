package graphic;

import Log.LogInformation;
import logic.LessonController;
import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeeLessonsAndTeachers implements ActionListener {
    static String username;
    static String password;
    static JFrame frame;
    static JTable information;
    static JPanel topOfPageInformation;
    static JPanel panelOfTable;
    static JScrollPane scrollPane;
    static JTextField consideredFilter;
    static JButton filterButton;
    static JButton addATeacher;
    static JButton addALesson;
    static JComboBox listOfFilters;
    static JLabel message;
    boolean wantListOfLessons;
    static int typeOfUser;


    public SeeLessonsAndTeachers(String userUsername, String userPassword, boolean wantListOfLessons, int determineTypeOfUser) {
        username = userUsername;
        password = userPassword;
        typeOfUser = determineTypeOfUser;
        topOfPageInformation = new JPanel();
        panelOfTable = new JPanel();
        consideredFilter = new JTextField();
        filterButton = new JButton("Filter");
        addATeacher = new JButton("Add new teacher");
        addALesson = new JButton("Add new Lesson");
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,750);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        this.wantListOfLessons = wantListOfLessons;
        panelOfTable.removeAll();
        setButtonsFeatures();
        setFilterTextBox();
        message = new JLabel();
        message.setBounds(100,500,600,50);
        panelOfTable.add(message);
        setPanelsFeatures();
        if (wantListOfLessons & typeOfUser != 5) {
            setComboBoxForLessons();
            setTableOfLessonsInformation(null);
        } else if (wantListOfLessons & typeOfUser == 5) {
            setComboBoxForLessons();
            addALesson.setBounds(20,25,135,25);
            addALesson.setBackground(Color.GRAY);
            addALesson.addActionListener(this);
            panelOfTable.add(addALesson);
            setTableOfLessonsForEducationalAssistant(null);
        } else if (typeOfUser != 6) {
            setComboBoxForTeachers();
            setTableOfTeachersInformation(null);
        }  else {
            setComboBoxForTeachers();
            addATeacher.setBounds(20,25,135,25);
            addATeacher.setBackground(Color.GRAY);
            addATeacher.addActionListener(this);
            panelOfTable.add(addATeacher);
            setTableOfTeachersForBoss(null);
        }
        panelOfTable.repaint();
        panelOfTable.revalidate();
        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();
    }

    private void setPanelsFeatures () {
        topOfPageInformation.setPreferredSize(new Dimension(750,127));
        topOfPageInformation.setLayout(new BorderLayout());
        topOfPageInformation.add(GeneralFormOfPag.topOfPage,BorderLayout.NORTH);
        frame.add(topOfPageInformation,BorderLayout.NORTH);

        panelOfTable.setPreferredSize(new Dimension(750,623));
        panelOfTable.setLayout(null);
    }

    private void setTableOfLessonsInformation (String filter) {
        String[][] data = null;
        if (filter == null) {
            data = LessonController.seeLessonsByUser();
        } else {
            data = LessonController.seeLessonsByUser(filter);
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
        frame.add(panelOfTable,BorderLayout.CENTER);
        frame.repaint();
        frame.revalidate();
    }

    private void setTableOfTeachersInformation (String filter) {
        String[][] data = null;
        if (filter == null) {
            data = UserController.seeListOfTeachersByUser();
        } else {
            data = UserController.seeListOfTeachersByUser(filter);
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
        frame.add(panelOfTable,BorderLayout.CENTER);
        frame.repaint();
        frame.revalidate();
    }

    private void setTableOfTeachersForBoss (String filter) {
        String[][] data = null;
        String[][] informationOfTeachers = null;
        if (filter == null) {
            informationOfTeachers = UserController.seeListOfTeachersByUser();
        } else {
            informationOfTeachers = UserController.seeListOfTeachersByUser(filter);
            panelOfTable.remove(scrollPane);
        }
        data = new String[informationOfTeachers.length][8];
        for (int i = 0; i < informationOfTeachers.length; i++) {
            for (int j = 0; j < 6; j++) {
                data[i][j] = informationOfTeachers[i][j];
            }
            data[i][6] = "Edit";
            data[i][7] = "Remove";
        }
        String[] column = {"Name","Email","Department name","Room number","Position","Lessons","Edit","Remove"};
        information = new JTable(data,column);
        information.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    String teacherName = (String) information.getModel().getValueAt(row,0);
                    if (column == 6) {
                        if (UserController.canEditATeacher(username,teacherName)) {
                            frame.dispose();
                            EditTeachersPage editTeachersPage = new EditTeachersPage();
                            editTeachersPage.setPageOfEditTeachers(username,password,teacherName);
                        } else {
                            message.setText("You can't edit this user");
                            LogInformation.createLogStatement("SeeLessonsAndTeachers","setTableOfTeachersForBoss",username + " can't edit this user","error");
                        }
                    } else if (column == 7) {
                        if (UserController.canEditATeacher(username,teacherName)) {
                            UserController.removeATeacher(teacherName);
                            message.setText("The teacher removed successfully");
                            LogInformation.createLogStatement("SeeLessonsAndTeachers","setTableOfTeachersForBoss",teacherName + " removed successfully","info");
                        } else {
                            message.setText("You can't remove this user");
                            LogInformation.createLogStatement("SeeLessonsAndTeachers","setTableOfTeachersForBoss",username + " can't edit this user","error");
                        }
                    }
                }
            }
        });
        scrollPane = new JScrollPane(information);
        scrollPane.setBounds(0,100,730,300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfTable.add(scrollPane);
        panelOfTable.repaint();
        panelOfTable.revalidate();
        frame.add(panelOfTable,BorderLayout.CENTER);
        frame.repaint();
        frame.revalidate();
    }

    private void setTableOfLessonsForEducationalAssistant (String filter) {
        String[][] data = null;
        String[][] informationOfLessons = null;
        if (filter == null) {
            informationOfLessons = LessonController.seeLessonsByUser();
        } else {
            informationOfLessons = LessonController.seeLessonsByUser(filter);
            panelOfTable.remove(scrollPane);
        }
        data = new String[informationOfLessons.length][10];
        for (int i = 0; i < informationOfLessons.length; i++) {
            for (int j = 0; j < 6; j++) {
                data[i][j] = informationOfLessons[i][j];
            }
            data[i][8] = "Edit";
            data[i][9] = "Remove";
        }
        String[] column = {"Name","Lesson number","Number of units","Level of education","Teacher name","Department name","Class time","Exam time","Edit","Remove"};
        information = new JTable(data,column);
        information.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    String lessonName = (String) information.getModel().getValueAt(row,0);
                    if (column == 8) {
                        if (LessonController.canEditOrRemoveALesson(username,lessonName)) {
                            frame.dispose();
                            EditLessonsPage editLessonsPage = new EditLessonsPage();
                            editLessonsPage.setPageOfEditLessons(username,password, lessonName);
                        } else {
                            message.setText("You can't edit this lesson");
                            LogInformation.createLogStatement("SeeLessonsAndTeachers","setTableOfLessonsForEducationalAssistant",username + " can't edit this lesson","error");
                        }
                    } else if (column == 9) {
                        if (LessonController.canEditOrRemoveALesson(username,lessonName)) {
                            LessonController.removeALesson(lessonName);
                            message.setText("The lesson removed successfully");
                            LogInformation.createLogStatement("SeeLessonsAndTeachers","setTableOfLessonsForEducationalAssistant",lessonName + " removed successfully","info");
                        } else {
                            message.setText("You can't remove this lesson");
                            LogInformation.createLogStatement("SeeLessonsAndTeachers","setTableOfLessonsForEducationalAssistant",username + " can't edit this lesson","error");
                        }
                    }
                }
            }
        });
        scrollPane = new JScrollPane(information);
        scrollPane.setBounds(0,100,730,300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfTable.add(scrollPane);
        panelOfTable.repaint();
        panelOfTable.revalidate();
        frame.add(panelOfTable,BorderLayout.CENTER);
        frame.repaint();
        frame.revalidate();
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
            frame.dispose();
            LogInformation.createLogStatement("SeeLessonsAndTeachers","clickOnBackButton","Back to main page","info");
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username,password);
            consideredFilter.setText("");
        } else if (e.getSource() == filterButton) {
            if (wantListOfLessons) {
                if (!consideredFilter.getText().equals("")) {
                    if (typeOfUser != 5) {
                        setTableOfLessonsInformation(consideredFilter.getText());
                    } else {
                        setTableOfLessonsForEducationalAssistant(consideredFilter.getText());
                    }
                }
            } else {
                if (!consideredFilter.getText().equals("")) {
                    if (typeOfUser != 6) {
                        setTableOfTeachersInformation(consideredFilter.getText());
                    } else {
                        setTableOfTeachersForBoss(consideredFilter.getText());
                    }
                }
            }
        } else if (e.getSource() == addATeacher) {
            frame.dispose();
            EditTeachersPage editTeachersPage = new EditTeachersPage();
            editTeachersPage.setPageForAddATeacher(username,password);
        } else if (e.getSource() == addALesson) {
            frame.dispose();
            EditLessonsPage editLessonsPage = new EditLessonsPage();
            editLessonsPage.setPageForAddALesson(username);
        }
    }
}
