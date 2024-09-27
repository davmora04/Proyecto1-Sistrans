package uniandes.edu.co.proyecto.model;

import jakarta.persistence.GenerationType; 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.lang.Integer; 

@Entity
@Table(name="SUCURSAL")
public class Sucursal {
    @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_sucursal;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "codigo_ciudad", referencedColumnName = "codigo") 
    private Ciudad ciudad;
    
    private Integer intalacionM2;
    private String telefono;
    public Sucursal(){;}
    
    public Sucursal(Integer id_sucursal,String nombre, Ciudad ciudad, Integer intalacionM2, String telefono ) {
        this.id_sucursal = id_sucursal;
        this.nombre  = nombre;
        this.ciudad = ciudad;
        this.intalacionM2 = intalacionM2;
        this.telefono = telefono;

}

    public Integer getId() {
        return id_sucursal;
    }

    public void setId(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getInstalacionM2() {
        return intalacionM2;
    }

    public void setInstalacionM2(Integer intalacionM2) {
        this.intalacionM2 = intalacionM2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}

