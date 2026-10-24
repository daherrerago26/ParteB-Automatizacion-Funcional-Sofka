@ignore
Feature: Create a user (reusable)

  # Precondición de otros escenarios: recibe el usuario en la variable "user"
  Scenario: Create the given user
    Given url baseUrl
    And path 'user'
    And request user
    When method post
    Then status 200
