package uniandes.edu.co.proyecto.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Sucursal;

public interface SucursalRepository extends JpaRepository <Sucursal, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sucursal (id_sucursal, nombre, codigo_ciudad, intalacionM2, telefono) VALUES (:id_sucursal, :nombre, :codigo_ciudad, :intalacionM2, :telefono)", nativeQuery = true)
    void insertarSucursal(@Param("id_sucursal") Integer id_sucursal, @Param("nombre") String nombre, @Param("codigo_ciudad") Integer codigo_ciudad, @Param("intalacionM2") Integer intalacionM2, @Param("telefono") String telefono);
    
    
    @Query(value = "SELECT s.ID_SUCURSAL, s.NOMBRE, s.INTALACIONM2, s.TELEFONO " +
    "FROM SUCURSAL s " +
    "JOIN BODEGA b ON s.ID_SUCURSAL = b.ID_SUCURSAL " +
    "JOIN BODEGA_PRODUCTO bp ON b.ID_BODEGA = bp.ID_BODEGA " +
    "WHERE bp.ID_PRODUCTO = :idProducto AND bp.CANTIDAD > 0", nativeQuery = true)
List<Map<String, Object>> findSucursalesByProductoId(@Param("idProducto") Integer idProducto);

@Query(value = "SELECT s.ID_SUCURSAL, s.NOMBRE, s.INTALACIONM2, s.TELEFONO " +
    "FROM SUCURSAL s " +
    "JOIN BODEGA b ON s.ID_SUCURSAL = b.ID_SUCURSAL " +
    "JOIN BODEGA_PRODUCTO bp ON b.ID_BODEGA = bp.ID_BODEGA " +
    "JOIN PRODUCTO p ON bp.ID_PRODUCTO = p.ID_PRODUCTO " +
    "WHERE p.NOMBRE = :nombreProducto AND bp.CANTIDAD > 0", nativeQuery = true)
List<Map<String, Object>> findSucursalesByProductoNombre(@Param("nombreProducto") String nombreProducto);

}
    

