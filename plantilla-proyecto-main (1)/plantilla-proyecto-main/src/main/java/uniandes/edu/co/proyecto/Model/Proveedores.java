package uniandes.edu.co.proyecto.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Proveedores {
    @Id
    private Integer id_proveedores;
    private String nombre;
    private String direccion;
    private String nombreContacto;
    private String telefonoContacto;

    public Proveedores() {;} 
    public Proveedores (Integer id_proveedores, String nombre, String direccion, String nombreContacto, String telefonoContacto) {
        this.id_proveedores = id_proveedores;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
    }
    public Integer getId_proveedores() {
        return id_proveedores;
    }
    public void setId_proveedores(Integer id_proveedores) {
        this.id_proveedores = id_proveedores;
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
    public String getNombreContacto() {
        return nombreContacto;
    }
    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }
    public String getTelefonoContacto() {
        return telefonoContacto;
    }
    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
    
    public Proveedores orElse (Object object) {
        throw new UnsupportedOperationException("Unimplemented method 'orElse'");
    }



    
}
