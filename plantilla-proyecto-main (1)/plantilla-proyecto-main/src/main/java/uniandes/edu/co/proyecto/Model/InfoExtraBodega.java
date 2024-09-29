package uniandes.edu.co.proyecto.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
@Embeddable
public class InfoExtraBodega implements Serializable {

    @Column(name = "ID_BODEGA")
    private Integer idBodega;

    @Column(name = "ID_PRODUCTO")
    private Integer idProducto;

    // Constructors
    public InfoExtraBodega() {
    }

    public InfoExtraBodega(Integer idBodega, Integer idProducto) {
        this.idBodega = idBodega;
        this.idProducto = idProducto;
    }

    // Getters and Setters
    public Integer getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(Integer idBodega) {
        this.idBodega = idBodega;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    // hashCode and equals (Important for composite keys)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoExtraBodega that = (InfoExtraBodega) o;
        return Objects.equals(idBodega, that.idBodega) && Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBodega, idProducto);
    }
}
