EJERCICIO 2 - PRUEBAS DE API REST SOBRE PETSTORE
================================================

Pruebas automatizadas de la API de usuarios de PetStore (https://petstore.swagger.io/), en el
orden del enunciado:

  1. Crear un usuario.
  2. Buscar el usuario creado.
  3. Actualizar el nombre y el correo del usuario.
  4. Buscar el usuario actualizado.
  5. Eliminar el usuario.

Cada caso es un escenario independiente: se puede ejecutar solo, con su tag.


1. TECNOLOGÍAS Y VERSIONES
--------------------------
  - Java JDK 17 (probado con Eclipse Temurin 17.0.19)
  - Gradle 8.14.6, incluido en el Gradle Wrapper (no hace falta instalarlo)
  - Karate 1.5.2 (io.karatelabs:karate-junit5)
  - JUnit 5.11.4 (JUnit Platform 1.11.4)
  - API bajo prueba: https://petstore.swagger.io/v2


2. REQUISITOS
-------------
  - JDK 17 instalado, con la variable JAVA_HOME apuntando a él o con "java" en el PATH.
  - Conexión a internet: la primera ejecución descarga Gradle y las dependencias, y las pruebas
    consumen la API pública.
  - No se necesita navegador.


3. EJECUCIÓN PASO A PASO
------------------------
  1) Abrir una terminal en esta carpeta (02-api-petstore-karate).

  2) Ejecutar las pruebas:

       Windows (PowerShell o CMD):   .\gradlew.bat clean test
       Linux o macOS:                ./gradlew clean test

     La primera ejecución puede tardar unos minutos porque descarga Gradle y las dependencias.
     Después, la ejecución completa tarda unos 10 segundos.

  3) Verificar el resultado: la consola muestra "scenarios: 5 | passed: 5 | failed: 0" y termina
     con "BUILD SUCCESSFUL".

  Opciones:

     Un solo caso, por su tag:
       Windows (PowerShell):   .\gradlew.bat clean test "-Dkarate.options=--tags @createUser"
       Linux o macOS:          ./gradlew clean test "-Dkarate.options=--tags @createUser"

       Tags disponibles:
         @createUser       Crear un usuario
         @getUser          Buscar el usuario creado
         @updateUser       Actualizar el nombre y el correo del usuario
         @getUpdatedUser   Buscar el usuario actualizado
         @deleteUser       Eliminar el usuario
         @petstoreUser     Todos los escenarios

     Ambiente (por defecto dev, el sandbox público de PetStore):
       Windows (PowerShell):   .\gradlew.bat clean test "-Dkarate.env=dev"
       Linux o macOS:          ./gradlew clean test -Dkarate.env=dev

     Nota: en PowerShell las propiedades -D deben ir entre comillas; sin comillas PowerShell
     corta el argumento en el punto y Gradle falla.


4. REPORTES
-----------
  Cada ejecución genera en build/:
    - build/karate-reports/karate-summary.html   Reporte HTML de Karate: escenarios, pasos y el
                                                 request y el response completos de cada petición.
    - build/karate-reports/*.json                Resultado en formato Cucumber JSON.
    - build/karate-reports/*.xml                 Resultado en formato JUnit XML.
    - build/karate.log                           Todas las peticiones y respuestas en texto plano.

  En el reporte HTML, los pasos "When method ..." tienen el número de línea en azul: al hacer clic
  sobre la fila se despliegan el request y el response, con headers, body y tiempo de respuesta.

  La carpeta evidencias/ contiene la ejecución entregada:
    - evidencias/karate-reports/               Reporte completo; abrir karate-summary.html.
    - evidencias/peticiones-y-respuestas.txt   Todas las peticiones y respuestas en texto plano.
    - evidencias/capturas/                     Resumen del reporte, detalle de los escenarios y un
                                               request con su response desplegados.


5. DATOS DE PRUEBA
------------------
  Los datos no están escritos en los escenarios:
    - data/unique-user.js     Genera en cada escenario un username, un id y correos únicos, para
                              no chocar con otros usuarios del sandbox público.
    - data/user-create.json   Plantilla del usuario a crear.
    - data/user-update.json   Campos que cambia la actualización: nombre, apellido y correo.
    - schemas/*.json          Estructura esperada del usuario y de las respuestas de la API.

  Todas las rutas son relativas a src/test/java/co/com/sofka/petstore/user/.
  Cada escenario elimina su usuario al terminar, aunque una validación falle.


  Las entradas, salidas, validaciones y variables de cada caso están resumidas al final de
  conclusiones.txt.


6. ESTRUCTURA DEL PROYECTO
--------------------------
  src/test/java/
    karate-config.js               URL base por ambiente, timeouts y headers comunes.
    logback-test.xml               Nivel de detalle del log: consola resumida y archivo completo.
    co/com/sofka/petstore/
      PetstoreTest.java            Runner JUnit 5: ejecuta los features y genera los reportes.
      user/
        user-crud.feature          Los 5 escenarios, uno por caso.
        common/                    Llamadas reutilizables (crear, actualizar y eliminar) para las
                                   precondiciones y la limpieza; tienen @ignore y no se ejecutan solas.
        data/                      Datos de prueba.
        schemas/                   Esquemas de validación.
