package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.repository.SucursalRepository;
import uniandes.edu.co.proyecto.model.Sucursal;

import org.springframework.ui.Model;
@Controller
public class SucursalController {
     @Autowired
    private SucursalRepository sucursalRepository;
    @GetMapping("/sucursal")
public String sucursal(Model model, Integer id) {
    System.out.println("holi");
    model.addAttribute("sucursal", sucursalRepository.darSucursal());
    System.out.println(sucursalRepository.darSucursal());
    return "sucursal";
}





    //  insertar nueva cuenta----------------------------------------------------------------------------------------
    @GetMapping("/sucursal/new")
    public String sucursalForm(Model model) {
        model.addAttribute("sucursal", new Sucursal());
        return "sucursalNuevo";
    }

    @PostMapping("/sucursal/new/save")
    public String sucursalGuardar(@ModelAttribute Sucursal sucursal) {
        sucursalRepository.insertarSucursal(sucursal.getNombre(),
        sucursal.getCiudad(),sucursal.getTamano(),sucursal.getTelefono()
        );
        return "redirect:/sucursal";
    }  

    
}

