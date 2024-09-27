package uniandes.edu.co.proyecto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Sucursal;

public interface SucursalRepository extends JpaRepository <Sucursal, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sucursal (id, nombre, codigo_ciudad, intalacionM2, telefono) VALUES (:id, :nombre, :codigo_ciudad, :intalacionM2, :telefono)", nativeQuery = true)
    void insertarSucursal(@Param("id") Integer id, @Param("nombre") String nombre, @Param("codigo_ciudad") Integer codigo_ciudad, @Param("intalacionM2") Integer intalacionM2, @Param("telefono") String telefono);
    
    
}
    

