@ignore
Feature: Delete a user (reusable cleanup)

  # Limpieza al final de cada escenario: no valida el status porque el escenario pudo haber eliminado ya al usuario
  Scenario: Delete the given user if it still exists
    Given url baseUrl
    And path 'user', username
    When method delete
