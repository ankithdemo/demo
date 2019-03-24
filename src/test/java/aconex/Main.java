package aconex;

import org.openqa.selenium.WebDriver;
import shortcuts.SeleniumFunctions;

//***************************************************
/*

This main class sends a transmittal and checks it with the recipient inbox

*/
//**************************************************

public class Main {

    public static void main(String[] args) {

        StartBrowser startBrowser  = new StartBrowser();
        WebDriver driver = startBrowser.startChrome();

        SeleniumFunctions sf = new SeleniumFunctions();

        Login login = new Login();
        login.openLogin(driver);
        login.login(driver);

        CreateMail createMail = new CreateMail();
        String mailnum = createMail.createMail(driver);

        Logout logout = new Logout();
        logout.logout(driver);

        sf.click(driver,"btnLogin");


        login.login(driver,"poleary");
        Inbox inbox = new Inbox();
        inbox.checkInbox(driver,mailnum);




    }

}
