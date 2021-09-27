package objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CmsLoginPage extends BasePage {

    public CmsLoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "id1")
    WebElement userName;

    @FindBy(id = "id2")
    WebElement password;

    @FindBy(id = "id9")
    WebElement loginButton;

    public void loginToCms(String userType) {
        waitUntilElementVisible(loginButton);
        userName.sendKeys(userType);
        password.sendKeys(userType);
        loginButton.click();
    }
}
