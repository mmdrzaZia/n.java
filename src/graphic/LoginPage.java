package graphic;

import Log.LogInformation;
import logic.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LoginPage implements ActionListener {
    static String username;
    static String password;
    static int securityCode;
    static int addressNumberOfKapcha;
    static JFrame mainFrame;
    static JLabel usernameLabel;
    static JLabel passwordLabel;
    static JLabel securityPasswordLabel;
    static JLabel kapchaPictureLabel;
    static JLabel errors;
    static JTextField usernameText;
    static JPasswordField passwordText;
    static JTextField securityPasswordText;
    static ImageIcon kapcha;
    static JButton changeKapcha;
    static JButton hideOrShowPassword;
    static JButton login;
    boolean isChangedPassword;

    public LoginPage(boolean isChanged) {
        isChangedPassword = isChanged;
        usernameLabel = new JLabel("Username :");
        passwordLabel = new JLabel("Password :");
        securityPasswordLabel = new JLabel("security code : ");
        kapchaPictureLabel = new JLabel();
        usernameText = new JTextField();
        errors = new JLabel();
        passwordText = new JPasswordField();
        securityPasswordText = new JTextField();
        changeKapcha = new JButton();
        hideOrShowPassword = new JButton();
        login = new JButton("Login");
        mainFrame = new JFrame();
        setLabelsFeatures();
        setButtonsFeatures();
        setTextFieldsFeatures();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(750,750);
        mainFrame.setLayout(null);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void setLabelsFeatures () {
        usernameLabel.setBounds(100,100,100,100);
        mainFrame.add(usernameLabel);

        passwordLabel.setBounds(100,200,100,100);
        mainFrame.add(passwordLabel);

        securityPasswordLabel.setBounds(80,300,100,100);
        mainFrame.add(securityPasswordLabel);

        kapchaPictureLabel.setBounds(200,350,200,200);
        addressNumberOfKapcha = generateRandomNumber();
        ImageIcon kapchaPicture = new ImageIcon(kapchaHandler.findAddressOfKapchaPictures(addressNumberOfKapcha));
        kapchaPictureLabel.setIcon(kapchaPicture);
        mainFrame.add(kapchaPictureLabel);

        errors.setBounds(300,550,350,50);
        mainFrame.add(errors);
    }

    private void setButtonsFeatures () {
        changeKapcha.setBounds(170,450,20,20);
        changeKapcha.setIcon(new ImageIcon("src/kapchasPictures/changeKapcha.jpg"));
        changeKapcha.addActionListener(this);
        mainFrame.add(changeKapcha);

        hideOrShowPassword.setBounds(475,250,20,20);
        hideOrShowPassword.addActionListener(this);
        mainFrame.add(hideOrShowPassword);

        login.setBounds(500,425,100,50);
        login.addActionListener(this);
        mainFrame.add(login);
    }

    private void setTextFieldsFeatures () {
        usernameText.setBounds(250,130,200,50);
        mainFrame.add(usernameText);

        passwordText.setBounds(250,230,200,50);
        mainFrame.add(passwordText);

        securityPasswordText.setBounds(250,330,200,50);
        mainFrame.add(securityPasswordText);
    }

    private static int generateRandomNumber () {
        Random random = new Random();
        return random.nextInt(5);
    }

    private static void changingKapchaCode () {
        int newKapchaNumber = addressNumberOfKapcha;
        while (newKapchaNumber == addressNumberOfKapcha) {
            newKapchaNumber = generateRandomNumber();
        }
        ImageIcon newKapchaPicture = new ImageIcon(kapchaHandler.findAddressOfKapchaPictures(newKapchaNumber));
        kapchaPictureLabel.removeAll();
        kapchaPictureLabel.setIcon(newKapchaPicture);
        addressNumberOfKapcha = newKapchaNumber;
        kapchaPictureLabel.repaint();
        kapchaPictureLabel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeKapcha) {
            LogInformation.createLogStatement("LoginPage","clickOnChangeKapcha","kapcha changed","info");
            changingKapchaCode();
        } else if (e.getSource() == hideOrShowPassword) {
            if (hideOrShowPassword.getIcon() != null) {
                hideOrShowPassword.setIcon(null);
            } else {
                ImageIcon ticIcon = new ImageIcon("src/kapchasPictures/tic.jpg");
                hideOrShowPassword.setIcon(ticIcon);
            }
            hideOrShowPassword.repaint();
            hideOrShowPassword.revalidate();
            if ( passwordText.getEchoChar() != '\u0000' ) {
                passwordText.setEchoChar('\u0000');
            } else {
                passwordText.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
            }
        } else if (e.getSource() == login) {
            username = usernameText.getText();
            password = passwordText.getText();
            if (!UserController.firstEntry(username) && !isChangedPassword && UserController.shouldChangePassword(username)) {
                mainFrame.dispose();
                ChangingPasswordPage changingPasswordPage = new ChangingPasswordPage(username,password);
            } else {
                securityCode = Integer.parseInt(securityPasswordText.getText());
                if (securityCode == kapchaHandler.correctEntry(addressNumberOfKapcha)) {
                    if (UserController.login(username, password)) {
                        mainFrame.dispose();
                        GeneralFormOfPag generalFormOfPag = new GeneralFormOfPag(username, password);
                    } else {
                        errors.setText("username or password is incorrect,try again!");
                        changingKapchaCode();
                        LogInformation.createLogStatement("LoginPage","clickOnLogin","username or password is incorrect","error");
                    }
                } else {
                    errors.setText("security code is incorrect,try again!");
                    changingKapchaCode();
                    LogInformation.createLogStatement("LoginPage","clickOnLogin","security code is incorrect","error");
                }
            }
        }
    }
}
