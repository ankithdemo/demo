package steps;
import aconex.Login;
import baseUtil.baseData;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

import java.util.List;
import java.util.Map;

public class Background extends baseData {
    private baseData base;
    Login login = new Login();

    public Background(baseData base) {
        this.base = base;
    }

    @Given("^user(\\d+)$")
    public void user(int arg0, DataTable dt) {

        //base.dataTables.add(dt);

        base.logindata.put(arg0,dt);

            /*List<Map<String, String>> list = dt.asMaps(String.class, String.class);
            login.openLogin(base.driver);
            switch (arg0) {
                case 1:
                    login.login(base.driver, list.get(0).get("username"), "D3m0Ac0n3x!", list.get(0).get("name"));
                    break;
                case 2:
                    login.login(base.driver, list.get(0).get("username"), "D3m0Ac0n3x!", list.get(0).get("name"));
                    break;

        }*/
            
    }


}
