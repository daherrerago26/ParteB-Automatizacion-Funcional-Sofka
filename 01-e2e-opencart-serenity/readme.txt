EJERCICIO 1 - PRUEBA E2E DE COMPRA EN OPENCART
===============================================

Prueba funcional automatizada (E2E) del flujo de compra en http://opencart.abstracta.us/:

  1. Agregar dos productos al carrito (Sony VAIO y Samsung Galaxy Tab 10.1).
  2. Visualizar el carrito y validar que contiene los dos productos.
  3. Completar el checkout como invitado ("Guest Checkout").
  4. Finalizar la compra hasta la confirmación "Your order has been placed!".


1. TECNOLOGÍAS Y VERSIONES
--------------------------
  - Java JDK 17 (probado con Eclipse Temurin 17.0.19)
  - Gradle 8.14.6, incluido en el Gradle Wrapper (no hace falta instalarlo)
  - Serenity BDD 5.4.1 con el patrón Screenplay
  - Cucumber 8.0.4 con Gherkin
  - Selenium WebDriver 4.51.0 (Selenium Manager descarga el chromedriver automáticamente)
  - JUnit Platform 6.1.3
  - Google Chrome (probado con la versión 155)


2. REQUISITOS
-------------
  - JDK 17 instalado, con la variable JAVA_HOME apuntando a él o con "java" en el PATH.
  - Google Chrome instalado.
  - Conexión a internet: la primera ejecución descarga Gradle y las dependencias, y la prueba usa el sitio en línea.


3. EJECUCIÓN PASO A PASO
------------------------
  1) Abrir una terminal en esta carpeta (01-e2e-opencart-serenity).

  2) Ejecutar la prueba:

       Windows (PowerShell o CMD):   .\gradlew.bat clean test
       Linux o macOS:                ./gradlew clean test

     La primera ejecución puede tardar unos minutos porque descarga Gradle y las dependencias.
     Se abre una ventana de Chrome que recorre el flujo de compra completo.

  3) Verificar el resultado: la consola termina con "BUILD SUCCESSFUL" y el resumen
     "SERENITY TESTS: SUCCESS".

  Opciones:

     Sin ventana del navegador (modo headless):
       Windows (PowerShell):   .\gradlew.bat clean test "-Dheadless.mode=true"
       Linux o macOS:          ./gradlew clean test -Dheadless.mode=true

     Solo el escenario de compra, por su tag:
       Windows (PowerShell):   .\gradlew.bat clean test "-Dcucumber.filter.tags=@guestCheckout"
       Linux o macOS:          ./gradlew clean test -Dcucumber.filter.tags=@guestCheckout

     Nota: en PowerShell las propiedades -D deben ir entre comillas; sin comillas PowerShell
     corta el argumento en el punto y Gradle falla.


4. REPORTES
-----------
  Cada ejecución genera:
    - target/site/serenity/index.html            Reporte completo de Serenity, con una captura por acción.
    - target/site/serenity/serenity-summary.html Resumen de una página.

  La carpeta evidencias/ contiene la ejecución entregada:
    - evidencias/serenity-summary.html   Resumen de una página (se abre directamente en el navegador).
    - evidencias/reporte-serenity.zip    Reporte completo; descomprimir y abrir index.html.
    - evidencias/capturas/               Capturas clave: producto agregado, carrito, datos de facturación,
                                         método de pago y pedido confirmado.


5. DATOS DE PRUEBA
------------------
  Los productos y los datos del cliente invitado están en el feature, no en el código Java:
    src/test/resources/features/purchase/guest_checkout.feature

  Para usar otros productos o cambiar los datos del cliente basta con editar las tablas del feature.
  La URL del sitio está en src/test/resources/serenity.conf (webdriver.base.url).


6. ESTRUCTURA DEL PROYECTO
--------------------------
  src/test/java/co/com/sofka/opencart/
    runners/          CucumberTestSuite: ejecuta los features con Cucumber sobre JUnit Platform.
    hooks/            Prepara el escenario de Screenplay antes de cada escenario.
    stepdefinitions/  Une los pasos del Gherkin con las tareas; no tiene lógica.
    tasks/            Tareas de negocio: abrir la tienda, agregar producto, ver carrito,
                      checkout como invitado, datos de facturación, envío, pago y confirmación.
    interactions/     Interacciones propias: marcar una casilla y esperar las peticiones AJAX pendientes.
    questions/        Preguntas sobre el estado de la página: productos del carrito y mensaje de confirmación.
    userinterfaces/   Targets (localizadores) de cada página.
    models/           Modelo del cliente invitado.
  src/test/resources/
    features/         Escenario en Gherkin.
    serenity.conf     Configuración de Serenity y de Chrome.


7. NOTA SOBRE LA URL
--------------------
  El enunciado indica http://opencart.abstracta.us/. La prueba abre el mismo sitio por
  https://opencart.abstracta.us/ porque Chrome cambia por su cuenta la URL http a https y, cuando
  el sitio tarda más de 5 segundos en responder por HTTPS, vuelve a http dejando la página en blanco.
  El detalle está en conclusiones.txt.
