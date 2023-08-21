#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@Books
Feature: Simple book API GET


  @Get_All_Books_001
  Scenario Outline: Check if user able to retrieve  list of  books with "<dataKey>" endpoint
    Given User creates GET Request for the  API endpoint with "<dataKey>"
    When User sends HTTPS get Request with "<dataKey>"
    Then User receives Status Code  with response body for endpoint "<dataKey>"

    Examples: 
      | dataKey |
      | Valid   |

  @Get_All_Books_002
  Scenario Outline: Check if user able to retrieve  list of  books with "<dataKey>" endpoint for type query parameters
    Given User creates GET Request for the  API endpoint with "<dataKey>"
    When User sends HTTPS get Request with for type query parameter "<dataKey>"
    Then User receives Status Code  with response body for type "<dataKey>"

    Examples: 
      | dataKey     |
      | fiction     |
      | non-fiction |

  @Get_All_Books_003
  Scenario Outline: Check if user able to retrieve  list of  books with "<dataKey>" endpoint for available query parameters
    Given User creates GET Request for the  API endpoint with "<dataKey>"
    When User sends HTTPS get Request with for available query parameter "<dataKey>"
    Then User receives Status Code  with response body for available "<dataKey>"

    Examples: 
      | dataKey |
      | true    |
      | false   |

  @Get_Single_Book_001
  Scenario Outline: Check if user able to retrieve a single book with book ID and API
    Given User creates GET Request with the single book id and  API "<dataKey>"
    When User sends HTTPS  get Request for single book with bookid "<dataKey>"
    Then User receives Status Code  with response body for single book with  bookid "<dataKey>"

    Examples: 
      | dataKey |
      | Valid   |
      | Invalid |

  