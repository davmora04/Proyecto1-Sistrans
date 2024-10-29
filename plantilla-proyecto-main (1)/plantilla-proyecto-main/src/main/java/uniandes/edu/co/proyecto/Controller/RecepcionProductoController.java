package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.service.RecepcionProductoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recepcion")
public class RecepcionProductoController {

    @Autowired
    private RecepcionProductoService recepcionProductoService;

    @GetMapping("/sucursal/{idSucursal}/bodega/{idBodega}")
    public List<Map<String, Object>> obtenerDocumentosIngreso(
            @PathVariable Integer idSucursal,
            @PathVariable Integer idBodega) {
        return recepcionProductoService.obtenerDocumentosIngreso(idSucursal, idBodega);
    }
}
