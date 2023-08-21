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
@tag
Feature: Simple book API Post

  @Post_RegisterAPIclient
  Scenario Outline: Check if user able to successfully register as a API client with  endpoint and request body
    Given User creates Post Request for the  API endpoint with fields from "<sheetName>" with "<dataKey>"
    When User sends HTTPS Post  Request with requestbody with "<dataKey>"
    Then User receives Status Code  with response body for endpoint with "<dataKey>" and  "<sheetName>"

    Examples: 
      | dataKey                  | sheetName |
      | Valid                    | Book      |
      | Post_Missing_Clientname  | Book      |
      | Post_Missing_clientEmail | Book      |

  @Get_All_Books_001
  Scenario Outline: Check if user able to retrieve  list of  books with "<dataKey>" endpoint
    Given User creates GET Request for the  API endpoint with "<dataKey>"
    When User sends HTTPS get Request with "<dataKey>"
    Then User receives Status Code  with response body for endpoint "<dataKey>"

    Examples: 
      | dataKey |
      | Valid   |

  @Post_Order
  Scenario Outline: Check if user able to successfully post a book order with endpoint and request body
    Given User creates Post Request for book order  with fields from "<sheetName>" with "<dataKey>"
    When User sends HTTPS  post Request for post order with "<dataKey>"
    Then User receives Status Code  with response body for post order with "<dataKey>" and  "<sheetName>"

    Examples: 
      | dataKey             | sheetName |
      | PostValid           | Book      |
      | PostInValidEndpoint | Book      |
      | Post_Missing_BookId | Book      |
      | PostInValidBookId   | Book      |

  @Get_SingleOrder002
  Scenario Outline: Check if user able to get single order with "<dataKey>" orderid
    Given Check if user able to  get single order with orderid "<dataKey>"
    When User creates GET Request for the  get single order with orderid "<dataKey>"
    Then User receives Status Code  with response body for  get single order with orderid "<dataKey>"

    Examples: 
      | dataKey |
      | Valid   |
      | Invalid |

  @Get_ALLOrders_005
  Scenario Outline: vCheck if user able to get all  orders
    Given Check if user able to retrieve  list of orders with endpoint "<dataKey>"
    When User creates GET Request for the  get all orders with "<dataKey>"
    Then User receives Status Code  with response body for  get all orders "<dataKey>"

    Examples: 
      | dataKey |
      | Valid   |
      | Invalid |

  @Patch_Order
  Scenario Outline: Check if user able to successfully update a book order with endpoint and request body
    Given User creates Put Request for book order  with fields from "<sheetName>" with "<dataKey>"
    When User sends HTTPS  put Request for book order with "<dataKey>"
    Then User receives Status Code  with response body for put order with "<dataKey>" and  "<sheetName>"

    Examples: 
      | dataKey                 | sheetName |
      | ValidPut_Customername   | Book      |
      | InvalidPut_Customername | Book      |

  @Delete_Order
  Scenario Outline: Check if user able to successfully delete a book order with endpoint and request body
    Given User creates delete Request for book order  with fields with "<dataKey>"
    When User sends HTTPS  delete Request for book order with "<dataKey>"
    Then User receives Status Code  with response body for put order with "<dataKey>"

    Examples: 
      | dataKey |
      | Valid   |
      | Invalid |
