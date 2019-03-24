Feature: LoginFeature
  This login functionality of Aconex

  Background:
    Given user1
      | username | name             |
      | tjones   | Mr. Trevor Jones |
    Given user2
      | username | name          |
      | tyeung   | Mr. Tim Yeung |


#  @smoke
#  Scenario: Login with correct credentials
#    Given I open login page
#    And I enter the "tjones" and password
#    Then I should see the task  page

  @test
  Scenario: Create a mail
    Given Open mail page
    And Enter the required fields
    And Click on send button
    Then Should return mail number

  @loginh
  Scenario: Open Doc register
    Given user1 logged in
    Given Open doc register page
    And Add/Remove required field
    And Click on search button
    Then Should show appropriate result