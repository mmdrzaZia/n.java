package graphic;

import Log.LogInformation;
import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralFormOfPag implements ActionListener {
    static String username;
    static String password;
    static String lastEntryTimeStr;
    static JFrame mainFrame;
    static JPanel topOfPage;
    static JPanel informationPanel;
    static JPanel centerInformationPanel;
    static JButton currentTime;
    static JButton exitButton;
    static JButton backToMainPage;
    static JLabel currentTimeText;
    static JLabel lastEntryTime;
    static JLabel profilePicture;
    static JLabel completeName;
    static JLabel email;
    static JMenuBar menuBar;

    public GeneralFormOfPag(String userUsername, String userPassword) {
        username = userUsername;
        password = userPassword;
        topOfPage = new JPanel();
        informationPanel = new JPanel();
        centerInformationPanel = new JPanel();
        currentTime = new JButton();
        exitButton = new JButton("exit");
        backToMainPage = new JButton("Back");
        currentTimeText = new JLabel("current time : ");
        lastEntryTime = new JLabel();
        profilePicture = new JLabel();
        completeName = new JLabel();
        menuBar = new JMenuBar();
        email = new JLabel();
        mainFrame = new JFrame();
        setPanelsFeatures();
        setLabelsFeatures();
        setButtonsFeatures();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(750,750);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(topOfPage,BorderLayout.NORTH);
        UserMainPage.fillMainPage();
        informationPanel.add(menuBar,BorderLayout.NORTH);
        mainFrame.add(informationPanel,BorderLayout.CENTER);
        mainFrame.setVisible(true);
        UserController.saveLastEntryTime(username,lastEntryTimeStr);
    }

    private void setPanelsFeatures () {
        topOfPage.setPreferredSize(new Dimension(750,111));
        topOfPage.setBackground(Color.BLUE);
        topOfPage.setLayout(null);

        centerInformationPanel.setLayout(null);

        informationPanel.setPreferredSize(new Dimension(750,500));
        informationPanel.setLayout(new BorderLayout());
    }

    private void setButtonsFeatures () {
        currentTime.setBounds(210,0,300,50);
        ShowCurrentTime showCurrentTime = new ShowCurrentTime(currentTime);
        currentTime.setBackground(Color.BLUE);
        currentTime.setForeground(Color.LIGHT_GRAY);
        topOfPage.add(currentTime);

        exitButton.setBounds(600,25,100,25);
        exitButton.setBackground(Color.gray);
        exitButton.addActionListener(this);
        topOfPage.add(exitButton);

        backToMainPage.setBounds(600,55,100,25);
        backToMainPage.setBackground(Color.gray);
        backToMainPage.setVisible(false);
        topOfPage.add(backToMainPage);
    }

    private void setLabelsFeatures () {
        currentTimeText.setBounds(125,0,200,50);
        currentTimeText.setBackground(Color.BLUE);
        currentTimeText.setForeground(Color.LIGHT_GRAY);
        topOfPage.add(currentTimeText);

        lastEntryTime.setBounds(125,50,500,50);
        lastEntryTime.setText("last entry time : " + UserController.getLastTimeEntry(username));
        lastEntryTimeStr = UserController.getTimeOfNow();
        lastEntryTime.setBackground(Color.BLUE);
        lastEntryTime.setForeground(Color.LIGHT_GRAY);
        topOfPage.add(lastEntryTime);

        profilePicture.setBounds(0,0,100,111);
        ImageIcon picture = new ImageIcon("src/kapchasPictures/profilePicture.jpg");
        profilePicture.setIcon(picture);
        topOfPage.add(profilePicture);

        completeName.setBounds(10,10,300,10);
        completeName.setText("complete name : " + UserController.getUserCompleteName());
        centerInformationPanel.add(completeName);

        email.setBounds(10,20,300,50);
        email.setText("email address : " + UserController.getUserEmail());
        centerInformationPanel.add(email);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            LogInformation.createLogStatement("GeneralFormOfPage","clickOnExiteButton","the application finished","info");
            System.exit(0);
        }
    }
}
