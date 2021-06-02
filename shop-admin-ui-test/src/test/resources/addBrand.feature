Feature: AddBrand

  Scenario Outline: Successful Login to the page, add brand and logout after
    Given I open chrome browser
    When I navigate to the login page
    And I provide the username as "<username>" and password as "<password>"
    And I click on the login button
    When I navigate to brands page
    And I click on create new brand button
    And I provide brand name
    And I click on submit button
    When Open the dropdown menu
    And click the logout button
    Then user has logged out

    Examples:
      | username | password |
      | admin | admin |