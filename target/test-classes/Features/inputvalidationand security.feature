Feature: Input Validation and Security

  Scenario: Verify max character limit for contact fields
    When User enters more than 300 characters in First Name
    Then Field should restrict input or show error

  Scenario: Verify Unicode and emojis in address field
    When User enters emojis or non-English characters in Address field
    Then They are accepted and displayed correctly
