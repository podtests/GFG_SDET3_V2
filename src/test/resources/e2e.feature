
Feature: Verify Whether EndToEnd testcases works

  Background: PreSteps to make sure user is on right state
    Given User is already loggedIn with credentials as "akhiljda@gmail.com" and "Password"

    @JIRA-3213
    Scenario: Add items1 to cart successfully
      Given User clicks on Item "Nike air zoom pegasus 35"
      When User selects the item options size as "L" and color as "Green"
      And User clicks on AddToCart
      # Then Item should get added to the cart

    Scenario: Add items2 to cart successfully
      Given User clicks on Item "abc"

    Scenario: Add items3 to cart successfully
      Given User clicks on Item "def"

    @Sanity
    Scenario Outline:
      Given User clicks on Item "Nike air zoom pegasus 35"

      @Sanity
      Examples:
      | col1 | col2 |
      | 1    | 2    |

      @Regression
      Examples:
        | col1 | col2 |
        | 3    | 4    |