package uniandes.edu.co.proyecto.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CATEGORIA")
public class CategoriaProducto {
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_categoria;

    private String codigo; 
    private String nombre;
    private String descripcion;
    private String caracteristicas_almacenamiento; 

    public CategoriaProducto(){;}
    

    public CategoriaProducto(Integer id_categoria, String codigo, String nombre, String descripcion, String caracteristicas_almacenamiento) {
        this.id_categoria = id_categoria;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicas_almacenamiento = caracteristicas_almacenamiento;
    }


    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicas_almacenamiento() {
        return caracteristicas_almacenamiento;
    }

    public void setCaracteristicas_almacenamiento(String caracteristicas_almacenamiento) {
        this.caracteristicas_almacenamiento = caracteristicas_almacenamiento;
    }

    
}
