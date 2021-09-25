package objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CmsHomePage extends BasePage {

    @FindBy(css = "[class*='menu-item-content']")
    WebElement contentMenu;

    public CmsHomePage() {
        PageFactory.initElements(driver, this);
    }

    public void navigateToFromSidePane() {
        elementClick(contentMenu);
        waitForLoading();
        elementClick(contentMenu);
    }

}
