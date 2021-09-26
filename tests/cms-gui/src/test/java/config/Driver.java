package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class Driver extends Utils {

    static String browser = setValue("browser");

    public static WebDriver getBrowserDriver() {
        switch (browser.toLowerCase()) {
            case "firefox":
                return new FirefoxDriver(firefoxOptions());
            case "edge":
                return new EdgeDriver(edgeOptions());
            case "safari":
                return new SafariDriver(new SafariOptions());
            default:
                return new ChromeDriver(chromeOptions());
        }
    }

    public static FirefoxOptions firefoxOptions() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions ffOptions = new FirefoxOptions();
        ffOptions.setHeadless(Boolean.parseBoolean(setValue("headless")));
        return ffOptions;
    }

    public static ChromeOptions chromeOptions() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(Boolean.parseBoolean(setValue("headless")));
        return chromeOptions;
    }

    public static EdgeOptions edgeOptions() {
        WebDriverManager.edgedriver().setup();
        return new EdgeOptions();
    }

}
