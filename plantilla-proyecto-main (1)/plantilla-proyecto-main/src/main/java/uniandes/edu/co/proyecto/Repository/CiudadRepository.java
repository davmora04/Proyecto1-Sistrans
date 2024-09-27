package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Ciudad;

public interface CiudadRepository  extends JpaRepository <Ciudad, Integer>{ 

    //crear CIUDAD
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ciudad (codigo, nombre) VALUES (:codigo, :nombre)", nativeQuery = true)
    void insertarCiudad(@Param("nombre") String nombre, @Param("codigo") Integer codigo);
    
}
