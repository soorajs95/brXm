package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objects.CmsHomePage;
import objects.ContentPage;

import java.util.Map;

public class CreateDocument {

    CmsHomePage cmsHomePage = new CmsHomePage();
    ContentPage contentPage = new ContentPage();

    @Then("user navigates to Content page from the side pane")
    public void userNavigatesToContentPageFromTheSidePane() {
        cmsHomePage.navigateToFromSidePane();
    }

    @And("user selects {} from the directory")
    public void userSelectsMyProjectFromTheDirectory(String folderName) {
        contentPage.selectRootContentFolder(folderName);
    }

    @Then("user creates a new document under {} directory")
    public void userCreatesANewDocumentUnderTestDirectory(String folderName, Map<String, String> data) {
        contentPage.createDocument(folderName, data);
    }

    @When("user creates a new blog")
    public void userCreatesANewBlogUnderTestBlogs(Map<String, String> data) {
        contentPage.createBlog(data);
    }

    @Then("document should be created successfully")
    public void documentShouldBeCreatedSuccessfully() {
        contentPage.verifyDocumentCreated();
    }

}
