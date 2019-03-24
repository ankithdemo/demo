package firefox;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import shortcuts.SeleniumFunctions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AconexTest {

    public static void main(String[] args){


        System.setProperty("webdriver.chrome.driver","C:\\Webdrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo1.aconex.com");
        driver.manage().window().maximize();

        SeleniumFunctions sf = new SeleniumFunctions();

        sf.fillValue(driver,"userName","tyeung");
        sf.fillValue(driver,"password","D3m0Ac0n3x!");
        sf.click(driver,"login");
        String name = sf.getValueByX(driver,"//*[@class=\"nav-userDetails\"]","title");
        if (name.equalsIgnoreCase("Mr Trevor Jones"))
        {
            System.out.println("Account Verified");
        }
        else
        {
            System.out.println("Account not verified");
        }

        /*Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(By.id("nav-bar-CORRESPONDENCE"))).perform();*/

        sf.click(driver,"nav-bar-CORRESPONDENCE");
        sf.click(driver,"nav-bar-CORRESPONDENCE-CORRESPONDENCE-CREATEMAIL");
        //sf.click(driver,"Correspondence_correspondenceTypeID");  //click on mail type
        //sf.clickByX(driver,"//*[@id=\"Correspondence_correspondenceTypeID\"]");
        driver.switchTo().frame("frameMain");

        sf.fillValueByX(driver,"//*[@class=\"uiField uiIconField isRequired large\"]//following-sibling::input[1]","pat");
        driver.findElement(By.xpath("//*[@class=\"uiField uiIconField isRequired large\"]//following-sibling::input[1]")).sendKeys(Keys.ENTER);
        Select selectType = new Select(driver.findElement(By.id("Correspondence_correspondenceTypeID")));
        selectType.selectByValue("23");
        Select selectRFI = new Select(driver.findElement(By.id("Correspondence_correspondenceReasonID")));
        selectRFI.selectByValue("1");
        sf.fillValue(driver,"Correspondence_subject","This is a autotest, is it working harshit?");
        sf.clickByX(driver,"//*[@class=\"uiField uiIconField isRequired large\"]");

        System.out.println(driver.findElements(By.tagName("iframe")).size());
        driver.switchTo().frame(0);
        sf.fillValueByX(driver,"//*[@class=\"cke_editable cke_editable_themed cke_contents_ltr cke_show_borders\"]","hello there this is body text");

        sf.click(driver,"btnSend");
        String mailNum = sf.getTextByX(driver,"//*[@class=\"mailHeader-numbers\"]//descendant::div[6]");
        System.out.println(mailNum);

        driver.switchTo().defaultContent();
        sf.clickByX(driver,"html/body/div[1]/div[1]/div[1]/div[1]/span[1]");
        sf.click(driver,"logoff");



        sf.click(driver,"btnLogin");


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        sf.fillValue(driver,"userName","poleary");
        sf.fillValue(driver,"password","D3m0Ac0n3x!");
        sf.click(driver,"login");
        name = sf.getValueByX(driver,"//*[@class=\"nav-userDetails\"]","title");
        if (name.equalsIgnoreCase("Mr Patrick O'Leary"))
        {
            System.out.println("Account Verified");
        }
        else
        {
            System.out.println("Account not verified");
        }
        sf.click(driver,"nav-bar-CORRESPONDENCE");
        sf.click(driver,"nav-bar-CORRESPONDENCE-CORRESPONDENCE-SEARCHINBOX");
        driver.switchTo().frame("frameMain");
        WebElement table= driver.findElement(By.id("rowPerMailTableBody"));
        List<WebElement> rows_table = table.findElements(By.tagName("tr"));
        int rows = rows_table.size();
        int check=1;
        for (int row=0;row<rows;row++)
        {
            List<WebElement> column_table = rows_table.get(row).findElements(By.tagName("td"));
            String vMailNum = column_table.get(2).getText();

            if (vMailNum.equalsIgnoreCase(mailNum)) {
                System.out.println("mail received");
                check = 0;
                break;
            }
        }
        if (check==1)
            System.out.println("mail not received");


    }
}
