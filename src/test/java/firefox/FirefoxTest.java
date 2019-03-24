package firefox;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import shortcuts.SeleniumFunctions;

public class FirefoxTest {

    /*public void UploadFileUsingSendKeys(WebDriver driver)
            throws InterruptedException {


        String workingDir = System.getProperty("user.dir");
        String filepath = workingDir + "\\SeleniumWebdriverUploadFile.html";
        driver.get(filepath);

        WebElement fileInput = driver.findElement(By.name("Filedata"));
        fileInput.sendKeys(filepath);

        // Added a wait to make you notice the difference.
        Thread.sleep(1000);

        driver.findElement(By.name("Filedata")).sendKeys(
                "C:\\Users\\kotaredd\\Desktop\\gmail.txt");

        // Added sleep to make you see the difference.
        Thread.sleep(1000);

        fileInput.sendKeys(filepath);
    }*/




    public static void main(String[] args) throws Exception{

        System.setProperty("webdriver.chrome.driver","C:\\Webdrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        /*System.setProperty("webdriver.gecko.driver","C:\\Gecko\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();*/

        /*driver.get("https://demo1.aconex.com/Logon");
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys("tjones");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("D3m0Ac0n3x!");
        driver.findElement(By.id("command")).submit();

        *//*WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(pageLoadCondition);*//*
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        *//*WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-bar-CORRESPONDENCE")));*//*

        driver.findElement(By.id("nav-bar-CORRESPONDENCE")).click();
        driver.findElement(By.id("nav-bar-CORRESPONDENCE-CORRESPONDENCE-CREATEMAIL")).click();

        driver.findElement(By.id("Correspondence_correspondenceTypeID")).click();
        new Select(driver.findElement(By.id("Correspondence_correspondenceTypeID"))).selectByVisibleText("Request For Information");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='*'])[1]/following::option[11]")).click();

        driver.findElement(By.id("SPEED_ADDRESS_TO")).click();
        driver.findElement(By.id("SPEED_ADDRESS_TO")).clear();
        driver.findElement(By.id("SPEED_ADDRESS_TO")).sendKeys("pat");
        driver.findElement(By.id("SPEED_ADDRESS_TO")).sendKeys(Keys.ENTER);
        //driver.close();
        //driver.quit();*/

       /* driver.get("https://www.seleniumeasy.com/");
        driver.findElement(By.linkText("Maven")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='about Install Maven in Eclipse'])[1]/following::a[1]")).click();
        driver.findElement(By.id("edit-name")).click();
        driver.findElement(By.id("edit-name")).clear();
        driver.findElement(By.id("edit-name")).sendKeys("harshit");
        driver.findElement(By.id("edit-subject")).clear();
        driver.findElement(By.id("edit-subject")).sendKeys("sheth");*/


       driver.get("https://www.gmail.com");
       driver.manage().window().maximize();


       SeleniumFunctions sf = new SeleniumFunctions();

       /*driver.findElement(By.id("identifierId")).clear();
       driver.findElement(By.id("identifierId")).sendKeys("ankith.demo@gmail.com");*/

       sf.fillValue(driver,"identifierId","ankith.demo@gmail.com");


       //driver.findElement(By.id("identifierNext")).click();

       sf.click(driver,"identifierNext");

       //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        // driver.findElement(By.xpath("//input[@name='password']")).sendKeys("D3m0Ac0n3x!");

       sf.fillValueByX(driver,"//input[@name='password']","D3m0Ac0n3x");


       //driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();

       /*driver.findElement(By.id("passwordNext")).sendKeys(Keys.TAB);
       driver.findElement(By.id("passwordNext")).sendKeys(Keys.TAB);
       driver.findElement(By.id("passwordNext")).sendKeys(Keys.ENTER);*/

       sf.click(driver,"passwordNext");



        /*WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement webElement;
        webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='gb']/div[2]/div[3]/div/div[2]/div/a")));
        String verfiy = (webElement.getAttribute("aria-label"));*/

        String verify = sf.getValueByX(driver,"//*[@id='gb']/div[2]/div[3]/div/div[2]/div/a","aria-label");

        if (verify.contains("ankith.demo@gmail.com"))
        {
            System.out.println("Account Verified");
        }
        else
        {
            System.out.println("Account not Verified");
        }



            //sending mail

        //sf.clickByX(driver,"//*[@id=\":76\"]/div/div"); //compose button
        sf.clickByX(driver,"//*[@class=\"T-I J-J5-Ji T-I-KE L3\"]");

        //sf.clickByX(driver,"//*[@class=\"aoD az6\"]");
        //sf.clickByX(driver,"//*[@id=\":cl\"]");

        sf.clickByX(driver,"//*[@class=\"wO nr l1\"]//following::textarea");

        sf.fillValueByX(driver,"//*[@class=\"wO nr l1\"]//following::textarea","i.harshit.cool@gmail.com");
        sf.fillValueByX(driver,"//*[@class=\"aoT\"]","this is a test");
        sf.clickByX(driver,"//*[@class=\"Am Al editable LW-avf\"]");
        sf.fillValueByX(driver,"//*[@class=\"Am Al editable LW-avf\"]","Hi ankith, this is a ankith. I am just testing buddy");
        sf.clickByX(driver,"//*[@class=\"a1 aaA aMZ\"]");  //clicking on attachment button


        //sf.clickByX(driver,"//*[@class=\"wG J-Z-I\"]");


        // All tries with javascript

        /*JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementsByName('Filedata')[0].style.display=\"block\"");
        js.executeScript("WinWaitActive(\"Open\")");
        js.executeScript("Send(\"C:\\Users\\kotaredd\\Desktop\\gmail.txt\")");
        js.executeScript("Send(\"{ENTER}\")");
        js.executeScript("document.getElementsByName('Filedata')[0].setAttribute('value', 'C:\\Users\\kotaredd\\Desktop\\gmail.txt");
        js.executeScript("document.getElementsByName('Filedata')[0].value = C:\\Users\\kotaredd\\Desktop\\gmail.txt");

        sf.fillValueByX(driver,"//*[@name='Filedata']","C://Users//kotaredd//Desktop//gmail.txt");
        js.executeScript("document.getElementsByName('Filedata')[0].setAttribute('value', 'C:/Users/kotaredd/Desktop/gmail.txt')");*/





        //Using AutoIT

        Runtime.getRuntime().exec("C:\\AutoITscripts\\gmail.exe");




        //Using Robo Class

       /* ClipboardOwner owner=null;
        String file = "C:\\Users\\kotaredd\\Desktop\\gmail.txt";
        Robot robot=new Robot();
        StringSelection attachment_path=new StringSelection(file);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(attachment_path,owner);

        robot.setAutoDelay(5000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.setAutoDelay(5000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);*/


        /*sf.clickByX(driver,"//*[@class=\"T-I J-J5-Ji aoO T-I-atl L3\"]");   //click on send button
        Thread.sleep(4000);
        sf.clickByX(driver,"//*[@class=\"gb_b gb_hb gb_R\"]");  //click on account icon
        sf.clickByX(driver,"//*[@class=\"gb_Aa gb_zg gb_Hg gb_ef gb_Tb\"]");  //sign out btn

            //ending of sending mail



        sf.fillValueByX(driver,"//input[@name='password']","D3m0Ac0n3x!");
        sf.click(driver,"passwordNext");*/



        //checking the first mail

        /*sf.clickByX(driver,"//*[@id=\":2y\"]");  //first mail
        sf.clickByX(driver,"//*[@id=\":ay\"]");  //click on expand*/



        /*WebDriverWait wait2 = new WebDriverWait(driver,20);
        WebElement webElement2;
        webElement2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\":ad\"]/div[1]/div[2]/div[2]/div/table/tbody/tr[1]/td[2]/span/span/span[1]")));
        System.out.println(webElement2.getAttribute("email"));*/

        //String email = (sf.getValueByX(driver,"//*[@id=\":ad\"]/div[1]/div[2]/div[2]/div/table/tbody/tr[1]/td[2]/span/span/span[1]","email"));  //email of the sender
/*        String email = sf.getValueByX(driver,"//*[@id=\":2z\"]/span[1]/span","email");
        if (email.equalsIgnoreCase("ankith.demo@gmail.com"))
        {
            System.out.println("email verified");
        }
        else
        {
            System.out.println("email not verified");
        }*/





       /*WebDriverWait wait = new WebDriverWait(driver,30);
       wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));*/

        //driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //Thread.sleep(5000);

        //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        //driver.findElement(By.xpath("//input[@name='password']")).clear();




    }


}
