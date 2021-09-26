package config;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import steps.Hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    public static WebDriver getDriver() {
        return Hooks.driver;
    }

    public static String readConfig(String config) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/test/resources/Config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(config).trim();
    }

    public static void getScreenshot(Scenario scenario) {
        try {
            WebDriver driver = getDriver();
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String scenarioName = scenario.getName().replaceAll("\\s", "");
            FileUtils.copyFile(scrFile, new File("target/screenshot/" + scenarioName + ".png"));
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenarioName);
        } catch (Throwable err) {
            err.printStackTrace();
        }
    }

    public static String setValue(String property) {
        return System.getProperty(property) == null ? readConfig(property) : System.getProperty(property);
    }

    public static void setBrowserSize() {
        WebDriver driver = getDriver();
        int width = Integer.parseInt(setValue("browser_width"));
        int height = Integer.parseInt(setValue("browser_height"));
        Dimension d = new Dimension(width, height);
        driver.manage().window().setSize(d);
    }
}
