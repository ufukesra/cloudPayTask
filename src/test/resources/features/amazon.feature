Feature: Product Search

  Scenario: User should be able to search a product in amazon search box and add it to basket
    Given User goes to landing page and search 'Dell Laptop'
    When Refines '4 GB' on the left filter panel
    And Select the product '2020 Newest Dell Inspiron 15 3000 PC Laptop'
    When Check the price is '$299.66'
    And Adds this laptop to the basket
    When Search a 'monitor' and add it to basket
    Then Verify if the total price is match in basket
