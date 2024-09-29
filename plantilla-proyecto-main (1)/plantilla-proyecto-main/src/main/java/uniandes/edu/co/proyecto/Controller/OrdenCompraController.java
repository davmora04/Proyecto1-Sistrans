package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.model.OrdenCompra;
import uniandes.edu.co.proyecto.repository.OrdenCompraRepository;

@RestController
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    // Endpoint para obtener todas las órdenes de compra
    @GetMapping("/ordencompra")
    public ResponseEntity<Iterable<OrdenCompra>> obtenerOrdenesCompra() {
        try {
            Collection<OrdenCompra> ordenesCompra = ordenCompraRepository.findAll();
            return ResponseEntity.ok(ordenesCompra);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para crear una nueva orden de compra
    @PostMapping("/ordencompra/new/save")
    public ResponseEntity<?> crearOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        try {
            // Establecer la fecha de creación como la fecha actual
            // Establecer el estado de la orden como 'vigente'
            ordenCompra.setEstado("vigente"); 

            // Guardar la orden de compra en la base de datos
            ordenCompraRepository.save(ordenCompra);
            return ResponseEntity.status(HttpStatus.CREATED).body("Orden de compra creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la orden de compra.");
        }
    }


    // Método de prueba para verificar si el servidor funciona
    @GetMapping("/test")
    public String test() {
        return "Servidor funcionando correctamente";
    }


     // Método para anular una orden de compra
     @PutMapping("/ordencompra/anular/{id}")
     public ResponseEntity<String> anularOrdenCompra(@PathVariable Integer id) {
         try {
             // Buscar la orden de compra por su ID
             Optional<OrdenCompra> optionalOrdenCompra = ordenCompraRepository.findById(id);
 
             if (optionalOrdenCompra.isPresent()) {
                 OrdenCompra ordenCompra = optionalOrdenCompra.get();
 
                 // Verificar si la orden ya está en estado "entregada"
                 if ("entregada".equalsIgnoreCase(ordenCompra.getEstado())) {
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("No se puede anular una orden de compra que ya ha sido entregada.");
                 }
 
                 // Cambiar el estado de la orden a "anulada"
                 ordenCompra.setEstado("anulada");
                 ordenCompraRepository.save(ordenCompra); // Guardar los cambios
 
                 return ResponseEntity.ok("La orden de compra ha sido anulada exitosamente.");
             } else {
                 return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body("No se encontró la orden de compra con ID: " + id);
             }
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body("Error al anular la orden de compra.");
         }
     }

   // Método para mostrar todas las órdenes de compra
    @GetMapping("/ordencompra/listar")
    public ResponseEntity<Iterable<OrdenCompra>> listarTodasLasOrdenesCompra() {
        try {
            // Obtener todas las órdenes de compra desde la base de datos
            Collection<OrdenCompra> ordenesCompra = ordenCompraRepository.findAll();
            return ResponseEntity.ok(ordenesCompra);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
