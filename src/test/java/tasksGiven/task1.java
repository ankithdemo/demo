package tasksGiven;

//-----------------------------------------------
/*Feature: Sending a mail (Save to Drafts & Preview)
        as a user of Aconex platform
        I want to be able to send different kinds of emails to To, CC & BCC recipients
        So that I can collaborate with different organizations on a given project

        Scenario: Send transmittal with mandatory fields filled in*/
//------------------------------------------------


import aconex.CreateMail;
import aconex.Login;
import aconex.StartBrowser;
import org.openqa.selenium.WebDriver;

public class task1 {
    public static void main(String[] args) {

        StartBrowser startBrowser  = new StartBrowser();
        WebDriver driver = startBrowser.startChrome();

        Login login = new Login();
        login.openLogin(driver);
        login.login(driver);

        CreateMail createMail = new CreateMail();
        createMail.openMail(driver);
        createMail.fillRecipient(driver,"pat");
        createMail.chooseType(driver);
        createMail.myTask(driver);

    }

}
