package uniandes.edu.co.proyecto.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table

public class Bodega {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String tamano;
    private String cantiaProd;
    
    public Bodega(){;}
    
    public Bodega(Integer id, String nombre, String tamano, String cantiaProd) {
        this.id = id;
        this.nombre = nombre;
        this.tamano = tamano;
        this.cantiaProd = cantiaProd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getCantiaProd() {
        return cantiaProd;
    }

    public void setCantiaProd(String cantiaProd) {
        this.cantiaProd = cantiaProd;
    }

    
    
    
}
