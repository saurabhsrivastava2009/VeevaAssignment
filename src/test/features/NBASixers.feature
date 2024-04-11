Feature: NBA Sixers Test

  Scenario: Validate Slide Titles
    Given the user navigates to NBA Sixers Home Page
    And the user captures the titles of all slides
    Then the user verifies the slide titles:
      | 76ers Sweep Pistons in Season Series                       |
      | Maxey Scores Career High, Sixers Top Spurs in Double OT    |
      | Jeff Dowtin Jr. Signs NBA Contract With 76ers              |
      | Iverson to be Honored With Sculpture on 76ers Legends Walk |
      | Get Tickets to Finish Out the 2023-24 Regular Season!      |

  Scenario: Validate Duration
    Given the user navigates to NBA Sixers Home Page
    And the user captures the titles of all slides
    Then the user verifies the slides duration as approx of 8 seconds
