package uniandes.edu.co.proyecto.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name="ORDENCOMPRA")
public class OrdenCompra {

@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orden_compra_seq")
    @SequenceGenerator(name = "orden_compra_seq", sequenceName = "SEQ_ID_COMPRA", allocationSize = 1)
    private Integer idCompra;

    private Integer cantidad;
        
    @Column(name = "PRECIOACORDADO")  

    private Double precioAcordado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "FECHAESPERA")
    private Date fechaEspera;

    private String estado;
    private Integer idSucursal;
    private Integer idProducto;
    private Integer idProveedor;

    // Getters y Setters
    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioAcordado() {
        return precioAcordado;
    }

    public void setPrecioAcordado(Double precioAcordado) {
        this.precioAcordado = precioAcordado;
    }

    public Date getFechaEspera() {
        return fechaEspera;
    }

    public void setFechaEspera(Date fechaEspera) {
        this.fechaEspera = fechaEspera;
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
}
