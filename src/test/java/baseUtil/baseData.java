package baseUtil;

import cucumber.api.DataTable;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class baseData {

    public WebDriver driver;
    public List<DataTable> dataTables = new ArrayList<>();
    public Map<Integer,DataTable> logindata = new HashMap<>();
}
