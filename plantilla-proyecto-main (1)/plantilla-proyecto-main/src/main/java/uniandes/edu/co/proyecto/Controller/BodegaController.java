package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;




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

    // Delete Bodega by ID
    // Delete Bodega by ID
    @DeleteMapping("/bodega/delete")
    public ResponseEntity<String> deleteBodega(@RequestBody Map<String, Object> body) {
        try {
            Integer idBodega = (Integer) body.get("id_bodega");

            if (idBodega == null) {
                return new ResponseEntity<>("id_bodega is required", HttpStatus.BAD_REQUEST);
            }

            bodegaRepository.eliminarBodega(idBodega);
            return ResponseEntity.status(HttpStatus.OK).body("Bodega eliminada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la Bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

// Endpoint to get the occupancy percentage of bodegas in a specific branch (Sucursal)@PostMapping("/bodega/ocupacion")
@PostMapping("/bodega/ocupacion")
public ResponseEntity<?> getOcupacionBodegas(@RequestBody Map<String, Object> body) {
    try {
        List<Integer> listaProductos = (List<Integer>) body.get("productos");
        Integer idSucursal = (Integer) body.get("id_sucursal");

        if (listaProductos == null || listaProductos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La lista de productos es requerida.");
        }

        Collection<Map<String, Object>> resultados = bodegaRepository.obtenerOcupacionBodegasPorProductos(idSucursal, listaProductos);
        return ResponseEntity.ok(resultados);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el porcentaje de ocupación: " + e.getMessage());
    }
}

//comentaro para commit?

}
