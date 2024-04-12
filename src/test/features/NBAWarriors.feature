@Sanity
Feature: NBA Warriors Tests

  Scenario: Save product details in Text file
    Given the user navigates to NBA Warriors Home Page
    And the user selects Shop and sub-menu as Men
    * the user switches to the Men's Golden State Warriors Gear, Mens Warriors Apparel, Guys Clothing | shop.warriors.com tab
    * the user select the department:Jackets
    Then the user saves the product details in file:jacket_product_details.txt

  Scenario: Count Video Feed
    Given the user navigates to NBA Warriors Home Page
    And the user selects 3 Dots and sub-menu as News & Features
    * the user counts the tiles on section VIDEOS and saves in tileCount
    Then the user verifies the tileCount is 23