package aconex;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import shortcuts.SeleniumFunctions;

import java.util.List;
import java.util.Scanner;

public class CreateMail {
    SeleniumFunctions sf = new SeleniumFunctions();

    public void openMail(WebDriver driver)
    {
        sf.click(driver,"nav-bar-CORRESPONDENCE");
        sf.click(driver,"nav-bar-CORRESPONDENCE-CORRESPONDENCE-CREATEMAIL");
        driver.switchTo().frame("frameMain");
    }

    public void fillRecipient(WebDriver driver,String... args)
    {
        for (int i=0;i<args.length;i++) {
            sf.fillValueByX(driver, "//*[@class=\"uiField uiIconField isRequired large\"]//following-sibling::input[1]", args[i]);
            driver.findElement(By.xpath("//*[@class=\"uiField uiIconField isRequired large\"]//following-sibling::input[1]")).sendKeys(Keys.ENTER);
        }
    }

    public void chooseType(WebDriver driver)
    {
        sf.selectValue(driver,"Correspondence_correspondenceTypeID","23");
    }

    public void chooseReason(WebDriver driver)
    {
        sf.selectValue(driver,"Correspondence_correspondenceReasonID","1");
    }

    public void fillSub(WebDriver driver)
    {
        sf.fillValue(driver,"Correspondence_subject","This is a autotest, is it working harshit?");
    }

    public void chooseRequired(WebDriver driver)
    {
        sf.clickByX(driver,"//*[@class=\"uiField uiIconField isRequired large\"]");
    }

    public void fillBody(WebDriver driver)
    {
        System.out.println(driver.findElements(By.tagName("iframe")).size());
        driver.switchTo().frame(0);
        sf.fillValueByX(driver,"//*[@class=\"cke_editable cke_editable_themed cke_contents_ltr cke_show_borders\"]","hello there this is body text");
        driver.switchTo().parentFrame();
    }

    public void clickSend(WebDriver driver)
    {
        sf.click(driver,"btnSend");
    }

    public String getMailNum(WebDriver driver)
    {
        String mailNum = sf.getTextByX(driver,"//*[@class=\"mailHeader-numbers\"]//descendant::div[6]");
        System.out.println(mailNum);
        return mailNum;
    }




    public String createMail(WebDriver driver){


        sf.click(driver,"nav-bar-CORRESPONDENCE");
        sf.click(driver,"nav-bar-CORRESPONDENCE-CORRESPONDENCE-CREATEMAIL");
        driver.switchTo().frame("frameMain");

        sf.fillValueByX(driver,"//*[@class=\"uiField uiIconField isRequired large\"]//following-sibling::input[1]","pat");
        driver.findElement(By.xpath("//*[@class=\"uiField uiIconField isRequired large\"]//following-sibling::input[1]")).sendKeys(Keys.ENTER);
        /*Select selectType = new Select(driver.findElement(By.id("Correspondence_correspondenceTypeID")));
        selectType.selectByValue("23");*/
        sf.selectValue(driver,"Correspondence_correspondenceTypeID","23");
        /*Select selectRFI = new Select(driver.findElement(By.id("Correspondence_correspondenceReasonID")));
        selectRFI.selectByValue("1");*/
        sf.selectValue(driver,"Correspondence_correspondenceReasonID","1");
        sf.fillValue(driver,"Correspondence_subject","This is a autotest, is it working harshit?");
        sf.clickByX(driver,"//*[@class=\"uiField uiIconField isRequired large\"]");

        System.out.println(driver.findElements(By.tagName("iframe")).size());
        driver.switchTo().frame(0);
        sf.fillValueByX(driver,"//*[@class=\"cke_editable cke_editable_themed cke_contents_ltr cke_show_borders\"]","hello there this is body text");

        driver.switchTo().parentFrame();
        sf.click(driver,"btnSend");
        String mailNum = sf.getTextByX(driver,"//*[@class=\"mailHeader-numbers\"]//descendant::div[6]");
        System.out.println(mailNum);
        return mailNum;
    }

    public void selectResponseRequired(WebDriver driver)
    {
        sf.selectValue(driver,"Correspondence_responseDate-responseTypes","2");
        sf.fillValue(driver,"Correspondence_responseDate-responseDate_da","07/04/2019");
    }



    Scanner sc = new Scanner(System.in);

    public void myTask(WebDriver driver)
    {
        //check all required fields
        sf.waitFunction(driver,"heroSection",1);
        WebElement fieldTable = driver.findElement(By.id("heroSection"));
        List<WebElement> fields = fieldTable.findElements(By.tagName("tr"));
        int length = fields.size();
        for (int i=0;i<length;i++)
        {
            List<WebElement> field = fields.get(i).findElements(By.tagName("td"));
            int length2 = field.size();
            for (int j=0;j<length2;j++)
            {
                int check=0;
                check = field.get(j).findElements(By.tagName("span")).size();
                if (check!=0)
                {
                    String value = field.get(j).getText();
                    System.out.println(value);

                    if (value.contains("Reason for Issue")) {
                        chooseReason(driver);
                        continue;
                    }

                    if (value.contains("Subject"))
                    {
                        fillSub(driver);
                        continue;
                    }
                    if (value.contains("Response Required"))
                    {
                        selectResponseRequired(driver);
                        continue;
                    }


                    /*int check2=0;
                    check2 = field.get(j).findElements(By.tagName("select")).size();
                    if (check2!=0)
                    {
                        Select select = new Select(field.get(j).findElement(By.tagName("select")));
                        select.selectByIndex(2);

                    }
                    check2 = field.get(j).findElements(By.tagName("input")).size();
                    if (check2!=0)
                    {
                        field.get(j).findElement(By.tagName("input")).sendKeys("");
                    }*/

                }
            }
        }


    }

}
