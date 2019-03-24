package shortcuts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumFunctions {

    WebElement webElement;

    public void waitFunction(WebDriver driver,String sid, int a)
    {
        WebDriverWait wait = new WebDriverWait(driver,20);
        switch (a){
            case 1: webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(sid)));
            break;
            case 2: webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sid)));
            break;
        }
    }

    public void fillValue(WebDriver driver,String sid, String value){

        /*WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement webElement;
        webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(sid)));*/

      /*  driver.findElement(By.id(sid)).clear();
        driver.findElement(By.id(sid)).sendKeys(value);*/

        waitFunction(driver,sid,1);

        webElement.clear();
        webElement.sendKeys(value);

    }

    public void fillValueByX (WebDriver driver,String sid, String value){

        /*WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement webElement;
        webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sid)));*/

        waitFunction(driver,sid,2);
        webElement.clear();
        webElement.sendKeys(value);

    }



    public void click(WebDriver driver,String sid){

        /*WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement webElement;
        webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(sid)));*/

        waitFunction(driver,sid,1);
        webElement.click();

    }

    public void clickByX(WebDriver driver,String sid){

        /*WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement webElement;
        webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sid)));*/

        waitFunction(driver,sid,2);
        webElement.click();


    }

    public String getValueByX(WebDriver driver,String sid,String attribute){

        /*WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement webElement;
        webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sid)));*/

        waitFunction(driver,sid,2);
        return webElement.getAttribute(attribute);
    }

    public String getTextByX(WebDriver driver,String sid){

        waitFunction(driver,sid,2);
        return webElement.getText();
    }

    public void selectValue(WebDriver driver,String sid,String... values)
    {
        waitFunction(driver,sid,1);
        Select select = new Select(webElement);
        for (int i=0;i<values.length;i++)
        select.selectByValue(values[i]);
    }


}
