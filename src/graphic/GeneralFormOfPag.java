package graphic;

import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class GeneralFormOfPag extends JFrame implements ActionListener {
    static String username;
    static String password;
    static GeneralFormOfPag mainFrame;
    static JPanel topOfPage = new JPanel();
    static JPanel informationPanel = new JPanel();
    static JPanel centerInformationPanel = new JPanel();
    static JButton currentTime = new JButton();
    static JButton exitButton = new JButton("exit");
    static JButton backToMainPage = new JButton("Back");
    static JLabel currentTimeText = new JLabel("current time : ");
    static JLabel lastEntryTime = new JLabel();
    static JLabel profilePicture = new JLabel();
    static JLabel completeName = new JLabel();
    static JLabel email = new JLabel();
    static JMenuBar menuBar = new JMenuBar();


    public static GeneralFormOfPag getInstance(String userUsername, String userPassword){
        if (mainFrame == null) {
            mainFrame = new GeneralFormOfPag(userUsername,userPassword);
        }
        return mainFrame;
    }

    public GeneralFormOfPag(String userUsername, String userPassword) {
        username = userUsername;
        password = userPassword;
        setPanelsFeatures();
        setLabelsFeatures();
        setButtonsFeatures();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750,750);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        add(topOfPage,BorderLayout.NORTH);
        UserMainPage.fillMainPage();
        informationPanel.add(menuBar,BorderLayout.NORTH);
        add(informationPanel,BorderLayout.CENTER);
        setVisible(true);
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
        lastEntryTime.setText("last entry time : " + getLastTime());
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

    private static String getLastTime () {
        Date date = new Date();
        String str = String.format("%tc", date );
        return str;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
