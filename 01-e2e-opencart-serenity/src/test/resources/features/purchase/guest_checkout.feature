@guestCheckout
Feature: Guest checkout
  As a visitor of the online store
  I want to buy products without creating an account
  So that I can complete my purchase quickly

  Scenario: A guest buys two products and receives the order confirmation
    Given Daniel is browsing the online store
    When they add the following products to the cart
      | Sony VAIO               |
      | Samsung Galaxy Tab 10.1 |
    And they review their shopping cart
    Then their cart should contain the products they added
    When they check out as a guest with the following details
      | firstName | lastName      | email                      | telephone  | address          | city     | postcode | country  | region    |
      | Daniel    | Herrera Gomez | daniel.herrera@example.com | 3001234567 | Calle 10 # 20-30 | Medellin | 050001   | Colombia | Antioquia |
    And they choose the "Flat Shipping Rate" delivery method
    And they pay with "Cash On Delivery" accepting the terms and conditions
    And they confirm the order
    Then they should see the order confirmation "Your order has been placed!"
