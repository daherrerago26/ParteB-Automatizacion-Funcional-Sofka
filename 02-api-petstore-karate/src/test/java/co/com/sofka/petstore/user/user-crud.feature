@petstoreUser
Feature: User management in the PetStore API
  As a consumer of the PetStore API
  I want to create, search, update and delete users
  So that I can manage the users of the store

  Background:
    * url baseUrl
    # Datos únicos por escenario: username, id y correos
    * def identity = call read('data/unique-user.js')
    * def userId = identity.userId
    * def username = identity.username
    * def email = identity.email
    * def updatedEmail = identity.updatedEmail
    # Usuario a crear y el mismo usuario con el nombre y el correo nuevos (el username y el id no cambian)
    * def newUser = read('data/user-create.json')
    * def updatedUser = karate.merge(newUser, read('data/user-update.json'))
    * def userSchema = read('schemas/user-schema.json')
    * def apiResponseSchema = read('schemas/api-response-schema.json')
    # Limpieza: el usuario se elimina al final de cada escenario aunque una validación falle
    * configure afterScenario = function(){ karate.call('common/delete-user.feature', { username: username }) }

  @createUser
  Scenario: Create a user
    Given path 'user'
    And request newUser
    When method post
    Then status 200
    And match response == apiResponseSchema
    And match response.code == 200
    # La API devuelve en "message" el id del usuario creado
    And match response.message == '' + newUser.id

  @getUser
  Scenario: Search the created user
    * call read('common/create-user.feature') { user: '#(newUser)' }
    Given path 'user', username
    When method get
    Then status 200
    And match response == userSchema
    And match response == newUser

  @updateUser
  Scenario: Update the name and email of the user
    * call read('common/create-user.feature') { user: '#(newUser)' }
    Given path 'user', username
    And request updatedUser
    When method put
    Then status 200
    And match response == apiResponseSchema
    And match response.code == 200
    And match response.message == '' + updatedUser.id

  @getUpdatedUser
  Scenario: Search the updated user
    * call read('common/create-user.feature') { user: '#(newUser)' }
    * call read('common/update-user.feature') { user: '#(updatedUser)' }
    Given path 'user', username
    When method get
    Then status 200
    And match response == userSchema
    And match response == updatedUser
    # El nombre y el correo cambiaron; el id y el username se mantienen
    And match response contains { firstName: '#(updatedUser.firstName)', lastName: '#(updatedUser.lastName)', email: '#(updatedEmail)' }
    And match response contains { id: '#(newUser.id)', username: '#(newUser.username)' }

  @deleteUser
  Scenario: Delete the user
    * call read('common/create-user.feature') { user: '#(newUser)' }
    Given path 'user', username
    When method delete
    Then status 200
    And match response == apiResponseSchema
    And match response.code == 200
    # La API devuelve en "message" el username eliminado
    And match response.message == username
    # Validación adicional: el usuario eliminado ya no se puede consultar
    Given path 'user', username
    When method get
    Then status 404
    And match response.message == 'User not found'
