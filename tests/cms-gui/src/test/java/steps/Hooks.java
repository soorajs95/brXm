package steps;

import config.Driver;
import config.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = Driver.getBrowserDriver();
        Utils.setBrowserSize();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed())
            Utils.getScreenshot(scenario);
        driver.quit();
    }

}
