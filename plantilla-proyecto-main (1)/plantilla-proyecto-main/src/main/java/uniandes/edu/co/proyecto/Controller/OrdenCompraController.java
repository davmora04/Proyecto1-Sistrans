package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.Date;

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
}
