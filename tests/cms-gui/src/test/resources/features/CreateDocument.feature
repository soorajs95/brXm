@CreateDocument @Regression
Feature: Create Document

  As an user I should be able to create document

  Scenario Outline: Users with <user> access should be to create a document
    Given user is logged in as <user>
    Then user navigates to Content page from the side pane
    And user selects My Project from the directory
    Then user creates a new document under test directory
      | name | test      |
      | type | Blog Post |
    When user creates a new blog
      | title        | test-title   |
      | introduction | test-intro   |
      | content      | test-content |
      | author       | Hippo Author |
      | date         | 9/25/2021    |
      | category     | CMS          |
    Then document should be created successfully
    Examples:
      | user   |
      | author |
      | editor |
      | admin  |
