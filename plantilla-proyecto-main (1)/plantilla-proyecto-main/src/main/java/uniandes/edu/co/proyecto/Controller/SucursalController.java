package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uniandes.edu.co.proyecto.repository.SucursalRepository;
import uniandes.edu.co.proyecto.model.Sucursal;

@Controller
public class SucursalController {
     @Autowired
    private SucursalRepository sucursalRepository;

    @GetMapping("/sucursal")
    public ResponseEntity<Collection<Sucursal>> sucursales() {
        try {
            Collection<Sucursal> sucursales = sucursalRepository.findAll();
            return ResponseEntity.ok(sucursales);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

@PostMapping("/sucursal/new/save")
    public ResponseEntity<?> createSucursal(@RequestBody Sucursal sucursal) {
        try {
            sucursalRepository.insertarSucursal(sucursal.getId_sucursal(), sucursal.getNombre(), sucursal.getCodigo_ciudad(), sucursal.getIntalacionM2(), sucursal.getTelefono());
            return ResponseEntity.status(HttpStatus.CREATED).body("Sucursal creada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la sucursal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/producto/{idOrName}/sucursales")
public ResponseEntity<?> getSucursalesByProducto(@PathVariable String idOrName) {
    try {
        List<Map<String, Object>> sucursales;
        System.out.println("Recibiendo idOrName: " + idOrName);
        
        try {
            Integer idProducto = Integer.parseInt(idOrName);
            System.out.println("Buscando por ID de producto: " + idProducto);
            sucursales = sucursalRepository.findSucursalesByProductoId(idProducto);
        } catch (NumberFormatException e) {
            System.out.println("Buscando por nombre de producto: " + idOrName);
            sucursales = sucursalRepository.findSucursalesByProductoNombre(idOrName);
        }

        // Si se encuentra una lista vacía
        if (sucursales == null || sucursales.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron sucursales con disponibilidad de este producto");
        }

        // Devuelve la lista de sucursales
        return ResponseEntity.ok(sucursales);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
}

    }
    
    




