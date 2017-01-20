package steps;

import base.BaseUtil;
import com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName;
import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java8.En;
import cucumber.api.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.LoginPage;
import transformation.EmailTransform;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 15/01/2017.
 */
public class LoginStep extends BaseUtil implements En {
    private BaseUtil baseUtil;
    /*
        public LoginStep(BaseUtil baseUtil) {
            this.baseUtil = baseUtil;
        }
    */
    private RemoteWebDriver remoteWebDriver;

    public LoginStep(BaseUtil baseUtil) {
        this.baseUtil = baseUtil;

        Given("^I navigate to the login page$", () -> {
            System.out.println("Navigate to login page\n");

            //System.setProperty("webdriver.gecko.driver","path of/geckodriver.exe");
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setCapability(FirefoxDriver.BINARY, System.getProperty("user.dir") + "\\tools\\FirefoxPortable\\FirefoxPortable.exe");
            desiredCapabilities.setCapability("marionette", false);
            desiredCapabilities.setPlatform(Platform.WIN10);
            desiredCapabilities.setBrowserName("firefox");

            try {
                remoteWebDriver = new RemoteWebDriver(new URL("http://192.168.56.1:5557/wd/hub"), desiredCapabilities);
                remoteWebDriver.navigate().to("http://executeautomation.com/demosite/Login.html");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            //baseUtil.webDriver.navigate().to("http://executeautomation.com/demosite/Login.html");
        });

        And("^I enter ([^\"]*) and ([^\"]*)$", (String userName, String password) -> {
            System.out.println("The userName is: " + userName);
            System.out.println("The password is: " + password);
        });

        And("^I enter the following details$", (DataTable table) -> {
            List<User> users = table.asList(User.class);

            //LoginPage loginPage = new LoginPage(baseUtil.webDriver);
            LoginPage loginPage = new LoginPage(remoteWebDriver);

            for (User user: users) {
                loginPage.login(user.userName, user.password);
            }
        });

        And("^I click login button$", () -> {
            //LoginPage loginPage = new LoginPage(baseUtil.webDriver);
            LoginPage loginPage = new LoginPage(remoteWebDriver);
            loginPage.clickLogin();
        });

        Then("^I should see the userform page$", () -> {
            //Assert.assertEquals("It's not displayed", baseUtil.webDriver.findElement(By.id("Initial")).isDisplayed(), true);
            Assert.assertEquals("It's not displayed", remoteWebDriver.findElement(By.id("Initial")).isDisplayed(), true);
        });

        And("^I enter the user email address as Email:([^\"]*)$", (String email) -> {
            String emailTrasformed = new EmailTransform().transform(email);
            System.out.println("The email address is: " + emailTrasformed);
        });
    }

    public class User {
        private String userName;
        private String password;

        public User(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }
    }
}
