package steps;

import io.cucumber.java.en.Given;
import objects.BasePage;
import objects.CmsLoginPage;

public class Common {

    CmsLoginPage cmsLoginPage = new CmsLoginPage();

    @Given("user is logged in as {}")
    public void userIsLoggedInAs(String userType) {
        cmsLoginPage.navigateTo(BasePage.setValue("cms_url"));
        cmsLoginPage.loginToCms(userType);
    }

}
