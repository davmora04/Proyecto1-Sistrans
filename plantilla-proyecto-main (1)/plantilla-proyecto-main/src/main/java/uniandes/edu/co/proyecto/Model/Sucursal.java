package uniandes.edu.co.proyecto.model;

import jakarta.persistence.GenerationType; 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.lang.Integer; 

@Entity
@Table(name="SUCURSAL")
public class Sucursal {
    @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_sucursal;

    private String nombre;

    private Integer codigo_ciudad;
    
    private Integer intalacionM2;
    private String telefono;

    public Sucursal(){;}

    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCodigo_ciudad() {
        return codigo_ciudad;
    }

    public void setCodigo_ciudad(Integer codigo_ciudad) {
        this.codigo_ciudad = codigo_ciudad;
    }

    public Integer getIntalacionM2() {
        return intalacionM2;
    }

    public void setIntalacionM2(Integer intalacionM2) {
        this.intalacionM2 = intalacionM2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}

