package uniandes.edu.co.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

import jakarta.persistence.Column;

@Entity
@Table(name = "RECEPCIONPRODUCTO")
public class RecepcionProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecepcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHARECEPCION")
    private Date fechaRecepcion;

    @Column(name = "CANTIDADRECIBIDA")
    private Integer cantidadRecibida;

    @Column(name = "COSTOUNITARIO")
    private Double costoUnitario;

    @ManyToOne
    @JoinColumn(name = "ID_BODEGA", nullable = false)
    private Bodega bodega;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", nullable = false)
    private Producto producto;

    // Constructor por defecto
    public RecepcionProducto() {
    }

    // Constructor con parámetros
    public RecepcionProducto(Integer idRecepcion, Date fechaRecepcion, Integer cantidadRecibida, Double costoUnitario, Bodega bodega, Producto producto) {
        this.idRecepcion = idRecepcion;
        this.fechaRecepcion = fechaRecepcion;
        this.cantidadRecibida = cantidadRecibida;
        this.costoUnitario = costoUnitario;
        this.bodega = bodega;
        this.producto = producto;
    }

    // Getters y Setters
    public Integer getIdRecepcion() {
        return idRecepcion;
    }

    public void setIdRecepcion(Integer idRecepcion) {
        this.idRecepcion = idRecepcion;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Integer getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Integer cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
