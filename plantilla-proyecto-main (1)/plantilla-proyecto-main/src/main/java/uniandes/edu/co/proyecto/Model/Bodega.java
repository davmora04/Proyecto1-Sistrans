package uniandes.edu.co.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BODEGA")
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_bodega;

    private String nombre;
    private Integer tamano;
    private Integer cantidad_prod;
    private Integer id_sucursal;

    // Default constructor
    public Bodega() {
    }

    // Parameterized constructor
    public Bodega(Integer id_bodega, String nombre, Integer tamano, Integer cantidad_prod, Integer id_sucursal) {
        this.id_bodega = id_bodega;
        this.nombre = nombre;
        this.tamano = tamano;
        this.cantidad_prod = cantidad_prod;
        this.id_sucursal = id_sucursal;
    }

    // Getters and Setters
    public Integer getId_bodega() {
        return id_bodega;
    }

    public void setId_bodega(Integer id_bodega) {
        this.id_bodega = id_bodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTamano() {
        return tamano;
    }

    public void setTamano(Integer tamano) {
        this.tamano = tamano;
    }

    public Integer getCantidad_prod() {
        return cantidad_prod;
    }

    public void setCantidad_prod(Integer cantidad_prod) {
        this.cantidad_prod = cantidad_prod;
    }

    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }
}
