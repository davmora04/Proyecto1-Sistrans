package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.repository.CiudadRepository;
import uniandes.edu.co.proyecto.model.Ciudad;

import org.springframework.ui.Model;

@Controller
public class CiudadController {
    @Autowired
    private CiudadRepository ciudadRepository;
    @GetMapping("/ciudad")
public String ciudad(Model model, Integer id) {
    if(id != null && !id.equals(null)) {
        model.addAttribute("ciudad", ciudadRepository.darCiudadPorId(id));
    } else {
        model.addAttribute("ciudad", ciudadRepository.darCiudad());
    }
    return "ciudad";
}





    //  insertar nueva cuenta----------------------------------------------------------------------------------------
    @GetMapping("/ciudad/new")
    public String ciudadForm(Model model) {
        model.addAttribute("ciudad", new Ciudad());
        return "ciudadNuevo";
    }

    @PostMapping("/cuenta/new/save")
    public String cuentaGuardar(@ModelAttribute Ciudad ciudad) {
        ciudadRepository.insertarCiudad(ciudad.getNombre());
        return "redirect:/ciudad";
    }  
//------------------------------------------------------------------------------------------------------   
    

    
}
