package graphic;

import Log.LogInformation;
import logic.RequestsController;
import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RequestsPages implements ActionListener {
    static String username;
    static String password;
    static JFrame requestsFrame;
    static JPanel panelOfRequests;
    static JTextField consideredTeacherName;
    static JTextField departmentName;
    static JButton requestButton;
    static JLabel message;
    static JTable receivedAnswersAndRequests;
    static JScrollPane scrollPane;
    static int typeOfRequest;
    static int typeOfUser;

    public RequestsPages(int userTypeOfRequest,int determineTypeOfUser,String userUsername,String userPassword) {
        username = userUsername;
        password = userPassword;
        typeOfUser = determineTypeOfUser;
        typeOfRequest = userTypeOfRequest;
        requestsFrame = new JFrame();
        panelOfRequests = new JPanel();
        consideredTeacherName = new JTextField();
        departmentName = new JTextField();
        requestButton = new JButton();
        message = new JLabel();
        requestsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        requestsFrame.setSize(750,750);
        requestsFrame.setLayout(new BorderLayout());
        requestsFrame.setLocationRelativeTo(null);
        setPanelsFeatures();
        setButtonFeatures();
        setLabelFeatures();
        determineTypeOfPage();
        requestsFrame.add(panelOfRequests,BorderLayout.CENTER);
        requestsFrame.setVisible(true);
    }

    private void determineTypeOfPage () {
        if (typeOfRequest == 1 & (typeOfUser == 1 | typeOfUser == 2 | typeOfUser == 3)) {
            setTextFieldAndLabelFeaturesForRecommendation();
            setTableForRecommendationAnswersForStudents();
        } else if (typeOfRequest == 1 & (typeOfUser == 4 | typeOfUser == 5 | typeOfUser == 6)) {
            requestButton.setVisible(false);
            setTableForRecommendationAnswersForTeachers();
        } else if (typeOfRequest == 2 & (typeOfUser == 1 | typeOfUser == 2 | typeOfUser == 3)) {
            setTableForCertificateStudentAnswer();
        } else if (typeOfRequest == 3 & (typeOfUser == 1 | typeOfUser == 2 | typeOfUser == 3)) {
            setAnswerLabelForMinorRequest();
            setTextFieldAndLabelFeaturesForMinorRequest();
        } else if (typeOfRequest == 3 & (typeOfUser == 5)) {
            requestButton.setVisible(false);
            setTableForMinorRequestsForTeachers();
        } else if (typeOfRequest == 4 & (typeOfUser == 1 | typeOfUser == 2 | typeOfUser == 3)) {
            setLabelsForWithdrawalFromEducationRequest();
        } else if (typeOfRequest == 4 & (typeOfUser == 5)) {
            requestButton.setVisible(false);
            setTableForWithdrawalFromEducationForTeachers();
        } else if (typeOfRequest == 5 & (typeOfUser == 1 | typeOfUser == 2 | typeOfUser == 3)) {
            setLabelsForDormRequest();
        } else if (typeOfRequest == 6 & (typeOfUser == 1 | typeOfUser == 2 | typeOfUser == 3)) {
            setLabelsForThesisDefenceRequest();
        } else if (typeOfRequest == 6 & typeOfUser == 5) {
            requestButton.setVisible(false);
            setTableForThesisDefenceForTeachers();
        }
    }

    private void setPanelsFeatures () {
       requestsFrame.add(GeneralFormOfPag.topOfPage,BorderLayout.NORTH);

       panelOfRequests.setPreferredSize(new Dimension(750,639));
       panelOfRequests.setLayout(null);
    }

    private void setTextFieldAndLabelFeaturesForRecommendation () {
        JLabel teacherName = new JLabel("Teacher name : ");
        teacherName.setBounds(50,50,200,25);
        panelOfRequests.add(teacherName);

        consideredTeacherName.setBounds(250,50,200,25);
        panelOfRequests.add(consideredTeacherName);
    }

    private void setTextFieldAndLabelFeaturesForMinorRequest () {
        JLabel destinationDepartmentName = new JLabel("Your considered department : ");
        destinationDepartmentName.setBounds(50,50,200,25);
        panelOfRequests.add(destinationDepartmentName);

        departmentName.setBounds(250,50,250,25);
        panelOfRequests.add(departmentName);
    }

    private void setLabelFeatures () {
        message.setBounds(200,500,500,100);
        panelOfRequests.add(message);
    }

    private void setButtonFeatures () {
        requestButton.setBounds(550,50,150,25);
        requestButton.setText("Send Request");
        requestButton.setBackground(Color.gray);
        requestButton.addActionListener(this);
        panelOfRequests.add(requestButton);

        GeneralFormOfPag.backToMainPage.setVisible(true);
        GeneralFormOfPag.backToMainPage.addActionListener(this);
    }

    private void setAnswerLabelForMinorRequest () {
        JLabel title = new JLabel("Condition of request : ");
        title.setBounds(50,200,200,50);
        panelOfRequests.add(title);

        JLabel conditionOfRequest = new JLabel();
        conditionOfRequest.setBounds(250,200,400,50);
        conditionOfRequest.setForeground(Color.blue);
        conditionOfRequest.setText(RequestsController.getAnswerOfMinorRequest(username));
        panelOfRequests.add(conditionOfRequest);
    }

    private void setLabelsForWithdrawalFromEducationRequest () {
        JLabel title = new JLabel("Condition of request : ");
        title.setBounds(50,200,200,50);
        panelOfRequests.add(title);

        JLabel conditionOfRequest = new JLabel();
        conditionOfRequest.setBounds(250,200,400,50);
        conditionOfRequest.setForeground(Color.blue);
        conditionOfRequest.setText(RequestsController.getAnswerOfWithdrawalFromEducationRequest(username));
        panelOfRequests.add(conditionOfRequest);
    }

    private void setLabelsForDormRequest () {
        JLabel title = new JLabel("Answer of request : ");
        title.setBounds(50,200,200,50);
        panelOfRequests.add(title);

        JLabel conditionOfRequest = new JLabel();
        conditionOfRequest.setBounds(250,200,400,50);
        conditionOfRequest.setForeground(Color.blue);
        conditionOfRequest.setText(RequestsController.getAnswerOfDormRequest(username));
        panelOfRequests.add(conditionOfRequest);
    }

    private void setLabelsForThesisDefenceRequest () {
        JLabel title = new JLabel("Answer of request : ");
        title.setBounds(50,200,200,50);
        panelOfRequests.add(title);

        JLabel conditionOfRequest = new JLabel();
        conditionOfRequest.setBounds(250,200,400,50);
        conditionOfRequest.setForeground(Color.blue);
        conditionOfRequest.setText(RequestsController.getAnswerOfThesisDefenceRequest(username));
        panelOfRequests.add(conditionOfRequest);
    }

    private void setTableForRecommendationAnswersForStudents () {
        String[][] data = RequestsController.getAnswersOfRecommendationRequests(username);
        String[] column = {"Answers of requests"};
        receivedAnswersAndRequests = new JTable(data,column);
        scrollPane = new JScrollPane(receivedAnswersAndRequests);
        scrollPane.setBounds(0,150,730,300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfRequests.add(scrollPane);
        panelOfRequests.repaint();
        panelOfRequests.revalidate();
        requestsFrame.repaint();
        requestsFrame.revalidate();
    }

    private void setTableForRecommendationAnswersForTeachers () {
        String[][] data = RequestsController.getListOfRecommendationsForATeacher(username);
        String[] column = {"Student name","Student number","Accept","Reject"};
        receivedAnswersAndRequests = new JTable(data,column);
        receivedAnswersAndRequests.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    String studentName = (String) receivedAnswersAndRequests.getModel().getValueAt(row,0);
                    if (column == 2) {
                        RequestsController.AcceptOrRejectRecommendationRequest(studentName, UserController.getUserCompleteName(),true);
                        message.setText("You accepted the request successfully");
                        LogInformation.createLogStatement("RequestsPages","setTableForRecommendationAnswersForTeachers",username + " accepted the request of " + studentName + " successfully","info");
                    } else if (column == 3) {
                        RequestsController.AcceptOrRejectRecommendationRequest(studentName, UserController.getUserCompleteName(),false);
                        message.setText("You rejected the request successfully");
                        LogInformation.createLogStatement("RequestsPages","setTableForRecommendationAnswersForTeachers",username + " rejected the request of " + studentName + " successfully","info");
                    }
                }
            }
        });
        scrollPane = new JScrollPane(receivedAnswersAndRequests);
        scrollPane.setBounds(0,150,730,300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfRequests.add(scrollPane);
        panelOfRequests.repaint();
        panelOfRequests.revalidate();
        requestsFrame.repaint();
        requestsFrame.revalidate();
    }

    private void setTableForMinorRequestsForTeachers () {
        String[][] data = RequestsController.getListOfMinorRequestsForATeacher(username);
        String[] column = {"Student name","Student number","Origin department","Destination department","Accept","Reject"};
        receivedAnswersAndRequests = new JTable(data,column);
        receivedAnswersAndRequests.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    String studentName = (String) receivedAnswersAndRequests.getModel().getValueAt(row,0);
                    if (column == 4) {
                        RequestsController.AcceptOrRejectMinorRequest(studentName,username,true);
                        message.setText("You accepted the request successfully");
                        LogInformation.createLogStatement("RequestsPages","setTableForMinorRequestsForTeachers",username + " accepted the request of " + studentName + " successfully","info");
                    } else if (column == 5) {
                        RequestsController.AcceptOrRejectMinorRequest(studentName,username,false);
                        message.setText("You rejected the request successfully");
                        LogInformation.createLogStatement("RequestsPages","setTableForMinorRequestsForTeachers",username + " rejected the request of " + studentName + " successfully","info");
                    }
                }
            }
        });
        scrollPane = new JScrollPane(receivedAnswersAndRequests);
        scrollPane.setBounds(0,150,730,300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfRequests.add(scrollPane);
        panelOfRequests.repaint();
        panelOfRequests.revalidate();
        requestsFrame.repaint();
        requestsFrame.revalidate();
    }

    private void setTableForWithdrawalFromEducationForTeachers () {
        String[][] data = RequestsController.getListOfWithdrawalFromEducationForATeacher(username);
        String[] column = {"Student name","Student number","Accept","Reject"};
        receivedAnswersAndRequests = new JTable(data,column);
        receivedAnswersAndRequests.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    String studentName = (String) receivedAnswersAndRequests.getModel().getValueAt(row,0);
                    if (column == 2) {
                        RequestsController.acceptOrRejectWithdrawalFromEducationRequest(studentName,username,true);
                        message.setText("You accepted the request successfully");
                        LogInformation.createLogStatement("RequestsPages","setTableForWithdrawalFromEducationForTeachers",username + " accepted the request of " + studentName + " successfully","info");
                    } else if (column == 3) {
                        RequestsController.acceptOrRejectWithdrawalFromEducationRequest(studentName,username,false);
                        message.setText("You rejected the request successfully");
                        LogInformation.createLogStatement("RequestsPages","setTableForWithdrawalFromEducationForTeachers",username + " rejected the request of " + studentName + " successfully","info");
                    }
                }
            }
        });
        scrollPane = new JScrollPane(receivedAnswersAndRequests);
        scrollPane.setBounds(0,150,730,300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfRequests.add(scrollPane);
        panelOfRequests.repaint();
        panelOfRequests.revalidate();
        requestsFrame.repaint();
        requestsFrame.revalidate();
    }

    private void setTableForThesisDefenceForTeachers () {
        String[][] data = RequestsController.getListOfThesisDefenceForATeacher(username);
        String[] column = {"Student name","Student number","date","Register date"};
        receivedAnswersAndRequests = new JTable(data,column);
        receivedAnswersAndRequests.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    String studentName = (String) receivedAnswersAndRequests.getModel().getValueAt(row,0);
                    if (column == 3) {
                        String date = (String) receivedAnswersAndRequests.getModel().getValueAt(row,2);
                        if (date != null && !date.equals("")) {
                            if ((!(date.length() == 10)) | (date.charAt(4) != '/') | (date.charAt(7) != '/')) {
                                message.setText("Format of date is incorrect");
                                LogInformation.createLogStatement("RequestsPages","setTableForThesisDefenceForTeachers",username + " entry the wrong format of date","info");
                            } else {
                                RequestsController.giveADateForThesisDefence(username,studentName,date);
                                message.setText("You register date successfully");
                                LogInformation.createLogStatement("RequestsPages","setTableForThesisDefenceForTeachers",username + " registered a date","info");
                            }
                        }
                    }
                }
            }
        });
        scrollPane = new JScrollPane(receivedAnswersAndRequests);
        scrollPane.setBounds(0,150,730,300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfRequests.add(scrollPane);
        panelOfRequests.repaint();
        panelOfRequests.revalidate();
        requestsFrame.repaint();
        requestsFrame.revalidate();
    }

    private void setTableForCertificateStudentAnswer () {
        String[][] data = RequestsController.getAnswerOfCertificateStudentRequest(username);
        String[] column = {"Answers of request"};
        receivedAnswersAndRequests = new JTable(data,column);
        scrollPane = new JScrollPane(receivedAnswersAndRequests);
        scrollPane.setBounds(0,150,730,100);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelOfRequests.add(scrollPane);
        panelOfRequests.repaint();
        panelOfRequests.revalidate();
        requestsFrame.repaint();
        requestsFrame.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GeneralFormOfPag.backToMainPage) {
            requestsFrame.dispose();
            consideredTeacherName.setText("");
            LogInformation.createLogStatement("EditTeacherPage","RequestsPages","Back to main page","info");
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
        } else if (e.getSource() == requestButton) {
            if (typeOfRequest == 1) {
                int result = RequestsController.addRecommendationRequest(username,consideredTeacherName.getText());
                if (result == 1) {
                    message.setText("You have requested successfully");
                    LogInformation.createLogStatement("RequestsPages","clickOnRecommendationRequest",username + " have requested successfully","info");
                } else if (result == 2) {
                    message.setText("You can't request,because the teacher is not exist");
                    LogInformation.createLogStatement("RequestsPages","clickOnRecommendationRequest",username + " can't request,because the teacher is not exist","info");
                } else {
                    message.setText("You have already requested");
                    LogInformation.createLogStatement("RequestsPages","clickOnRecommendationRequest",username + " have already requested","info");
                }
            } else if (typeOfRequest == 2) {
                if (RequestsController.addCertificateRequest(username)) {
                    setTableForCertificateStudentAnswer();
                    message.setText("You have requested successfully");
                    LogInformation.createLogStatement("RequestsPages","clickOnCertificateStudentRequest",username + " have requested successfully","info");
                } else {
                    message.setText("You have already requested");
                    LogInformation.createLogStatement("RequestsPages","clickOnCertificateStudentRequest",username + " have already requested","info");
                }
            } else if (typeOfRequest == 3) {
                if (RequestsController.addMinorRequest(username,departmentName.getText())) {
                    setTextFieldAndLabelFeaturesForMinorRequest();
                    message.setText("You have requested successfully");
                    LogInformation.createLogStatement("RequestsPages","clickOnMinorRequest",username + " have requested successfully","info");
                } else {
                    message.setText("You have already requested");
                    LogInformation.createLogStatement("RequestsPages","clickOnMinorRequest",username + " have already requested","info");
                }
            } else if (typeOfRequest == 4) {
                if (RequestsController.addWithdrawalFromEducationRequest(username)) {
                    setLabelsForWithdrawalFromEducationRequest();
                    message.setText("You have requested successfully");
                    LogInformation.createLogStatement("RequestsPages","clickOnWithdrawalFromEducationRequest",username + " have requested successfully","info");
                } else {
                    message.setText("You have already requested");
                    LogInformation.createLogStatement("RequestsPages","clickOnWithdrawalFromEducationRequest",username + " have already requested","info");
                }
            } else if (typeOfRequest == 5) {
                if (RequestsController.addDormRequest(username)) {
                    setLabelsForDormRequest();
                    message.setText("You have requested successfully");
                    LogInformation.createLogStatement("RequestsPages","clickOnDormRequest",username + " have requested successfully","info");
                } else {
                    message.setText("You have already requested");
                    LogInformation.createLogStatement("RequestsPages","clickOnDormRequest",username + " have already requested","info");
                }
            } else if (typeOfRequest == 6) {
                if (RequestsController.addThesisDefenceRequest(username)) {
                    setLabelsForThesisDefenceRequest();
                    message.setText("You have requested successfully");
                    LogInformation.createLogStatement("RequestsPages","clickOnThesisDefenceRequest",username + " have requested successfully","info");
                } else {
                    message.setText("You have already requested");
                    LogInformation.createLogStatement("RequestsPages","clickOnThesisDefenceRequest",username + " have already requested","info");
                }
            }
        }
    }
}
