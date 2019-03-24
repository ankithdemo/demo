package aconex;
import org.openqa.selenium.WebDriver;

//***************************************
/*
This class gets and stores the table by doc no
        as a key and rest of its attributes as values

*/
//****************************************

public class Main2 {

    public static void main(String[] args) {

        StartBrowser startBrowser = new StartBrowser();
        WebDriver driver = startBrowser.startChrome("C:\\Program Files (x86)\\Google\\Chrome\\Application\\72.0.3626.81\\default_apps\\docs.crx");

        Login login = new Login();
        login.openLogin(driver);
        login.login(driver);

        Register register = new Register();
        register.openRegister(driver);
        register.addColumn(driver,"version");
        register.removeColumn(driver,"lock");

        //register.disableDocHist(driver);
        register.setPageSize(driver,"100");
        register.searchDoc(driver);
        register.getTotal(driver);
        //register.getDocinfo(driver);
        //register.printTable();
        //register.searchDocument("C-9000","Discipline","Status","Date Modified");


        register.getDocinfo2(driver);
        register.searchDocument2("C-9000","documentVersion","documentTitle","fileId");
    }


}

