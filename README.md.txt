API RESTful con Spring Boot

Este proyecto contiene una serie de ejercicios prácticos para implementar y probar una API RESTful utilizando Spring Boot. Los ejercicios incluyen desde la creación de un endpoint simple hasta la implementación de pruebas unitarias con StepVerifier.

📌 Requisitos

Java 11 o superior

Spring Boot

Postman (para pruebas manuales)

IntelliJ IDEA o cualquier otro IDE compatible con Spring Boot

📌 Ejercicios

1️⃣ Crear un Endpoint Simple

Descripción: Crear un endpoint en un controlador REST que devuelva el mensaje "¡Hola, API RESTful!" cuando el usuario acceda a /api/saludo.

📄 Código esperado:

@RestController
@RequestMapping("/api")
public class SaludoController {
    @GetMapping("/saludo")
    public String obtenerSaludo() {
        return "¡Hola, API RESTful!";
    }
}

🔹 Prueba:

GET: http://localhost:8080/api/saludo

Salida esperada: "¡Hola, API RESTful!"

2️⃣ Crear un CRUD Básico de Productos

Descripción: Implementar una API RESTful para manejar un CRUD (Crear, Leer, Actualizar, Eliminar) de productos.

📄 Modelo esperado:

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private String id;
    private String nombre;
    private double precio;
}

🔹 Endpoints esperados:

POST /api/productos → Agregar un producto.

GET /api/productos → Listar todos los productos.

GET /api/productos/{id} → Obtener un producto por ID.

PUT /api/productos/{id} → Actualizar un producto.

DELETE /api/productos/{id} → Eliminar un producto.

🔹 Prueba:

POST: Agregar un producto con Postman.

GET: Consultar todos los productos.

3️⃣ Implementar Internacionalización (i18n)

Descripción: Modificar el Ejercicio 1 para que el mensaje de saludo pueda ser traducido según el idioma solicitado en la URL (?lang=es o ?lang=en).

📄 Archivos de propiedades:

messages_es.properties

saludo=¡Hola, API RESTful en Español!

messages_en.properties

saludo=Hello, RESTful API in English!

📄 Código esperado:

@RestController
@RequestMapping("/api")
public class SaludoController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/saludo")
    public String obtenerSaludo(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("saludo", null, locale);
    }
}

🔹 Prueba:

GET: http://localhost:8080/api/saludo?lang=es → "¡Hola, API RESTful en Español!"

GET: http://localhost:8080/api/saludo?lang=en → "Hello, RESTful API in English!"

4️⃣ Crear un Endpoint Reactivo con WebFlux

Descripción: Modificar el CRUD de productos para que la lista de productos sea manejada de manera reactiva usando Flux.

📄 Código esperado:

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @GetMapping
    public Flux<Producto> listarProductos() {
        return Flux.just(
            new Producto("1", "Laptop", 1200.0),
            new Producto("2", "Mouse", 25.0),
            new Producto("3", "Teclado", 45.0)
        );
    }
}

🔹 Prueba:

GET: http://localhost:8080/api/productos

Salida esperada: JSON con los productos en formato reactivo.

5️⃣ Implementar Pruebas con StepVerifier

Descripción: Crear una prueba unitaria con StepVerifier para validar que el endpoint de productos devuelve los valores esperados.

📄 Código esperado:

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoControllerTest {
    @Autowired
    private ProductoController productoController;

    @Test
    public void testListaProductos() {
        Flux<Producto> productos = productoController.listarProductos();

        StepVerifier.create(productos)
            .expectNextMatches(p -> p.getNombre().equals("Laptop"))
            .expectNextMatches(p -> p.getNombre().equals("Mouse"))
            .expectNextMatches(p -> p.getNombre().equals("Teclado"))
            .verifyComplete();
    }
}

🔹 Prueba:

Ejecutar la prueba con JUnit y StepVerifier.

Salida esperada: La prueba debe pasar correctamente.

🚀 Ejecución del Proyecto

Clonar el repositorio:

git clone <URL_DEL_REPOSITORIO>

Navegar a la carpeta del proyecto:

cd nombre-del-proyecto

Compilar y ejecutar la aplicación:

mvn spring-boot:run

Probar los endpoints usando Postman o el navegador.

📌 Tecnologías Utilizadas

Spring Boot (para el desarrollo de la API RESTful)

Spring WebFlux (para la programación reactiva)

Spring Security (para autenticación y seguridad, si se desea extender)

JUnit 5 y StepVerifier (para pruebas unitarias y reactivas)

📄 Licencia

Este proyecto es de código abierto y se distribuye bajo la licencia MIT.

📌 ¡Feliz programación! 🚀