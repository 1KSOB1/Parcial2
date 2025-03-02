package com.parcial3.parcial3;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final List<Producto> productos = new ArrayList<>();

    // 1️⃣ Crear un producto (POST)
    @PostMapping
    public String agregarProducto(@RequestBody Producto producto) {
        productos.add(producto);
        return "Producto agregado correctamente";
    }

    // 2️⃣ Listar todos los productos (GET)
    @GetMapping
    public List<Producto> listarProductos() {
        return productos;
    }

    // 3️⃣ Obtener un producto por ID (GET)
    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable String id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 4️⃣ Actualizar un producto por ID (PUT)
    @PutMapping("/{id}")
    public String actualizarProducto(@PathVariable String id, @RequestBody Producto productoActualizado) {
        Optional<Producto> productoExistente = productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (productoExistente.isPresent()) {
            productos.remove(productoExistente.get());
            productos.add(productoActualizado);
            return "Producto actualizado correctamente";
        }
        return "Producto no encontrado";
    }

    // 5️⃣ Eliminar un producto por ID (DELETE)
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable String id) {
        boolean eliminado = productos.removeIf(p -> p.getId().equals(id));
        return eliminado ? "Producto eliminado correctamente" : "Producto no encontrado";
    }
}
