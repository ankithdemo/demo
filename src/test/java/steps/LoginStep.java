package steps;

import aconex.CheckPage;
import aconex.Login;
import aconex.Register;
import aconex.StartBrowser;
import baseUtil.baseData;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginStep extends baseData {
    private baseData base;
    Login login = new Login();
    CheckPage check = new CheckPage();

    public LoginStep(baseData base) {
        this.base = base;
    }

    @Then("^I should see the task  page$")
    public void iShouldSeeTheTaskPage() {
        check.ifTaskPage(base.driver);
    }

    @Given("^I open login page$")
    public void iOpenLoginPage() {
        /*StartBrowser startBrowser  = new StartBrowser();
        base.driver = startBrowser.startChrome();*/
        login.openLogin(base.driver);
    }

    @And("^I enter the \"([^\"]*)\" and password$")
    public void iEnterTheAndPassword(String username) throws Throwable {
        login.login(base.driver,username);
    }
}
