package steps;

import aconex.CreateMail;
import aconex.Login;
import aconex.StartBrowser;
import baseUtil.baseData;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class MailSteps extends baseData {

    private baseData base;
    Login login = new Login();

    public MailSteps(baseData base) {
        this.base = base;
    }

    CreateMail cm = new CreateMail();

    @And("^Click on send button$")
    public void clickOnSendButton() {
        cm.clickSend(base.driver);
    }

    @Given("^Open mail page$")
    public void openMailPage() {
        /*StartBrowser startBrowser  = new StartBrowser();
        driver = startBrowser.startChrome();*/
        login.openLogin(base.driver);
        login.login(base.driver);
        cm.openMail(base.driver);
    }

    @And("^Enter the required fields$")
    public void enterTheRequiredFields() {
        cm.chooseType(base.driver);
        cm.fillRecipient(base.driver,"pat");
        cm.chooseReason(base.driver);
        cm.fillSub(base.driver);
        cm.chooseRequired(base.driver);
        cm.fillBody(base.driver);
    }

    @Then("^Should return mail number$")
    public void shouldReturnMailNumber() {
        cm.getMailNum(base.driver);
    }
}
