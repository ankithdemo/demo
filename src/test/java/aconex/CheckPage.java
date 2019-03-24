package aconex;

import org.openqa.selenium.WebDriver;
import shortcuts.SeleniumFunctions;

public class CheckPage {

    public boolean ifTaskPage(WebDriver driver)
    {
        SeleniumFunctions sf = new SeleniumFunctions();
        driver.switchTo().frame("frameMain");
        String name = sf.getTextByX(driver,"//*[@id=\"toolbar_left\"]");
        if (name.equalsIgnoreCase("my task"))
            return true;
        else
            return false;
    }


}
