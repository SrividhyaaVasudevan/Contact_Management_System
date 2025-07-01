Feature: Login Functionality

  Scenario: Verify login with valid credentials
    Given User is on the login page
    When User enters valid email and password
    And User clicks on the login button
    Then User is redirected to the contact list page
    And Close the browser

  Scenario: Verify login with incorrect password
    Given User is on the login page
    When User enters valid email and invalid password
    And User clicks on the login button
    Then User should stay on login page
    And Close the browser

  Scenario: Verify login with empty fields
    Given User is on the login page
    When User leaves email and password fields empty
    And User clicks on the login button
    Then Required field validation should appear
    And Close the browser

  Scenario: Verify login with invalid email format
    Given User is on the login page
    When User enters an invalid email format
    And User clicks on the login button
    Then Email format error should be displayed
    And Close the browser

  Scenario: Verify password field masks input
    Given User is on the login page
    Then Password field should mask input
    And Close the browser