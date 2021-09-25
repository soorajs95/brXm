package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objects.ContentPage;

import java.util.Map;

public class EditDocument {

    ContentPage contentPage = new ContentPage();

    @When("user edits the blog")
    public void userEditsTheBlog(Map<String, String> data) {
        contentPage.clickEditDocument();
        contentPage.editBlog(data);
    }

    @Then("document should be edited successfully")
    public void documentShouldBeEditedSuccessfully() {
        contentPage.verifyDocumentEdited();
    }
}
