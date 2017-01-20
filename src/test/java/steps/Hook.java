package steps;

import base.BaseUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

/**
 * Created by Nick on 15/01/2017.
 */
public class Hook extends BaseUtil {

    private BaseUtil baseUtil;

    public Hook(BaseUtil baseUtil) {
        this.baseUtil = baseUtil;
    }

    @Before
    public void initializeTest() {
        System.out.println("Opening the browser: Firefox");

/*
        System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "\\tools\\BrowserDriversWin64\\geckodriver.exe");
        baseUtil.webDriver = new FirefoxDriver(
                new FirefoxBinary(
                        new File(System.getProperty("user.dir"),
                                "\\tools\\FirefoxPortable\\FirefoxPortable.exe")
                ),
                new FirefoxProfile());
*/
        //System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\tools\\BrowserDriversWin64\\IEDriverServer.exe");
        //baseUtil.webDriver = new InternetExplorerDriver();
    }

    @After
    public void tearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println(scenario.getName());
        }
        System.out.println("Closing the browser: MOCK");
    }
}
