package graphic;

import logic.RequestsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequestsPages implements ActionListener {
    static String username;
    static String password;
    static JFrame requestsFrame;
    static JPanel panelOfRequests;
    static JTextField consideredTeacherName;
    JTextField departmentName;
    static JButton requestButton;
    static JLabel message;
    static JTable receivedAnswers;
    static JScrollPane scrollPane;
    static boolean isStudent;
    static int typeOfRequest;

    public RequestsPages(int userTypeOfRequest,boolean userIsStudent,String userUsername,String userPassword) {
        username = userUsername;
        password = userPassword;
        isStudent = userIsStudent;
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
        if (typeOfRequest == 1 & isStudent) {
            setTextFieldAndLabelFeaturesForRecommendation();
            setTableForRecommendationAnswers();
        } else if (typeOfRequest == 1 & !isStudent) {
            //TODO
        } else if (typeOfRequest == 2 & isStudent) {
            setTableForCertificateStudentAnswer();
        } else if (typeOfRequest == 3 & isStudent) {
            setAnswerLabelForMinorRequest();
            setTextFieldAndLabelFeaturesForMinorRequest();
        } else if (typeOfRequest == 3 & !isStudent) {
            //TODO
        } else if (typeOfRequest == 4 & isStudent) {
            setLabelsForWithdrawalFromEducationRequest();
        } else if (typeOfRequest == 4 & !isStudent) {
            //TODO
        } else if (typeOfRequest == 5 & isStudent) {
            setLabelsForDormRequest();
        } else if (typeOfRequest == 6 & isStudent) {
            setLabelsForThesisDefenceRequest();
        } else if (typeOfRequest == 7 & isStudent) {

        } else if (typeOfRequest == 7 & !isStudent) {

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

    private void setTableForRecommendationAnswers () {
        String[][] data = RequestsController.getAnswersOfRecommendationRequests(username);
        String[] column = {"Answers of requests"};
        receivedAnswers = new JTable(data,column);
        scrollPane = new JScrollPane(receivedAnswers);
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
        receivedAnswers = new JTable(data,column);
        scrollPane = new JScrollPane(receivedAnswers);
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
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
        } else if (e.getSource() == requestButton) {
            if (typeOfRequest == 1) {
                int result = RequestsController.addRecommendationRequest(username,consideredTeacherName.getText());
                if (result == 1) {
                    message.setText("You have requested successfully");
                } else if (result == 2) {
                    message.setText("You can't request,because the teacher is not exist");
                } else {
                    message.setText("You have already requested");
                }
            } else if (typeOfRequest == 2) {
                if (RequestsController.addCertificateRequest(username)) {
                    setTableForCertificateStudentAnswer();
                    message.setText("You have requested successfully");
                } else {
                    message.setText("You have already requested");
                }
            } else if (typeOfRequest == 3) {
                if (RequestsController.addMinorRequest(username,departmentName.getText())) {
                    setTextFieldAndLabelFeaturesForMinorRequest();
                    message.setText("You have requested successfully");
                } else {
                    message.setText("You have already requested");
                }
            } else if (typeOfRequest == 4) {
                if (RequestsController.addWithdrawalFromEducationRequest(username)) {
                    setLabelsForWithdrawalFromEducationRequest();
                    message.setText("You have requested successfully");
                } else {
                    message.setText("You have already requested");
                }
            } else if (typeOfRequest == 5) {
                if (RequestsController.addDormRequest(username)) {
                    setLabelsForDormRequest();
                    message.setText("You have requested successfully");
                } else {
                    message.setText("You have already requested");
                }
            } else if (typeOfRequest == 6) {
                if (RequestsController.addThesisDefenceRequest(username)) {
                    setLabelsForThesisDefenceRequest();
                    message.setText("You have requested successfully");
                } else {
                    message.setText("You have already requested");
                }
            }
        }
    }
}
