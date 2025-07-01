Feature: UI and Responsiveness

  Scenario: Verify alignment of fields on contact form
    Given User opens Add Contact form on desktop
    Then All fields and buttons are properly aligned

  Scenario: Verify toast messages or success indicators
    When User adds or deletes a contact
    Then A success toast or confirmation message appears