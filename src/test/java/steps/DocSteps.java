package steps;

import aconex.Login;
import aconex.Register;
import baseUtil.baseData;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.List;
import java.util.Map;

public class DocSteps extends baseData {

    private baseData base;
    Login login = new Login();
    public DocSteps(baseData base) {
        this.base = base;
    }
    Register register = new Register();


    @Given("^Open doc register page$")
    public void openDocRegisterPage() {
        /*login.openLogin(base.driver);
        login.login(base.driver);*/
        register.openRegister(base.driver);
    }

    @And("^Add/Remove required field$")
    public void addRemoveRequiredField() {
        register.addColumn(base.driver,"version");
        register.removeColumn(base.driver,"lock");
    }

    @Then("^Should show appropriate result$")
    public void shouldShowAppropriateResult() {
        register.dynamicSearchDoc(base.driver,"C-9000","Discipline","Status","Date Modified");

    }

    @And("^Click on search button$")
    public void clickOnSearchButton() {
        register.setPageSize(base.driver,"100");
        register.searchDoc(base.driver);
        register.getTotal(base.driver);
    }

    @Given("^user(\\d+) logged in$")
    public void userLoggedIn(int arg0) {
        //DataTable dt = base.dataTables.get(arg0-1);
        DataTable dt = base.logindata.get(arg0);
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        login.openLogin(base.driver);
        login.login(base.driver, list.get(0).get("username"), "D3m0Ac0n3x!", list.get(0).get("name"));
    }
}
