package graphic;

import Log.LogInformation;
import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangingEmailAndPhoneNumber implements ActionListener {
    static String username;
    static String password;
    static JFrame frame;
    static JPanel topOfThePageInformation;
    static JPanel panelOfChanging;
    static JLabel emailAddress;
    static JLabel phoneNumber;
    static JTextField newEmailAddress;
    static JTextField newPhoneNumber;
    static JButton registerChanges;
    static JLabel message;


    public ChangingEmailAndPhoneNumber(String userUsername, String userPassword) {
        username = userUsername;
        password = userPassword;
        topOfThePageInformation = new JPanel();
        panelOfChanging = new JPanel();
        emailAddress = new JLabel("Email address");
        phoneNumber = new JLabel("Phone number");
        newEmailAddress = new JTextField(UserController.getUserEmail());
        newPhoneNumber = new JTextField(UserController.getUserPhoneNumber());
        registerChanges = new JButton("Register changes");
        message = new JLabel();
        message.setBounds(100,500,600,50);
        panelOfChanging.add(message);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,750);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        setButtonFeatures();
        setLabelsAndTextFields();
        setPanelsFeatures();
        frame.add(panelOfChanging,BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void setLabelsAndTextFields () {
        emailAddress.setBounds(100,200,100,25);
        panelOfChanging.add(emailAddress);

        newEmailAddress.setBounds(225,200,150,25);
        panelOfChanging.add(newEmailAddress);

        phoneNumber.setBounds(100,275,100,25);
        panelOfChanging.add(phoneNumber);

        newPhoneNumber.setBounds(225,275,150,25);
        panelOfChanging.add(newPhoneNumber);
    }

    private void setButtonFeatures () {
        GeneralFormOfPag.backToMainPage.setVisible(true);
        GeneralFormOfPag.backToMainPage.addActionListener(this);

        registerChanges.setBounds(300,400,150,25);
        registerChanges.setBackground(Color.GRAY);
        registerChanges.addActionListener(this);
        panelOfChanging.add(registerChanges);
    }

    private void setPanelsFeatures () {
        frame.add(GeneralFormOfPag.topOfPage,BorderLayout.NORTH);

        panelOfChanging.setPreferredSize(new Dimension(750,639));
        panelOfChanging.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GeneralFormOfPag.backToMainPage) {
            frame.dispose();
            LogInformation.createLogStatement("ChangingEmailAndPhoneNumber","clickOnBackButton","Back to main page","info");
            GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
        } else if (e.getSource() == registerChanges) {
            UserController.changeEmailAndPhoneNumber(username,newEmailAddress.getText(),newPhoneNumber.getText());
            message.setText("You changed information successfully!");
        }
    }
}
