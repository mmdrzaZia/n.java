package graphic;

import Log.LogInformation;

public class GuiMain {
    public static void main(String[] args) {
        LogInformation.createLogStatement("GuiMain","main","Application started","info");
        LoginPage loginPage = new LoginPage(false);
    }
}
