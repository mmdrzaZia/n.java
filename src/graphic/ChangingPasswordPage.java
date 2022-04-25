package graphic;

import Log.LogInformation;
import logic.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangingPasswordPage implements ActionListener {
    static String username;
    static String password;
    static JFrame frame;
    static JLabel title;
    static JLabel oldPassword;
    static JTextField entryOldPassword;
    static JLabel newPassword;
    static JTextField entryNewPassword;
    static JButton registerChangedPassword;
    static JLabel message;

    public ChangingPasswordPage(String userUsername,String userPassword) {
        username = userUsername;
        password = userPassword;
        title = new JLabel();
        oldPassword = new JLabel();
        entryOldPassword = new JTextField();
        newPassword = new JLabel();
        entryNewPassword = new JTextField();
        registerChangedPassword = new JButton();
        message = new JLabel();
        message.setBounds(100,600,600,50);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,750);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.add(message);
        setLabelsAndTextFieldsAndButton();
        frame.setVisible(true);
    }

    private void setLabelsAndTextFieldsAndButton () {
        title.setBounds(25,100,700,25);
        title.setText("You have been offline for a long time. You must change your password!");
        title.setForeground(Color.BLACK);
        frame.add(title);

        oldPassword.setBounds(100,300,100,25);
        oldPassword.setText("Old password");
        frame.add(oldPassword);

        entryOldPassword.setBounds(200,300,100,25);
        frame.add(entryOldPassword);

        newPassword.setBounds(100,375,100,25);
        newPassword.setText("New password");
        frame.add(newPassword);

        entryNewPassword.setBounds(200,375,100,25);
        frame.add(entryNewPassword);

        registerChangedPassword.setBounds(300,500,200,25);
        registerChangedPassword.setText("Register new password");
        registerChangedPassword.addActionListener(this);
        frame.add(registerChangedPassword);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerChangedPassword) {
            int result = UserController.correctChangePassword(username,password,entryNewPassword.getText());
            if (result == 1) {
                frame.dispose();
                LogInformation.createLogStatement("ChangingPasswordPage","clickOnChangePasswordButton","password changed successfully for " + username,"info");
                LoginPage loginPage = new LoginPage(true);
            } else if (result == 2) {
                message.setText("Your new password and old password are the same!");
            } else if (result == 3) {
                message.setText("Your old password is incorrect");
            }
        }
    }
}
