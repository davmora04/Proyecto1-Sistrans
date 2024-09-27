package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
            sucursalRepository.insertarSucursal(sucursal.getId(), sucursal.getNombre(), sucursal.getCiudad().getCodigo(), sucursal.getInstalacionM2(), sucursal.getTelefono());
            return ResponseEntity.status(HttpStatus.CREATED).body("Sucursal creada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la sucursal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    }
    
    




