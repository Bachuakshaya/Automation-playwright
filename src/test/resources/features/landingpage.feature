Feature: P-Collab Landing Page

  Scenario: Verify landing page and navigate to Create PR

    Given I navigate to the P-Collab login page
    When I enter valid username
    And I enter valid password
    And I enter the CAPTCHA
    And I click the login button

    Then I should be redirected to the landing page

    When I click on the P-Collab organization
    Then I should be redirected to the P-Collab application

    When I click on RFQ Process
    Then I should see the RFQ Process page

    When I click on Create PR
    Then I should be redirected to the Create PR page