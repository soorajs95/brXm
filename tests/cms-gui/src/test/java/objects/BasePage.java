package objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.Hooks;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class BasePage {

    By progressBarLocator = By.xpath("//mat-progress-bar");

    WebDriver driver = Hooks.getBrowser();

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public WebDriverWait webDriverWait() {
        return new WebDriverWait(driver, (Long.parseLong(readConfig("webdriver_wait_timeout"))));
    }

    public void waitUntilElementVisible(WebElement element) {
        webDriverWait().until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementVisible(By locator) {
        webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilAllElementVisible(List<WebElement> elements) {
        webDriverWait().until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitInSeconds(int seconds) {
        try {
            new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(By.id("justForWait")));
        } catch (TimeoutException | NoSuchElementException ignored) {
        }
    }

    public void setText(WebElement element, String text) {
        waitUntilElementVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public void elementClick(WebElement element) {
        try {
            webDriverWait().until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (StaleElementReferenceException e) {
            element.click();
        }
    }

    public void elementClick(By locator) {
        webDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public void selectByVisibleText(WebElement element, String value) {
        waitUntilElementVisible(element);
        new Select(element).selectByVisibleText(value);
    }

    public void switchToFrame(WebElement element) {
        waitUntilElementVisible(element);
        driver.switchTo().frame(element);
    }

    public void waitForLoading() {
        webDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(progressBarLocator));
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
}
