# Parte B · Automatización funcional

Solución de los ejercicios de `prueba-tecnica-v3.pdf` (Prueba Técnica).

| Carpeta | Ejercicio | Herramientas |
|---|---|---|
| [`01-e2e-opencart-serenity`](01-e2e-opencart-serenity/) | Ejercicio 1 · Prueba E2E de compra en OpenCart | Serenity BDD 5.4.1, Screenplay, Cucumber, Selenium, Gradle |
| [`02-api-petstore-karate`](02-api-petstore-karate/) | Ejercicio 2 · Pruebas de API REST sobre PetStore | Karate 1.5.2, Gradle |

## Requisitos

- JDK 17, con la variable `JAVA_HOME` apuntando a él o con `java` en el `PATH`.
- Google Chrome, para el Ejercicio 1.
- Conexión a internet: las pruebas usan los sitios públicos de OpenCart y PetStore, y la primera ejecución descarga Gradle y las dependencias.

No hace falta instalar Gradle: cada carpeta trae su Gradle Wrapper.

## Cómo ejecutar las pruebas

### 1. Clonar el repositorio

```
git clone https://github.com/daherrerago26/ParteB-Automatizacion-Funcional-Sofka.git
```

En Windows conviene clonar en una ruta corta (por ejemplo `C:\pruebas`): algunas rutas del proyecto tienen más de 100 caracteres y Windows limita la ruta completa a 260.

Todos los comandos siguientes se escriben desde la carpeta raíz del repositorio (`ParteB-Automatizacion-Funcional-Sofka`).

### 2. Ejecutar solo el Ejercicio 1 · Prueba E2E de compra en OpenCart

```
cd 01-e2e-opencart-serenity
.\gradlew.bat clean test        # Windows
./gradlew clean test            # Linux o macOS
```

- Se abre Chrome y recorre la compra completa como invitado. El resultado esperado es `BUILD SUCCESSFUL`.
- Sin ventana del navegador: `.\gradlew.bat clean test "-Dheadless.mode=true"`.
- Reporte: `target/site/serenity/index.html` (completo, con capturas) y `target/site/serenity/serenity-summary.html` (resumen).

### 3. Ejecutar solo el Ejercicio 2 · Pruebas de API REST sobre PetStore

```
cd 02-api-petstore-karate
.\gradlew.bat clean test        # Windows
./gradlew clean test            # Linux o macOS
```

- El resultado esperado es `scenarios: 5 | passed: 5 | failed: 0` y `BUILD SUCCESSFUL`.
- Un solo caso, por su tag: `.\gradlew.bat clean test "-Dkarate.options=--tags @createUser"`.
- Reporte: `build/karate-reports/karate-summary.html`, con el request y el response de cada petición.

### 4. Ejecutar los dos ejercicios juntos

Un solo comando ejecuta el Ejercicio 1 y luego el Ejercicio 2, y vuelve a la carpeta raíz:

```
# Windows (PowerShell)
cd 01-e2e-opencart-serenity; .\gradlew.bat clean test; cd ..\02-api-petstore-karate; .\gradlew.bat clean test; cd ..

# Linux o macOS
(cd 01-e2e-opencart-serenity && ./gradlew clean test); (cd 02-api-petstore-karate && ./gradlew clean test)
```

- El Ejercicio 2 se ejecuta aunque el Ejercicio 1 falle.
- Cada ejercicio termina con su propio `BUILD SUCCESSFUL` y deja su reporte en su carpeta, en las mismas rutas de los puntos 2 y 3.
- Para ejecutar el Ejercicio 1 sin ventana del navegador, agregar `"-Dheadless.mode=true"` después de su `clean test`.

En PowerShell, las propiedades `-D` van entre comillas; sin comillas, PowerShell corta el argumento en el punto y Gradle falla.

## Documentación de cada ejercicio

Cada carpeta tiene:

- `readme.txt`: tecnologías y versiones, y el paso a paso de ejecución.
- `conclusiones.txt`: hallazgos y conclusiones del ejercicio.
- `evidencias/`: reportes y capturas de la ejecución entregada, para revisarlos sin ejecutar nada.
