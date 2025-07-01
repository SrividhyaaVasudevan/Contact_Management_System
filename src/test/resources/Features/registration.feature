Feature: User Registration on Contact List App

  Scenario: Verify sign-up with valid inputs
    Given User is on the registration page
    When User enters valid unique email and password
    And User clicks the Sign Up button
    Then User should be redirected to login page after logout
    And Close the registration browser

  Scenario: Verify registration with already registered email
    Given User is on the registration page
    When User enters already registered email
    And User clicks the Sign Up button
    Then Email address is already in use message should be shown
    And Close the registration browser

  Scenario: Verify registration with blank fields
    Given User is on the registration page
    When User leaves all registration fields blank
    And User clicks the Sign Up button
    Then Error message "User validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required., email: Email is invalid, password: Path `password` is required." should be shown on registration
    And Close the registration browser

  Scenario: Verify email format validation during sign-up
    Given User is on the registration page
    When User enters invalid email format in registration
    And User clicks the Sign Up button
    Then Email should be considered invalid during registration
    And Close the registration browser
