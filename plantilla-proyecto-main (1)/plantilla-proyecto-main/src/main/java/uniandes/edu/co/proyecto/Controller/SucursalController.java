package uniandes.edu.co.proyecto.Controller;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.Model.Ciudad;
import uniandes.edu.co.proyecto.Model.Sucursal;
import uniandes.edu.co.proyecto.Repository.CiudadRepository;
import uniandes.edu.co.proyecto.Repository.SucursalRepository;

import org.springframework.ui.Model;
@Controller
public class SucursalController {
     @Autowired
    private SucursalRepository sucursalRepository;
    @GetMapping("/sucusal")
public String sucursal(Model model, Integer id) {
    if(id != null && !id.equals(null)) {
        model.addAttribute("sucursal", sucursalRepository.darSucursalPorId(id));
    } else {
        model.addAttribute("sucursal", sucursalRepository.darSucursal());
    }
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
