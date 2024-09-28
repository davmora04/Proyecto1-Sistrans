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

import uniandes.edu.co.proyecto.model.CategoriaProducto;
import uniandes.edu.co.proyecto.repository.CategoriaProductoRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CategoriaProductoController {
    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @GetMapping("/categoria")
    public ResponseEntity<Collection<CategoriaProducto>> categoriaProducto() {
        try {
            Collection<CategoriaProducto> categoriaProducto = categoriaProductoRepository.findAll();
            return ResponseEntity.ok(categoriaProducto);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/categoria/new/save")
    public ResponseEntity<?> createCategoriaProducto(@RequestBody CategoriaProducto categoriaProducto) {
        try {
            categoriaProductoRepository.insertarCategoria(categoriaProducto.getId_categoria(), categoriaProducto.getCodigo(), categoriaProducto.getNombre(), categoriaProducto.getDescripcion(), categoriaProducto.getCaracteristicas_almacenamiento());
            return ResponseEntity.status(HttpStatus.CREATED).body("CategoriaProducto creado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el CategoriaProducto: " , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@GetMapping("/categoria/leer")
    
    
    
    
}
