package firefox;



import aconex.StartBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import shortcuts.SeleniumFunctions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AconexDocFilter {

    public static void main(String[] args) {

        //System.setProperty("webdriver.chrome.driver","C:\\Webdrivers\\chromedriver.exe");
        /*ChromeOptions options = new ChromeOptions();

        options.addExtensions(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\72.0.3626.81\\default_apps\\docs.crx"));
        options.setBinary(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"));

        WebDriver driver = new ChromeDriver(options);*/

        StartBrowser startBrowser = new StartBrowser();
        WebDriver driver = startBrowser.startChrome("C:\\Program Files (x86)\\Google\\Chrome\\Application\\72.0.3626.81\\default_apps\\docs.crx");


        driver.get("https://demo1.aconex.com");
        driver.manage().window().maximize();

        SeleniumFunctions sf = new SeleniumFunctions();

        sf.fillValue(driver,"userName","tjones");
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

        //clicking on doc - > doc register
        sf.click(driver,"nav-bar-DOC");
        sf.click(driver,"nav-bar-DOC-DOC-SEARCH");
        driver.switchTo().frame("frameMain");

        //filling doc fields
        /*sf.fillValue(driver,"docno","A-100");
        sf.click(driver,"showDocHistory");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Select selectStatus = new Select(driver.findElement(By.id("docstatus")));
        selectStatus.selectByValue("1879048216");*/

        //Adding/Removing a new column
        sf.click(driver,"btnConfigureColumns");
        Select selectaddcolumn = new Select(driver.findElement(By.id("selectedRegColumns_AVAIL")));
        selectaddcolumn.selectByValue("version");
        sf.click(driver,"btnselectedRegColumns_ADD");
        Select selectremcolumn = new Select(driver.findElement(By.id("selectedRegColumns")));
        selectremcolumn.selectByValue("lock");
        sf.click(driver,"btnselectedRegColumns_REMOVE");
        sf.click(driver,"btnconfigureColumnsPanel_ok");


        //setting show doc history
        if (!driver.findElement(By.id("showDocHistory")).isEnabled())
        sf.click(driver,"showDocHistory");

        //setting page size
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        Select selectPageSize = new Select(driver.findElement(By.id("resultSize")));
        String pageSize = "25";
        selectPageSize.selectByValue(pageSize);
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

        //clicking search button
        sf.click(driver,"btnSearch_page");



        //***** check the table content if status in doc field and table are same


        /*sf.waitFunction(driver,"resultTable",1);
        WebElement table= driver.findElement(By.id("resultTable"));
        List<WebElement> rows_table = table.findElements(By.tagName("tr"));
        int rows = rows_table.size();


        List<WebElement> column_table = rows_table.get(0).findElements(By.tagName("th"));
        int columns = column_table.size();
        int pos = 0;

        for (int column = 0; column < columns; column++) {

            String text = column_table.get(column).getText();
            if (text.equalsIgnoreCase("Status"))
                break;
            pos++;
        }
        System.out.println(pos);


        int check=1;
        for (int row=1;row<rows;row++) {
            column_table = rows_table.get(row).findElements(By.tagName("td"));

            String text = column_table.get(pos).getText();
            if (!(text.equalsIgnoreCase("For Construction"))) {
                System.out.println("Search Failed");
                check = 0;
                break;
            }
        }
        if (check==1)
            System.out.println("Search verified");
        */


        //No of doc
        int total = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"numResults\"]//following::b[1]")).getText());
        System.out.println(total);


        //checking if latest version of the doc is bold

        sf.waitFunction(driver,"resultTable",1);
        WebElement table= driver.findElement(By.id("resultTable"));
        List<WebElement> rows_table = table.findElements(By.tagName("tr"));
        int rows = rows_table.size();

        List<WebElement> column_table = rows_table.get(0).findElements(By.tagName("th"));
        int columns = column_table.size();
        int posVersion = -1;
        int posDocno = -1;



        for (int column = 0; column < columns; column++) {

            String text = column_table.get(column).getText();
            if (text.equalsIgnoreCase("Version"))
                posVersion = column;
            if (text.equalsIgnoreCase("Document No"))
                posDocno = column;
        }
        System.out.println("titles position retrieved");
        System.out.println(posDocno);
        System.out.println(posVersion);

        int check=1;
        column_table = rows_table.get(1).findElements(By.tagName("td"));

        String initDocno = column_table.get(posDocno).getText();
        int initVersion = Integer.parseInt(column_table.get(posVersion).getText());

        int noOfPages = total/(Integer.parseInt(pageSize)) +1;

        int docsReveiwed=0;
        JavascriptExecutor js = (JavascriptExecutor)driver;


        for (int page=0;page<noOfPages;page++) {

            js.executeScript("setPage("+(page+1)+")");

            sf.waitFunction(driver,"resultTable",1);
            table= driver.findElement(By.id("resultTable"));
            rows_table = table.findElements(By.tagName("tr"));
            rows = rows_table.size();

            sf.waitFunction(driver,"//*[@id=\"searchResultsWrapper\"]",2);
            sf.waitFunction(driver,"resultTable",1);

            for (int row = 1; row < rows; row++) {
                docsReveiwed++;
                column_table = rows_table.get(row).findElements(By.tagName("td"));
                String docNo = column_table.get(posDocno).getText();
                int version = Integer.parseInt(column_table.get(posVersion).getText());

                int checkBold = 0;
            /*try{

                column_table.get(posDocno).findElement(By.tagName("strong"));
                checkBold = 1;

            }catch (Exception e)
            {
                //System.out.println("not bold");
            }*/

                String data = rows_table.get(row).getAttribute("data-uimenu-props-json");
                int start = data.indexOf("isCurrent");
                int stop = data.indexOf("true", start);
                if ((stop - start) == 11)
                    check = 1;

                if (initDocno.equalsIgnoreCase(docNo)) {
                    if (initVersion > version) {
                        if (checkBold == 1) {
                            System.out.println("invalid doc version");
                            System.out.println(docNo + " current doc");
                            System.out.println(initDocno + " initial doc");
                            System.out.println(initVersion + " initial version");
                            System.out.println(version + " current version");
                            check = 0;
                            break;
                        }
                    } else
                        initVersion = version;
                } else {
                    System.out.println(initDocno);
                    System.out.println(initVersion);
                    initDocno = docNo;
                    initVersion = version;
                }
            }
        }




        if (check==1)
            System.out.println("everything is fine");
        System.out.println("Docs reviewed - "+docsReveiwed);

        System.out.println("checked the table");

        /*JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("setPage(12)");*/






    }
}
