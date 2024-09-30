package uniandes.edu.co.proyecto.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

}
