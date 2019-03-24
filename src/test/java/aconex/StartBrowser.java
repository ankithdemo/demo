package aconex;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class StartBrowser {

    public WebDriver startChrome(String... args)
    {
        System.setProperty("webdriver.chrome.driver","C:\\Webdrivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        for (int i=0;i<args.length;i++)
        options.addExtensions(new File(args[i]));
        options.setBinary(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"));
        WebDriver driver = new ChromeDriver(options);
        return  driver;

    }

    public WebDriver startFirefox(String... args)
    {
        FirefoxProfile profile = new FirefoxProfile();
        for (int i=0;i<args.length;i++)
        profile.addExtension(new File(args[i]));
        System.setProperty("webdriver.gecko.driver","C:\\Gecko\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        return driver;
    }
}
