Feature: Create Purchase Requisition

  Scenario: Fill item details on Create PR page

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

    When I enter PR Order Title "Automation Test PR"
    And I enter Requisition Description "This is My First PR"
    And I enter Mat Code "MAT001" for row 1
    And I enter Material Description "Test Material" for row 1
    And I enter Quantity "10" for row 1
    And I select UOM "Piece(c)" for row 1
    And I slect date "02-09-2026" for row 1
    And I select Purchase Organization "1100 - SK Industries" for row 1
    And I enter Remarks "Urgent Procurement" for row 1
    And I upload an attachment for row 1
    And I add a new row
    Then a new row should be added