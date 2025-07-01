Feature: Edit Contact Functionality

  Scenario: Verify user can edit an existing contact
    Given User is logged in and contact exists
    When User clicks Edit and modifies phone or email
    And User saves the changes
    Then Contact list shows updated information

  Scenario: Verify canceling an edit
    Given User is editing a contact
    When User clicks cancel or navigates away
    Then Contact remains unchanged

  Scenario: Verify validation during edit
    Given User is editing a contact
    When User removes Last Name and saves
    Then Validation error "Last Name is required" is shown