package aconex;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import shortcuts.SeleniumFunctions;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Register {

    int total;
    String pageSize;
    SeleniumFunctions sf = new SeleniumFunctions();
    Map<String,List<String>> map = new HashMap<String, List<String>>();
    static List<String> header = new ArrayList<String>();
    HashMap<String,HashMap<String,String>> map3= new HashMap<String,HashMap<String,String>>();


    public void openRegister(WebDriver driver)
    {
        sf.click(driver,"nav-bar-DOC");
        sf.click(driver,"nav-bar-DOC-DOC-SEARCH");
        driver.switchTo().frame("frameMain");
    }

    public void addColumn(WebDriver driver,String... args)
    {
        sf.click(driver,"btnConfigureColumns");
        sf.waitFunction(driver,"selectedRegColumns_AVAIL",1);
        /*Select selectaddcolumn = new Select(driver.findElement(By.id("selectedRegColumns_AVAIL")));
        for (int i=0;i<args.length;i++)
        selectaddcolumn.selectByValue(args[i]);*/
        sf.selectValue(driver,"selectedRegColumns_AVAIL",args);
        sf.click(driver,"btnselectedRegColumns_ADD");
        sf.click(driver,"btnconfigureColumnsPanel_ok");

    }

    public void removeColumn(WebDriver driver,String... args)
    {
        sf.waitFunction(driver,"resultTable",1);
        sf.click(driver,"btnConfigureColumns");
        sf.waitFunction(driver,"selectedRegColumns",1);
        /*Select selectremcolumn = new Select(driver.findElement(By.id("selectedRegColumns")));
        for (int i=0;i<args.length;i++)
        selectremcolumn.selectByValue(args[i]);*/
        sf.selectValue(driver,"selectedRegColumns",args);
        sf.click(driver,"btnselectedRegColumns_REMOVE");
        sf.click(driver,"btnconfigureColumnsPanel_ok");
    }

    public void enableDocHist(WebDriver driver)
    {
        if (!driver.findElement(By.id("showDocHistory")).isEnabled())
            sf.click(driver,"showDocHistory");
    }

    public void disableDocHist(WebDriver driver)
    {
        if (driver.findElement(By.id("showDocHistory")).isEnabled())
            sf.click(driver,"showDocHistory");
    }

    public void setPageSize(WebDriver driver,String pageSize)
    {
        this.pageSize = pageSize;
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        /*Select selectPageSize = new Select(driver.findElement(By.id("resultSize")));
        selectPageSize.selectByValue(pageSize);*/
        sf.selectValue(driver,"resultSize",pageSize);
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    }

    public void searchDoc(WebDriver driver)
    {
        sf.click(driver,"btnSearch_page");
        sf.waitFunction(driver,"resultTable",1);
        sf.click(driver,"btnSearch_page");
    }

    public void getTotal(WebDriver driver)
    {
        sf.waitFunction(driver,"resultTable",1);
        total = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"numResults\"]//following::b[1]")).getText());
        System.out.println(total);
    }

    public void getHeader(WebDriver driver)
    {
        sf.waitFunction(driver,"resultTable",1);
        WebElement table= driver.findElement(By.id("resultTable"));
        List<WebElement> rows_table = table.findElements(By.tagName("tr"));

        List<WebElement> column_table = rows_table.get(0).findElements(By.tagName("th"));
        int columns = column_table.size();
        header.clear();
        for (int column = 1; column < columns; column++) {
            String text = column_table.get(column).getText();
            header.add(text);
        }
        System.out.println(header);
    }

    public void getDocinfo(WebDriver driver)
    {
        sf.waitFunction(driver,"resultTable",1);
        WebElement table= driver.findElement(By.id("resultTable"));
        List<WebElement> rows_table = table.findElements(By.tagName("tr"));
        int rows = rows_table.size();

        int noOfPages = total/(Integer.parseInt(pageSize));
        if (total%(Integer.parseInt(pageSize))!=0)
            noOfPages++;


        /*int posDocno=-1;

        List<WebElement> column_table = rows_table.get(0).findElements(By.tagName("th"));
        int columns = column_table.size();

        for (int column = 2; column < columns; column++) {

            String text = column_table.get(column).getText();
            if (text.equalsIgnoreCase("Document No")) {
                posDocno = column;
                continue;
            }
            header.add(text);

        }*/
        Register register = new Register();
        register.getHeader(driver);
        List<WebElement> column_table;
        int columns;
        int posDocno = header.indexOf("Document No");



        int docsReveiwed=0;
        JavascriptExecutor js = (JavascriptExecutor)driver;

        for (int page = 0; page < noOfPages; page++) {

            js.executeScript("setPage(" + (page + 1) + ")");
            sf.waitFunction(driver,"resultTable",1);
            table= driver.findElement(By.id("resultTable"));
            rows_table = table.findElements(By.tagName("tr"));
            rows = rows_table.size();

            sf.waitFunction(driver,"//*[@id=\"searchResultsWrapper\"]",2);
            sf.waitFunction(driver,"resultTable",1);

            for (int row = 1; row < rows; row++) {
                docsReveiwed++;
                List<String> metaData = new ArrayList<String>();
                column_table = rows_table.get(row).findElements(By.tagName("td"));
                columns = column_table.size();
                for (int column = 2;column<columns;column++) {
                    if (column==posDocno)
                        continue;
                    metaData.add(column_table.get(column).getText());
                }
                String docno = column_table.get(posDocno).getText();
                map.put(docno,metaData);
            }
        }
        System.out.println("Documents Reviewed - "+docsReveiwed);
    }





    public void getDocinfo2(WebDriver driver)
    {
        sf.waitFunction(driver,"resultTable",1);
        WebElement table= driver.findElement(By.id("resultTable"));
        List<WebElement> rows_table = table.findElements(By.tagName("tr"));
        int rows = rows_table.size();
        int noOfPages = total/(Integer.parseInt(pageSize));
        if (total%(Integer.parseInt(pageSize))!=0)
            noOfPages++;
        int posDocno=-1;

        List<WebElement> column_table = rows_table.get(0).findElements(By.tagName("th"));
        int columns = column_table.size();

        for (int column = 2; column < columns; column++) {

            String text = column_table.get(column).getText();
            if (text.equalsIgnoreCase("Document No")) {
                posDocno = column;
                continue;
            }
            header.add(text);
        }
        int docsReviewed=0;
        JavascriptExecutor js = (JavascriptExecutor)driver;

        for (int page = 0; page < 1; page++) {

            js.executeScript("setPage(" + (page + 1) + ")");
            sf.waitFunction(driver,"resultTable",1);
            table= driver.findElement(By.id("resultTable"));
            rows_table = table.findElements(By.tagName("tr"));
            rows = rows_table.size();

            sf.waitFunction(driver,"//*[@id=\"searchResultsWrapper\"]",2);
            sf.waitFunction(driver,"resultTable",1);

            for (int row = 1; row < rows; row++) {
                HashMap<String, String> map2 = new HashMap<String,String>();
                docsReviewed++;
                String json = rows_table.get(row).getAttribute("data-uimenu-props-json");
                json.substring(1,json.length()-1);
                String[] pairs = json.split(",");
                System.out.println(Arrays.toString(pairs));
                for (int i=0;i<pairs.length;i++)
                {
                    String pair = pairs[i];
                    String[] keyvalue = pair.split(":");
                    System.out.println(Arrays.toString(keyvalue));
                    map2.put(keyvalue[0],String.valueOf(keyvalue[1]));
                }

                String docno = map2.get("documentNo");
                map3.put(docno,map2);
            }
        }
        System.out.println("Documents Reviewed - "+docsReviewed);
    }


    public void searchDocument2(String key, String... values)
    {
        System.out.println("Doc no - "+key);
        for (int i=0;i<values.length;i++)
        {
            String value = values[i];
            if (value.equalsIgnoreCase("all")) {
                System.out.println(map3.get(key));
                return;
            } else {
                String data  = map3.get(key).get(value);
                System.out.println(value+" - "+data);
            }
        }
    }





    public void printTable()
    {
        for(Map.Entry<String, List<String>> m:map.entrySet()){
            System.out.println(m.getKey()+" "+ (m.getValue()));
        }

        /*Iterator<Map.Entry<String, List<String>>> itr = map.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<String, List<String>> entry = itr.next();
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue().get(4));
        }*/
    }

    public void searchDocument (String key,String... values)
    {
        System.out.println("Doc no - "+key);
        for (int i=0;i<values.length;i++)
        {
            String value = values[i];
            if (value.equalsIgnoreCase("all")) {
                System.out.println(map.get(key));
                return;
            } else {
                int index = header.indexOf(value);
                List<String> temp = map.get(key);
                System.out.println(value+" - "+temp.get(index));
            }
        }
    }


    public void dynamicSearchDoc(WebDriver driver,String docno,String... values)
    {
        sf.waitFunction(driver,"resultTable",1);
        WebElement table= driver.findElement(By.id("resultTable"));
        List<WebElement> rows_table = table.findElements(By.tagName("tr"));
        int rows = rows_table.size();
        pageSize = String.valueOf(rows-1);
        int noOfPages = total/(Integer.parseInt(pageSize));
        if (total%(Integer.parseInt(pageSize))!=0)
            noOfPages++;
        List<WebElement> column_table;
        Register register = new Register();
        register.getHeader(driver);
        String s = "Document No";
        int posDocNo = header.indexOf(s);
        System.out.println(posDocNo);
        int docsReveiwed=0;
        JavascriptExecutor js = (JavascriptExecutor)driver;
        for (int page = 0; page < noOfPages; page++)
        {
            js.executeScript("setPage(" + (page + 1) + ")");
            sf.waitFunction(driver,"resultTable",1);
            table= driver.findElement(By.id("resultTable"));
            rows_table = table.findElements(By.tagName("tr"));
            rows = rows_table.size();
            sf.waitFunction(driver,"//*[@id=\"searchResultsWrapper\"]",2);
            sf.waitFunction(driver,"resultTable",1);
            for (int row = 1; row < rows; row++) {
                docsReveiwed++;
                column_table = rows_table.get(row).findElements(By.tagName("td"));
                String doc = column_table.get(posDocNo+1).getText();
                if (doc.equalsIgnoreCase(docno)) {
                    for (int i = 0; i < values.length; i++) {
                        String value = values[i];
                        if (value.equalsIgnoreCase("all")) {
                            rows_table.get(row).getText();
                            System.out.println("Documents Reviewed - "+docsReveiwed);
                            return;
                        } else {
                            int index = header.indexOf(value);
                            System.out.println(value + " - " + column_table.get(index+1).getText());
                        }
                    }
                    System.out.println("Documents Reviewed - "+docsReveiwed);
                    return;
                }
            }
        }
        System.out.println("Documents Reviewed - "+docsReveiwed);
    }
}
