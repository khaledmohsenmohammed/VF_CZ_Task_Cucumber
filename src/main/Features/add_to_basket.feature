Feature: Add iPhone to the Basket

  Scenario: User adds an iPhone to the basket
    Given the user is on the Vodafone Czech Republic homepage
    When the user navigates to the iPhone product page
    And the user adds the iPhone to the basket
    Then the basket should show the new iPhone added
