Feature: Session and Navigation

  Scenario: Verify logout redirects to login page
    Given User is logged in
    When User clicks Logout
    Then User is redirected to login page

  Scenario: Verify login state on refresh
    Given User is logged in
    When User refreshes the page
    Then User stays on contact list page

  Scenario: Verify login is required to access contact list
    Given User is not logged in
    When User navigates to /contact-list URL
    Then User is redirected to login page

  Scenario: Verify user cannot access contact list after logout via browser back
    Given User logs out
    When User presses back button in browser
    Then User remains on login page
