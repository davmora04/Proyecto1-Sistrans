package uniandes.edu.co.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.proyecto.model.RecepcionProducto;

public interface RecepcionProductoRepository extends JpaRepository <RecepcionProducto, Integer> {
    @Query(value = "SELECT s.NOMBRE AS nombreSucursal, b.NOMBRE AS nombreBodega, rp.ID_RECEPCION AS numeroDocumento, " +
                   "rp.FECHARECEPCION AS fechaDocumento " +
                   "FROM RECEPCIONPRODUCTO rp " +
                   "JOIN BODEGA b ON rp.ID_BODEGA = b.ID_BODEGA " +
                   "JOIN SUCURSAL s ON b.ID_SUCURSAL = s.ID_SUCURSAL " +
                   "WHERE s.ID_SUCURSAL = :idSucursal " +
                   "AND b.ID_BODEGA = :idBodega " +
                   "AND rp.FECHARECEPCION >= SYSDATE - 30", nativeQuery = true)
    List<RecepcionProducto> buscarDocumentosIngreso(@Param("idSucursal") Integer idSucursal, 
                                            @Param("idBodega") Integer idBodega);
}
