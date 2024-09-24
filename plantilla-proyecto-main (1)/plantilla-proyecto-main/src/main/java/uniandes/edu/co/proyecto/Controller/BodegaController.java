package uniandes.edu.co.proyecto.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.repository.BodegaRepository;
import uniandes.edu.co.proyecto.model.Bodega;

import org.springframework.ui.Model;

@Controller
public class BodegaController {
 @Autowired
    private BodegaRepository bodegaRepository;
    @GetMapping("/bodega")
public String bodega(Model model, Integer id) {
    if(id != null && !id.equals(null)) {
        model.addAttribute("bodega", bodegaRepository.darBodegaPorId(id));
    } else {
        model.addAttribute("bodega", bodegaRepository.darBodega());
    }
    return "bodega";
}





    //  insertar nueva cuenta----------------------------------------------------------------------------------------
    @GetMapping("/bodega/new")
    public String bodegaForm(Model model) {
        model.addAttribute("bodega", new Bodega());
        return "bodegaNuevo";
    }

    @PostMapping("/bodega/new/save")
    public String bodegaGuardar(@ModelAttribute Bodega bodega) {
        bodegaRepository.insertarBodega(bodega.getNombre(),bodega.getTamano(),bodega.getCantiaProd());
        return "redirect:/ciudad";
    }  
//------------------------------------------------------------------------------------------------------  








}
