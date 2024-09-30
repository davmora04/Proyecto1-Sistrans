package uniandes.edu.co.proyecto.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Insert a new Producto using native SQL query
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO producto (id_producto, nombre, codigobarras, precioventa, presentacion, cantidadpresentacion, unidadmedida, expiracion, id_categoria) " +
            "VALUES (:id_producto, :nombre, :codigobarras, :precioventa, :presentacion, :cantidadpresentacion, :unidadmedida, :expiracion, :id_categoria)", nativeQuery = true)
    void insertarProducto(@Param("id_producto") Integer id_producto, @Param("nombre") String nombre, @Param("codigobarras") String codigobarras,
                          @Param("precioventa") Double precioventa, @Param("presentacion") String presentacion, 
                          @Param("cantidadpresentacion") Integer cantidadpresentacion, @Param("unidadmedida") String unidadmedida,
                          @Param("expiracion") LocalDate expiracion, @Param("id_categoria") Integer id_categoria);

    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    Producto findByNombre(@Param("nombre") String nombre);


    // Update the Producto by codigo de barras
    @Modifying
    @Transactional
    @Query(value = "UPDATE producto SET nombre = :nombre, precioventa = :precioventa, presentacion = :presentacion, " +
            "cantidadpresentacion = :cantidadpresentacion, unidadmedida = :unidadmedida, expiracion = :expiracion, " +
            "id_categoria = :id_categoria WHERE codigobarras = :codigobarras", nativeQuery = true)
    void actualizarProducto(@Param("codigobarras") String codigobarras, @Param("nombre") String nombre, 
                            @Param("precioventa") Double precioventa, @Param("presentacion") String presentacion, 
                            @Param("cantidadpresentacion") Integer cantidadpresentacion, @Param("unidadmedida") String unidadmedida, 
                            @Param("expiracion") LocalDate expiracion, @Param("id_categoria") Integer id_categoria);
    //rfc2
    @Query(value = "SELECT p.* FROM PRODUCTO p " +
    "JOIN CATEGORIA c ON p.ID_CATEGORIA = c.ID_CATEGORIA " +
    "WHERE (:minPrecio IS NULL OR p.PRECIOVENTA >= :minPrecio) " +
    "AND (:maxPrecio IS NULL OR p.PRECIOVENTA <= :maxPrecio) " +
    "AND (:expirationDate IS NULL OR p.EXPIRACION >= :expirationDate) " +
    "AND (:idCategoria IS NULL OR c.ID_CATEGORIA = :idCategoria)", nativeQuery = true)
    List<Producto> buscarProductosPorCriterios(@Param("minPrecio") Double minPrecio,
                                @Param("maxPrecio") Double maxPrecio,
                                @Param("expirationDate") LocalDate expirationDate,
                                @Param("idCategoria") Integer idCategoria);
    
                                

    @Query(value = "SELECT p.ID_PRODUCTO, p.NOMBRE, p.CODIGOBARRAS, p.PRECIOVENTA, " +
    "i.TOTALEXISTENCIAS, i.MIVELMINREORDEN, i.COSTOPROMEDIO " +
    "FROM PRODUCTO p " +
    "JOIN INFOEXTRABODEGA i ON p.ID_PRODUCTO = i.ID_PRODUCTO " +
    "JOIN BODEGA b ON i.ID_BODEGA = b.ID_BODEGA " +
    "JOIN SUCURSAL s ON b.ID_SUCURSAL = s.ID_SUCURSAL " +
    "WHERE s.ID_SUCURSAL = :idSucursal AND b.ID_BODEGA = :idBodega", 
        nativeQuery = true)
        List<Object[]> obtenerInventarioProductos(@Param("idSucursal") Integer idSucursal, @Param("idBodega") Integer idBodega);
                            
        @Query(value = "SELECT p.ID_PRODUCTO, p.NOMBRE AS NOMBRE_PRODUCTO, b.ID_BODEGA, b.NOMBRE AS NOMBRE_BODEGA, " +
        "s.ID_SUCURSAL, s.NOMBRE AS NOMBRE_SUCURSAL, bp.CANTIDAD AS CANTIDAD_ACTUAL, " +
        "pr.ID_PROVEEDOR, pr.NOMBRE AS NOMBRE_PROVEEDOR " +
        "FROM PRODUCTO p " +
        "JOIN BODEGA_PRODUCTO bp ON p.ID_PRODUCTO = bp.ID_PRODUCTO " +
        "JOIN BODEGA b ON bp.ID_BODEGA = b.ID_BODEGA " +
        "JOIN SUCURSAL s ON b.ID_SUCURSAL = s.ID_SUCURSAL " +
        "LEFT JOIN PRODUCTOPROVEEDOR pp ON p.ID_PRODUCTO = pp.ID_PRODUCTO " +
        "LEFT JOIN PROVEEDORES pr ON pp.ID_PROVEEDOR = pr.ID_PROVEEDOR " +
        "WHERE bp.CANTIDAD < b.CANTIDAD_PROD", nativeQuery = true)
List<Map<String, Object>> findProductosPorReorden();
}
