package uniandes.edu.co.proyecto.repository;

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
}
