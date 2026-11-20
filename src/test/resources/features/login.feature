Feature: Login to P-Collab

  Background:
    Given I navigate to the P-Collab login page

  Scenario: 1. Successful login with valid credentials
    When I enter valid username
    And I enter valid password
    And I enter the CAPTCHA
    And I click the login button
    Then I should be redirected to the landing page

  Scenario: 2. Login fails with invalid credentials
    When I enter username "akshaya.bachu@incresol.com"
    And I enter password "wrongpassword"
    And I enter the CAPTCHA
    And I click the login button
    Then I should see an error message

  Scenario: 3. Login fails with empty username
    When I click on the username field and then click away
    And I click the login button
    Then I should see a validation error for username

  Scenario:4. Login fails with empty password
    When I enter valid username
    And I enter the CAPTCHA
    And I click the login button
    Then I should see a validation error for password