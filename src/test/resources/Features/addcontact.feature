Feature: Add Contact Functionality

  Scenario: Verify adding contact with all valid details
    Given User is logged in
    When User fills in all required and optional contact fields
    And User clicks on the Submit button
    Then Contact is added and visible in the contact list

  Scenario: Verify adding contact with missing required fields
    Given User is logged in
    When User leaves First Name and Last Name fields blank
    And User clicks on the Submit button
    Then Required field validation messages are displayed

  Scenario: Verify phone field accepts only numeric input
    Given User is logged in
    When User enters alphabets in the phone number field
    And User clicks on the Submit button
    Then Phone field should show input validation error

  Scenario: Verify adding duplicate contact details
    Given User is logged in
    When User adds a contact that already exists
    Then Application should prevent or allow duplicate based on rules

  Scenario: Verify form resets after contact is added
    Given User is logged in
    When User adds a contact and navigates to add another
    Then The add contact form should be reset for new input
