Feature: LoginFeature
  This feature do something

  Scenario: Login with correct username and password
    Given  I navigate to the login page
    And I enter the user email address as Email:admin
    And I enter the following details
      | UserName | Password      |
      | admin    | adminpassowrd |
    And I click login button
    Then I should see the userform page
