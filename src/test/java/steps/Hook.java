package steps;

import aconex.StartBrowser;
import baseUtil.baseData;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

public class Hook extends baseData {

    private static baseData base;

    public Hook(baseData base) {
        this.base = base;
    }


    @Before
    public void initializeTest(){
        StartBrowser startBrowser  = new StartBrowser();
        base.driver = startBrowser.startChrome();
        System.out.println("starting web driver");
    }

    @After
    public void tearDownTest(){
        System.out.println("closing chrome browser");
    }
}
