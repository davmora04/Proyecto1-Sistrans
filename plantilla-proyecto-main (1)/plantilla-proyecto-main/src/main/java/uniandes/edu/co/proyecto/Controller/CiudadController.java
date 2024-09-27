package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;


import uniandes.edu.co.proyecto.model.Ciudad;
import uniandes.edu.co.proyecto.repository.CiudadRepository;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class CiudadController {
       @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping("/ciudad")
    public ResponseEntity<Iterable<Ciudad>> ciudad() {
        try {
            Collection<Ciudad> ciudades = ciudadRepository.findAll();
            return ResponseEntity.ok(ciudades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }   

    }
    @PostMapping("/ciudad/new/save")
    public ResponseEntity<?> ciudadGuardar(@RequestBody Ciudad ciudad) {
        try {
            ciudadRepository.insertarCiudad(ciudad.getNombre(), ciudad.getCodigo());
            return ResponseEntity.status(HttpStatus.CREATED).body("ciudad creada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
