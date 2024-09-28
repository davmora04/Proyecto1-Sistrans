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

import uniandes.edu.co.proyecto.model.Proveedores;
import uniandes.edu.co.proyecto.repository.ProveedoresRepository;

@Controller
public class ProveedoresController {
    @Autowired
    private ProveedoresRepository proveedoresRepository;

    @GetMapping("/proveedores")
    public ResponseEntity<Collection<Proveedores>> proveedores() {
        try {
            Collection<Proveedores> proveedores = proveedoresRepository.findAll();
            return ResponseEntity.ok(proveedores);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/proveedores/new/save") 
    public ResponseEntity<?> createProveedor(@RequestBody Proveedores proveedor) {
        try {
            proveedoresRepository.insertarProveedor(proveedor.getId_proveedor(), proveedor.getnit(), proveedor.getNombre(), proveedor.getDireccion(), proveedor.getNombre_contacto(), proveedor.getTelefono_contacto());
            return ResponseEntity.status(HttpStatus.CREATED).body("Proveedor creado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el proveedor: " , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/proveedores/{id}/update/save") 
    public ResponseEntity<?> updateProveedor(@RequestBody Proveedores proveedor, @PathVariable("id")Integer id) {
        try {
            proveedoresRepository.actualizarProveedor(id, proveedor.getnit(), proveedor.getNombre(), proveedor.getDireccion(), proveedor.getNombre_contacto(), proveedor.getTelefono_contacto());
            return ResponseEntity.status(HttpStatus.CREATED).body("Proveedor actualizado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar a el proveedor: " , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

