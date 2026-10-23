@ignore
Feature: Update a user (reusable)

  # Precondición de otros escenarios: recibe en "user" el usuario completo con los datos nuevos
  Scenario: Replace the data of the given user
    Given url baseUrl
    And path 'user', user.username
    And request user
    When method put
    Then status 200
