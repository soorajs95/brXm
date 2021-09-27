@PublishDocument
Feature: Publish Document

  As an user I should be able to publish document

  Scenario: Users with author access should be able to request for publication
    Given user is logged in as author
    Then user navigates to Content page from the side pane
    And user selects My Project from the directory
    When user selects a document from the list
    And user requests the document for publication
    Then the publication request should be created successful