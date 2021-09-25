package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objects.ContentPage;

public class PublishDocument {

    ContentPage contentPage = new ContentPage();

    @When("user selects a document from the list")
    public void userSelectsADocumentToRequestPublish() {
        contentPage.selectDocumentFromList();
    }

    @And("user requests the document for publication")
    public void userRequestsTheDocumentForPublication() {
        contentPage.requestForPublication();
    }

    @Then("the publication request should be created successful")
    public void thePublicationRequestShouldBeCreatedSuccessful() {
        contentPage.verifyPublishRequestCreated();
    }
}
