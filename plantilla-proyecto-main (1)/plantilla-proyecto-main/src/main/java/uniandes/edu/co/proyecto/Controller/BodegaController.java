package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uniandes.edu.co.proyecto.model.Bodega;
import uniandes.edu.co.proyecto.repository.BodegaRepository;

@Controller
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    // Endpoint to get all Bodegas
    @GetMapping("/bodega")
    public ResponseEntity<Collection<Bodega>> getAllBodegas() {
        try {
            Collection<Bodega> bodegas = bodegaRepository.findAll();
            return ResponseEntity.ok(bodegas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint to create a new Bodega
    @PostMapping("/bodega/new/save")
    public ResponseEntity<String> createBodega(@RequestBody Bodega bodega) {
        try {
            // Insert Bodega using the custom native query in the repository
            bodegaRepository.insertarBodega(bodega.getId_bodega(), bodega.getNombre(), bodega.getTamano(), bodega.getCantidad_prod(), bodega.getId_sucursal());
            return ResponseEntity.status(HttpStatus.CREATED).body("Bodega creada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la Bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get a Bodega by its ID
    @GetMapping("/bodega/{id}")
    public ResponseEntity<Bodega> getBodegaById(@PathVariable Integer id) {
        try {
            Bodega bodega = bodegaRepository.leerBodega(id);
            if (bodega != null) {
                return ResponseEntity.ok(bodega);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
