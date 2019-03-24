package aconex;

import org.openqa.selenium.WebDriver;
import shortcuts.SeleniumFunctions;

public class Login {

    public void openLogin(WebDriver driver)
    {
        driver.get("https://demo1.aconex.com");
        driver.manage().window().maximize();
    }

    public void login(WebDriver driver,String... arguments) {

        SeleniumFunctions sf = new SeleniumFunctions();

        String username = "tjones";
        String password = "D3m0Ac0n3x!";
        String myName = "Mr Trevor Jones";
        int check =1;

        if (arguments != null && arguments.length >= 1) {
            username = arguments[0];
            check=0;
            if (arguments.length >= 2)
            {
                password = arguments[1];
                check=0;
            }
            if (arguments.length >= 3)
            {
                myName = arguments[2];
                check=1;
            }
        }


        sf.fillValue(driver, "userName", username);
        sf.fillValue(driver, "password", password);
        sf.click(driver, "login");
        String name = sf.getValueByX(driver, "//*[@class=\"nav-userDetails\"]", "title");

        if (check==1)
        if (name.equalsIgnoreCase(myName)) {
            System.out.println("Account Verified");
        } else {
            System.out.println("Account not verified");
        }
    }

}
