@EditDocument @Regression
Feature: Edit Document

  As an user I should be able to edit document

  Scenario Outline: Users with <user> access should be to edit a document
    Given user is logged in as <user>
    Then user navigates to Content page from the side pane
    And user selects My Project from the directory
    Then user selects a document from the list
    When user edits the blog
      | title        | test-title-edited   |
      | introduction | test-intro-edited   |
      | content      | test-content-edited |
    Then document should be edited successfully
    Examples:
      | user   |
      | author |
      | editor |
      | admin  |