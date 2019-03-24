package aconex;

import org.openqa.selenium.WebDriver;
import shortcuts.SeleniumFunctions;

public class Logout {

    public void logout(WebDriver driver){

        SeleniumFunctions sf = new SeleniumFunctions();
        driver.switchTo().defaultContent();
        sf.clickByX(driver,"html/body/div[1]/div[1]/div[1]/div[1]/span[1]");
        sf.click(driver,"logoff");
    }
}
