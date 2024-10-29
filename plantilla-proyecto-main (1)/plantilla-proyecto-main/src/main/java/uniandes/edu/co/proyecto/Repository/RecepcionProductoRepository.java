package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.proyecto.model.RecepcionProducto;

import java.util.List;
import java.util.Map;

public interface RecepcionProductoRepository extends JpaRepository<RecepcionProducto, Integer> {
    
    @Query(value = "SELECT s.NOMBRE AS nombreSucursal, b.NOMBRE AS nombreBodega, rp.ID_PRODUCTO, " +
                   "p.NOMBRE AS nombreProveedor, " +
                   "rp.ID_RECEPCION AS numeroDocumento, rp.FECHARECEPCION AS fechaDocumento " +
                   "FROM RECEPCIONPRODUCTO rp " +
                   "JOIN BODEGA b ON rp.ID_BODEGA = b.ID_BODEGA " +
                   "JOIN SUCURSAL s ON b.ID_SUCURSAL = s.ID_SUCURSAL " +
                   "JOIN PRODUCTOPROVEEDOR pp ON rp.ID_PRODUCTO = pp.ID_PRODUCTO " +
                   "JOIN PROVEEDORES p ON pp.ID_PROVEEDOR = p.ID_PROVEEDOR " +
                   "WHERE s.ID_SUCURSAL = :idSucursal " +
                   "AND b.ID_BODEGA = :idBodega " +
                   "AND rp.FECHARECEPCION >= SYSDATE - 30", nativeQuery = true)
    List<Map<String, Object>> buscarDocumentosIngreso(@Param("idSucursal") Integer idSucursal, 
                                            @Param("idBodega") Integer idBodega);
}
