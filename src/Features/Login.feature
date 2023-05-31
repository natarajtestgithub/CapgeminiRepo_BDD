Feature: Create Order

  Scenario: User registers and creates an order
    Given User is on the landing page
    When User registers on the page
    Then User should be successfully registered
    And User adds items to the cart
    Then User should see the items added to the cart and confirm order
