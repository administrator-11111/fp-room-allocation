# Sistema de Gestión de Salas - Paradigma Funcional

Este repositorio contiene la implementación del Ejercicio 2 correspondiente al sistema de asignación de salas. El código ha sido desarrollado en **Kotlin**, aplicando estrictamente el **Paradigma Funcional** según los conceptos del lenguaje núcleo definidos por la taxonomía de Peter Van Roy (2009). 

El sistema evalúa un flujo secuencial de solicitudes de reserva contra un catálogo de salas, respetando la inmutabilidad de los datos, utilizando funciones de orden superior y propagando el estado mediante clausuras léxicas (`fold`), sin recurrir a ciclos imperativos ni a estados mutables globales.

## 📋 Requisitos del Sistema

Para compilar y ejecutar este proyecto, es necesario contar con:

1. **Java Development Kit (JDK):** Versión 8 o superior (Recomendado JDK 11 o 17).
2. **Kotlin:** Compilador de Kotlin instalado (si se ejecuta por consola) o un entorno de desarrollo compatible.
3. **IDE Recomendado:** [IntelliJ IDEA](https://www.jetbrains.com/idea/) (Community o Ultimate), ya que incluye soporte nativo para Kotlin.

## 📁 Estructura del Proyecto

El código fuente se encuentra dentro de la carpeta `src/sistema/salas/` y se divide en tres archivos principales para mantener la separación de preocupaciones:

* `modelos/Modelos.kt`: Contiene los Registros (`data classes` inmutables) que representan las Salas, Solicitudes, y el Estado de Procesamiento.
* `logica/Logica.kt`: Contiene las funciones de orden superior para las validaciones y el motor funcional principal (`procesarSolicitudes`) basado en clausuras.
* `Main.kt`: Contiene el punto de entrada del programa (`main`), el catálogo base de salas, el flujo de solicitudes y la llamada a la ejecución.

## 🚀 Instrucciones de Compilación y Ejecución

### Opción 1: Ejecución mediante IntelliJ IDEA (Recomendado)

1. Abre **IntelliJ IDEA**.
2. Selecciona `File > Open...` y elige la carpeta raíz de este repositorio.
3. En el panel de proyecto (a la izquierda), navega hasta `src/sistema/salas/Main.kt`.
4. Abre el archivo `Main.kt`. Verás un ícono de un triángulo verde (Play) justo al lado de la función `fun main()`.
5. Haz clic en el triángulo verde y selecciona **Run 'MainKt'**.
6. El programa se compilará automáticamente y el reporte final se mostrará en la consola integrada en la parte inferior.

### Opción 2: Ejecución mediante Línea de Comandos (Terminal)

Si prefieres compilar y ejecutar el código directamente desde la terminal utilizando el compilador de Kotlin (`kotlinc`):

1. Abre tu terminal o consola de comandos.
2. Navega hasta el directorio raíz del proyecto (donde se encuentra la carpeta `src`).
3. Ejecuta el siguiente comando para compilar todos los archivos `.kt` y generar un archivo `.jar`:

   ```bash
   kotlinc src/sistema/salas/modelos/Modelos.kt src/sistema/salas/logica/Logica.kt src/sistema/salas/Main.kt -include-runtime -d SistemaSalas.jar

Una vez compilado, ejecuta el archivo `.jar` generado con el siguiente comando:

   ```bash
   java -jar SistemaSalas.jar
  ```
>Nota de transparencia: La estructuración, documentación y optimización del código de este proyecto fueron realizadas con la asistencia de un modelo de Inteligencia Artificial local provisto por Antigravity (https://antigravity.google/).