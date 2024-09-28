package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.model.Producto;
import uniandes.edu.co.proyecto.repository.ProductoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Create a new Producto
    @PostMapping("/producto/new/save")
    public ResponseEntity<String> createProducto(@RequestBody Producto producto) {
        try {
            // Ensure the expiracion is in LocalDate format if not already
            LocalDate expiracion = producto.getExpiracion();
            if (expiracion == null) {
                return new ResponseEntity<>("Fecha de expiración es obligatoria", HttpStatus.BAD_REQUEST);
            }

            productoRepository.insertarProducto(producto.getId_producto(), producto.getNombre(), producto.getCodigobarras(), 
                    producto.getPrecioventa(), producto.getPresentacion(), producto.getCantidadpresentacion(), 
                    producto.getUnidadmedida(), expiracion, producto.getId_categoria());
            return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el Producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/producto/search")
    public ResponseEntity<?> getProductoByIdOrNombre(@RequestBody Map<String, Object> body) {
        try {
            Integer id = (Integer) body.get("id");
            String nombre = (String) body.get("nombre");

            // Check if both fields are null
            if (id == null && nombre == null) {
                return new ResponseEntity<>("Either id or nombre must be provided", HttpStatus.BAD_REQUEST);
            }

            Producto producto;

            // Search by id if provided
            if (id != null) {
                producto = productoRepository.findById(id).orElse(null);
            } else {
                // Otherwise, search by nombre
                producto = productoRepository.findByNombre(nombre);
            }

            // Check if the product was found
            if (producto != null) {
                return ResponseEntity.ok(producto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }




    // Update a Producto by its codigo de barras
    @PutMapping("/producto/update")
    public ResponseEntity<String> updateProducto(@RequestBody Map<String, Object> body) {
        try {
            String codigobarras = (String) body.get("codigobarras");
            String nombre = (String) body.get("nombre");
            Double precioventa = (Double) body.get("precioventa");
            String presentacion = (String) body.get("presentacion");
            Integer cantidadpresentacion = (Integer) body.get("cantidadpresentacion");
            String unidadmedida = (String) body.get("unidadmedida");
            String expiracionStr = (String) body.get("expiracion");
            LocalDate expiracion;

            try {
                expiracion = LocalDate.parse(expiracionStr);  // Parse String to LocalDate
            } catch (DateTimeParseException ex) {
                return new ResponseEntity<>("Fecha de expiración inválida", HttpStatus.BAD_REQUEST);
            }

            Integer id_categoria = (Integer) body.get("id_categoria");

            productoRepository.actualizarProducto(codigobarras, nombre, precioventa, presentacion, 
                    cantidadpresentacion, unidadmedida, expiracion, id_categoria);
            return ResponseEntity.status(HttpStatus.OK).body("Producto actualizado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el Producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}