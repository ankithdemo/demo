package aconex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import shortcuts.SeleniumFunctions;

import java.util.List;

public class Inbox {

    public void checkInbox(WebDriver driver,String mailNum){
        SeleniumFunctions sf = new SeleniumFunctions();
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
