package objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class ContentPage extends BasePage {

    @FindBy(css = "iframe[src$='/cms/?iframe']")
    WebElement contentIframe;

    @FindBy(css = "[class='row row-selected'] [class='hi hi-more hi-m']")
    WebElement folderOptions;

    @FindBy(css = "[title='Add new document...']")
    WebElement addDocumentOption;

    @FindBy(className = "hippo-form-input")
    WebElement newDocumentDialogNameInput;

    @FindBy(className = "hippo-form-select")
    WebElement newDocumentDialogDocTypeDropdown;

    @FindBy(css = "input[value='OK']")
    WebElement newDocumentDialogPrimaryButton;

    @FindBy(css = "div.hippo-editor-field-value-container>input")
    WebElement titleInput;

    @FindBy(css = "div.hippo-editor-field-textarea textarea")
    WebElement introductionTextArea;

    @FindBy(css = "[role='textbox']")
    WebElement contentTextBox;

    @FindBy(css = "div.btn-bar")
    WebElement authorSelectButton;

    @FindBy(name = "buttons:1:button")
    WebElement authorDialogPrimaryButton;

    @FindBy(css = "input.date")
    WebElement dateInput;

    @FindBy(css = "[title='Save changes and stop editing']")
    WebElement doneButton;

    @FindBy(css = "div.hippo-preview-document")
    WebElement publicationDate;

    String folderNameLocator = "a[title='%s']";
    String selectAuthorLocator = "span[title='%s']";
    String categoriesLocator = "//option[text()='%s']";
    String documentListLocator = "table.hippo-list-documents span[title='%s']";
    String authorFolderSelectionLocator = "form.hippo-dialog-form div>a[title='%s']";

    String documentName;

    public ContentPage() {
        PageFactory.initElements(driver, this);
    }

    public void selectRootContentFolder(String folderName) {
        switchToFrame(contentIframe);
        By locator = By.cssSelector(String.format(folderNameLocator, folderName));
        elementClick(locator);
    }

    public void selectFolder(String folderName) {
        By locator = By.cssSelector(String.format(folderNameLocator, folderName));
        elementClick(locator);
    }

    public void selectFolderAuthor(String folderName) {
        By locator = By.cssSelector(String.format(authorFolderSelectionLocator, folderName));
        waitUntilElementVisible(locator);
        if (!driver.findElement(locator).getAttribute("class").contains("expanded"))
            elementClick(locator);
    }

    public void createDocument(String folderName, Map<String, String> data) {
        selectFolder(folderName);
        elementClick(folderOptions);
        elementClick(addDocumentOption);
        documentName = data.get("name") + "-" + java.time.LocalDateTime.now();
        setText(newDocumentDialogNameInput, documentName);
        selectByVisibleText(newDocumentDialogDocTypeDropdown, data.get("type"));
        elementClick(newDocumentDialogPrimaryButton);
    }

    public void createBlog(Map<String, String> data) {
        setText(titleInput, data.get("title"));
        setText(introductionTextArea, data.get("introduction"));
        setText(contentTextBox, data.get("content"));
        elementClick(authorSelectButton);
        selectAuthor(data.get("author"));
        setText(dateInput, data.get("date"));
        By locator = By.xpath(String.format(categoriesLocator, data.get("category")));
        elementClick(locator);
        elementClick(doneButton);
    }

    public void selectAuthor(String authorName) {
        selectFolderAuthor("My Project");
        selectFolderAuthor("blog");
        selectFolderAuthor("authors");
        By locator = By.cssSelector(String.format(selectAuthorLocator, authorName));
        elementClick(locator);
        elementClick(authorDialogPrimaryButton);
    }

    public void verifyDocumentCreated() {
        By locator = By.cssSelector(String.format(documentListLocator, documentName));
        elementClick(locator);
        waitUntilElementVisible(publicationDate);
        Assert.assertTrue(publicationDate.isDisplayed());
    }

}
