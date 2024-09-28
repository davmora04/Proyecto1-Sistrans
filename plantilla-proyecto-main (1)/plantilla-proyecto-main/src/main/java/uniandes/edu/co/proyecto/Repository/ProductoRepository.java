package uniandes.edu.co.proyecto.repository;

import java.time.LocalDate;
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
}
