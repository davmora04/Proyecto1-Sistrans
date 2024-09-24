package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.repository.ProveedoresRepository;
import uniandes.edu.co.proyecto.model.Proveedores;

import org.springframework.ui.Model;

public class ProveedorController {

    
    @Autowired
    private ProveedoresRepository proveedoresRepository;
    @GetMapping("/proveedores")
    public String proveedores(Model model, Integer id) {
        if(id != null && !id.equals(null)) {
            model.addAttribute("proveedores", proveedoresRepository.darProveedorPorId(id));
        } else {
            model.addAttribute("proveedores", proveedoresRepository.darProveedores());
        }
        return "proveedores";
    }
    //  insertar nueva cuenta
    @GetMapping("/proveedores/new")
    public String proveedoresForm(Model model) {
        model.addAttribute("proveedores", new Proveedores());
        return "proveedoresNuevo";
    }

    @PostMapping("/proveedores/new/save")
    public String proveedoresGuardar(@ModelAttribute Proveedores proveedores) {
        proveedoresRepository.insertarProveedor(proveedores.getNombre(), proveedores.getDireccion(), proveedores.getNombreContacto(), proveedores.getTelefonoContacto(), proveedores.getId_proveedores());
        return "redirect:/proveedores";
    
    }  
    //Actualizar Cuenta
    @GetMapping("/proveedores/{id}/edit")
    public String proveedoresUpdate(@PathVariable("id") Integer id, Model model) {
        Proveedores proveedores = proveedoresRepository.darProveedorPorId(id);
        if (proveedores != null) {
            model.addAttribute("proveedores", proveedores);
            return "proveedoresEditar";
        } else {
            return "redirect:/proveedores";
        }   
    }

    @PostMapping("/proveedores/{id}/edit")
    public String proveedoresEditarGuardar(@PathVariable("id") Integer id, @ModelAttribute Proveedores proveedores) {
        proveedoresRepository.actualizarProveedor(proveedores.getNombre(), proveedores.getDireccion(), proveedores.getNombreContacto(), proveedores.getTelefonoContacto(), proveedores.getId_proveedores());
        return "redirect:/proveedores";
    }





    
}