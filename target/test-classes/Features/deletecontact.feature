Feature: Delete Contact Functionality

  Scenario: Verify deleting a contact
    Given User is logged in and contact exists
    When User clicks delete icon
    Then Contact is removed from the list

  Scenario: Verify delete confirmation
    Given User is about to delete a contact
    Then Confirmation alert appears
    When User confirms deletion
    Then Contact is deleted

  Scenario: Verify contact no longer appears after deletion
    Given User deletes a contact
    When Page is refreshed
    Then Deleted contact is not listed