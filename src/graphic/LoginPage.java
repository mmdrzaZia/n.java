package graphic;

import logic.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LoginPage extends JFrame implements ActionListener {
    static String username;
    static String password;
    static int securityCode;
    static int addressNumberOfKapcha;
    private static LoginPage mainFrame;
    static JLabel usernameLabel = new JLabel("Username :");
    static JLabel passwordLabel = new JLabel("Password :");
    static JLabel securityPasswordLabel = new JLabel("security code : ");
    static JLabel kapchaPictureLabel = new JLabel();
    static JLabel errors = new JLabel();
    static JTextField usernameText = new JTextField();
    static JPasswordField passwordText = new JPasswordField();
    static JTextField securityPasswordText = new JTextField();
    static ImageIcon kapcha;
    static JButton changeKapcha = new JButton();
    static JButton hideOrShowPassword = new JButton();
    static JButton login = new JButton("Login");

    public static LoginPage getInstance(){
        if (mainFrame == null) {
            mainFrame = new LoginPage();
        }
        return mainFrame;
    }

    public LoginPage() {
        setLabelsFeatures();
        setButtonsFeatures();
        setTextFieldsFeatures();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750,750);
        setLayout(null);
        setLocationRelativeTo(null);
        add(usernameLabel);
        add(passwordLabel);
        add(securityPasswordLabel);
        add(kapchaPictureLabel);
        add(errors);
        add(changeKapcha);
        add(hideOrShowPassword);
        add(login);
        add(usernameText);
        add(passwordText);
        add(securityPasswordText);
        setVisible(true);
    }

    private void setLabelsFeatures () {
        usernameLabel.setBounds(100,100,100,100);
        passwordLabel.setBounds(100,200,100,100);
        securityPasswordLabel.setBounds(80,300,100,100);
        kapchaPictureLabel.setBounds(200,350,200,200);
        addressNumberOfKapcha = generateRandomNumber();
        ImageIcon kapchaPicture = new ImageIcon(kapchaHandler.findAddressOfKapchaPictures(addressNumberOfKapcha));
        kapchaPictureLabel.setIcon(kapchaPicture);
        errors.setBounds(300,550,250,50);
    }

    private void setButtonsFeatures () {
        changeKapcha.setBounds(170,450,20,20);
        changeKapcha.setIcon(new ImageIcon("src/kapchasPictures/changeKapcha.jpg"));
        changeKapcha.addActionListener(this);

        hideOrShowPassword.setBounds(475,250,20,20);
        hideOrShowPassword.addActionListener(this);

        login.setBounds(500,425,100,50);
        login.addActionListener(this);
    }

    private void setTextFieldsFeatures () {
        usernameText.setBounds(250,130,200,50);
        passwordText.setBounds(250,230,200,50);
        securityPasswordText.setBounds(250,330,200,50);
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
            securityCode = Integer.parseInt(securityPasswordText.getText());
            if (securityCode == kapchaHandler.correctEntry(addressNumberOfKapcha)) {
                if (UserController.login(username,password)) {
                    mainFrame.dispose();
                    GeneralFormOfPag generalFormOfPag = GeneralFormOfPag.getInstance(username,password);
                } else {
                    errors.setText("username or password is incorrect,try again!");
                    changingKapchaCode();
                    //TODO
                    //ADD AN ERROR
                }
            } else {
                errors.setText("security code is incorrect,try again!");
                changingKapchaCode();
                //TODO
                //ADD AN ERROR
            }
        }
    }
}
