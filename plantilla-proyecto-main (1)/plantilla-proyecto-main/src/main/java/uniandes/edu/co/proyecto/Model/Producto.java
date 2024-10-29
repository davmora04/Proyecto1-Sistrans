package uniandes.edu.co.proyecto.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PRODUCTO")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_producto;

    private String nombre;
    private String codigobarras;
    private Double precioventa;
    private String presentacion;
    private Integer cantidadpresentacion;
    private String unidadmedida;
    private LocalDate expiracion; // Use LocalDate for dates
    private Integer id_categoria; // Foreign Key from CategoriaProducto

    // Default constructor
    public Producto() {}

    // Parameterized constructor
    public Producto(Integer id_producto, String nombre, String codigobarras, Double precioventa, String presentacion,
                    Integer cantidadpresentacion, String unidadmedida, LocalDate expiracion, Integer id_categoria) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.codigobarras = codigobarras;
        this.precioventa = precioventa;
        this.presentacion = presentacion;
        this.cantidadpresentacion = cantidadpresentacion;
        this.unidadmedida = unidadmedida;
        this.expiracion = expiracion;
        this.id_categoria = id_categoria;
    }

    // Getters and Setters
    public Integer getId_producto() {

        
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigobarras() {
        return codigobarras;
    }

    public void setCodigobarras(String codigobarras) {
        this.codigobarras = codigobarras;
    }

    public Double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(Double precioventa) {
        this.precioventa = precioventa;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Integer getCantidadpresentacion() {
        return cantidadpresentacion;
    }

    public void setCantidadpresentacion(Integer cantidadpresentacion) {
        this.cantidadpresentacion = cantidadpresentacion;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public LocalDate getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(LocalDate expiracion) {
        this.expiracion = expiracion;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }
}
