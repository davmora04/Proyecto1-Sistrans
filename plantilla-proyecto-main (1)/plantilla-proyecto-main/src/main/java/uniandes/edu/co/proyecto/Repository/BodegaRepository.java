package uniandes.edu.co.proyecto.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.model.RecepcionProducto;

import uniandes.edu.co.proyecto.model.Bodega;

public interface BodegaRepository extends JpaRepository<Bodega, Integer> {

    // Insert new BODEGA
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO bodega (id_bodega, nombre, tamano, cantidad_prod, id_sucursal) VALUES (:id_bodega, :nombre, :tamano, :cantidad_prod, :id_sucursal)", nativeQuery = true)
    void insertarBodega(@Param("id_bodega") Integer id_bodega, @Param("nombre") String nombre, @Param("tamano") Integer tamano, @Param("cantidad_prod") Integer cantidad_prod, @Param("id_sucursal") Integer id_sucursal);

    // Read BODEGA by ID
    @Query(value = "SELECT * FROM bodega WHERE id_bodega = :id_bodega", nativeQuery = true)
    Bodega leerBodega(@Param("id_bodega") Integer id_bodega);

    // Delete BODEGA by ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bodega WHERE id_bodega = :id_bodega", nativeQuery = true)
    void eliminarBodega(@Param("id_bodega") Integer id_bodega);

    @Query(value = "SELECT " +
    "S.NOMBRE AS NOMBRE_SUCURSAL, " +
    "B.ID_BODEGA, " +
    "B.NOMBRE AS NOMBRE_BODEGA, " +
    "B.TAMANO AS TAMANO_BODEGA, " +
    "SUM(BP.CANTIDAD * P.CANTIDADPRESENTACION) AS VOLUMEN_OCUPADO, " +
    "(SUM(BP.CANTIDAD * P.CANTIDADPRESENTACION) / B.TAMANO) * 100 AS PORCENTAJE_OCUPACION " +
    "FROM SUCURSAL S " +
    "JOIN BODEGA B ON S.ID_SUCURSAL = B.ID_SUCURSAL " +
    "JOIN BODEGA_PRODUCTO BP ON B.ID_BODEGA = BP.ID_BODEGA " +
    "JOIN PRODUCTO P ON BP.ID_PRODUCTO = P.ID_PRODUCTO " +
    "WHERE S.ID_SUCURSAL = :idSucursal " +
    "AND BP.ID_PRODUCTO IN :listaProductos " +
    "GROUP BY S.NOMBRE, B.ID_BODEGA, B.NOMBRE, B.TAMANO", 
    nativeQuery = true)
Collection<Map<String, Object>> obtenerOcupacionBodegasPorProductos(@Param("idSucursal") Integer idSucursal, @Param("listaProductos") List<Integer> listaProductos);

//CORREGIR
@Transactional(readOnly = true)
@Query("SELECT r FROM RecepcionProducto r " +
       "JOIN r.bodega b " +
       "JOIN r.producto p " +
       "WHERE b.id_sucursal = :idSucursal AND b.id_bodega = :idBodega AND r.fechaRecepcion >= CURRENT_DATE - 30")
List<RecepcionProducto> findRecepcionProductosBySucursalAndBodega(
        @Param("idSucursal") Integer idSucursal,
        @Param("idBodega") Integer idBodega);

/* @Transactional(rollbackFor = Exception.class)
@Query(value = //"DELETE " + "RECEPCIONPRODUCTO "  + "WHERE " + "id_recepcion=2; " +
//"DELETE " + "BODEGA_PRODUCTO " + "WHERE " + "ID_BODEGA = 2001 " + "AND " + "ID_PRODUCTO = 3001" 
"UPDATE ORDENCOMPRA " + "SET ESTADO = 'anulada' " + "WHERE " + "ID_COMPRA = 1" 

//"INSERT " + "INTO " + "RECEPCIONPRODUCTO (ID_RECEPCION, FECHARECEPCION, CANTIDADRECIBIDA, COSTOUNITARIO, ID_BODEGA, ID_PRODUCTO) " + "VALUES (2, SYSDATE, v_cantidad_recibida, v_costo_unitario, v_id_bodega, v_id_producto;" 

, nativeQuery = true)
void insertarProductoBodega(@Param("v_id_orden") Integer v_id_orden, @Param("v_id_producto") Integer v_id_producto, 
@Param("v_id_bodega") Integer v_id_bodega, @Param("v_cantidad_recibida") Integer v_cantidad_recibida, 
@Param("v_costo_unitario") Double v_costo_unitario, @Param("v_cantidad_existente") Integer v_cantidad_existente,
@Param("v_cantidad_orden") Integer v_cantidad_orden); 

}

*/
// 1. Eliminar Recepción de Producto
@Modifying
@Transactional
@Query(value = "DELETE FROM RECEPCIONPRODUCTO WHERE ID_RECEPCION = :idRecepcion", nativeQuery = true)
void eliminarRecepcionProducto(@Param("idRecepcion") Integer idRecepcion);

/*// 2. Eliminar Producto de la Bodega
@Modifying
@Transactional
@Query(value = "DELETE FROM BODEGA_PRODUCTO WHERE ID_BODEGA = :idBodega AND ID_PRODUCTO = :idProducto", nativeQuery = true)
void eliminarProductoDeBodega(@Param("idBodega") Integer idBodega, @Param("idProducto") Integer idProducto); */

// 3. Actualizar el estado de la Orden de Compra a 'anulada'
@Modifying
@Transactional
@Query(value = "UPDATE ORDENCOMPRA SET ESTADO = 'anulada' WHERE ID_COMPRA = :idCompra", nativeQuery = true)
void actualizarEstadoOrdenCompra(@Param("idCompra") Integer idCompra);

// 4. Insertar Recepción de Producto
@Modifying
@Transactional
@Query(value = "INSERT INTO RECEPCIONPRODUCTO (ID_RECEPCION, FECHARECEPCION, CANTIDADRECIBIDA, COSTOUNITARIO, ID_BODEGA, ID_PRODUCTO) " +
               "VALUES (:idRecepcion, SYSDATE, :cantidadRecibida, :costoUnitario, :idBodega, :idProducto)", nativeQuery = true)
void insertarRecepcionProducto(@Param("idRecepcion") Integer idRecepcion, @Param("cantidadRecibida") Integer cantidadRecibida,
                               @Param("costoUnitario") Double costoUnitario, @Param("idBodega") Integer idBodega, @Param("idProducto") Integer idProducto);

// 5. Actualizar la cantidad en Bodega
@Modifying
@Transactional
@Query(value = "UPDATE BODEGA_PRODUCTO SET CANTIDAD = CANTIDAD + :cantidadRecibida WHERE ID_BODEGA = :idBodega AND ID_PRODUCTO = :idProducto", nativeQuery = true)
void actualizarCantidadBodega(@Param("idBodega") Integer idBodega, @Param("idProducto") Integer idProducto, @Param("cantidadRecibida") Integer cantidadRecibida);

// 6. Actualizar el estado de la Orden de Compra a 'ENTREGADA'
@Modifying
@Transactional
@Query(value = "UPDATE ORDENCOMPRA SET ESTADO = 'ENTREGADA' WHERE ID_COMPRA = :idOrden", nativeQuery = true)
void actualizarEstadoOrdenEntregada(@Param("idOrden") Integer idOrden);



}



