package uniandes.edu.co.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="PROVEEDORES")
public class Proveedores {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_proveedor;

    private Integer nit;
    private String nombre;
    private String direccion;
    
    private String nombre_contacto;
    
    private String telefono_contacto;
    
    public Proveedores(){;}

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre_contacto() {
        return nombre_contacto;
    }

    public void setNombre_contacto(String nombre_contacto) {
        this.nombre_contacto = nombre_contacto;
    }

    public String getTelefono_contacto() {
        return telefono_contacto;
    }

    public void setTelefono_contacto(String telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }

    public Proveedores(Integer id_proveedor, Integer nit, String nombre, String direccion, String nombre_contacto,
            String telefono_contacto) {
        this.id_proveedor = id_proveedor;
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombre_contacto = nombre_contacto;
        this.telefono_contacto = telefono_contacto;
    }

    public Integer getnit() {
        return nit;
    }

    public void setnit(Integer nit) {
        this.nit = nit;
    }

    

    
}
