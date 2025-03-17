## Spring Boot: Actividad integradora

### Presentación del requerimiento:

Una casa de electricidad solicitó crear su sistema de control de catálogo de su mercadería. Inicialmente, no necesita manejar stock de los artículos. Solamente tener un catálogo de lo disponible en su comercio.


* **Entidades:** El mismo deberá contemplar al menos las siguientes entidades:
    * `Usuario`
        * Existirán 2 roles, ADMIN y USER. Por defecto cualquier usuario será USER.
        * Definir 1 usuario como administrador para gestiones generales desde la base de datos.
        * **Atributos a contemplar para un registro:**
            * `id` : Autogenerado. Tipo `UUID`.
            * `email` (correo electrónico): Campo obligatorio. No pueden existir correos electrónicos repetidos. Tipo `String`.
            * `nombre` : Campo obligatorio. Tipo `String`.
            * `apellido` : Campo obligatorio. Tipo `String`.
            * `password` : Campo obligatorio. Tipo `String` con encriptación `BCrypt`.
            * `rol` : Tipo `Rol`. Valor por defecto `USER`.
    * `Rol`
        * Será de tipo `Enum`. Valores `ADMIN` y `USER`
    * `Fabrica`
        * **Atributos a contemplar:**
            * `id` : Autogenerado. Tipo `UUID`.
            * `nombre` : Campo obligatorio. Tipo `String`.
    * `Articulo`
        * **Atributos a contemplar:**
            * `id` : Autogenerado. Tipo `UUID`.
            * `nro` : Código de identificación interno de un producto. Código correlativo único y autogenerado. Debe iniciar en 1, y si se agrega otro artículo a la lista sería 2… y así sucesivamente. Tipo `Integer`.
            
                >**Tip:** Para generar un código correlativo único y autogenerado, puedes usar la clase AtomicInteger. Esta clase permite manejar números de forma segura en entornos concurrentes. Puedes inicializarla con 1 y usar incrementAndGet() para asignar cada nuevo número de artículo de forma automática.
                >
                >Ejemplo de inicialización y declaración:
                >~~~
                >private static final AtomicInteger atomicInteger = new AtomicInteger(0);
                >private Integer nro;
                >~~~
                >
            * `nombre` : Campo obligatorio. Tipo `String`.
            * `descripcion` : Campo obligatorio. Tipo `String`.
            * `fabrica` : Representará un dato de tipo `Fabrica`, por lo que deberá establecerse la relación correspondiente.

* **Repositorios:** Cada clase del tipo entidad deberá tener asociado el repositorio correspondiente.

* **Servicios:** Cada clase del tipo entidad deberá tener asociado su propio servicio.

---
###  **Plazo y funcionalidades de la Primera Etapa**

* **Duración:** 6 sesiones.

En esta primera etapa, el sistema deberá permitir:

* **Registro de nuevos usuarios:** Por defecto, los usuarios se registrarán con el rol de `USER`.

* **Alta de artículos:** Inicialmente, cualquier usuario podrá agregar nuevos artículos al sistema.

* **Modificación de artículos:** En un primer momento, cualquier usuario podrá editar la información de los artículos existentes.

* **Listado de artículos:** Todos los usuarios registrados tendrán acceso al catálogo completo de artículos disponibles.

En la base de datos deberán existir:

* Al menos 2 usuarios registrados: uno con rol de `ADMIN` (rol asignado desde la BBDD) y otro con rol de `USER`.

* Al menos 10 artículos registrados.

---
###  **Funcionalidades Adicionales (No requeridos como obligatorios para primera etapa)**

Una vez que hayas cumplido con los requisitos iniciales, puedes considerar incorporar estas mejoras al sistema:

* **Restricción de permisos:** Solo los administradores podrán dar de alta o modificar artículos.

* **Carga de imágenes:** Posibilidad de adjuntar imágenes a los artículos.

* **Vista previa de artículos:** En la página principal, se mostrarán tarjetas con las imágenes de los artículos disponibles. Sin embargo, solo los usuarios registrados podrán acceder a la información completa.

* **Eliminación de artículos:** Solo los administradores podrán eliminar artículos.

* **Edición de perfil:** Cada usuario podrá modificar la información de su propio perfil.

---

© 2025 | Desarrollado por [Fernando Noguera](https://www.linkedin.com/in/jfnoguerab/)