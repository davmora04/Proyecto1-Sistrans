package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Ciudad;

public interface CiudadRepository extends JpaRepository <Ciudad, Integer>{
    //Para seleccionar todas las cuentas
    @Query(value = "SELECT * FROM ciudad", nativeQuery = true)
    Collection<Ciudad> darCiudad();
    
    //Para selecionar una cuenta en base al id
    @Query(value = "SELECT * FROM ciudad WHERE id = :id", nativeQuery = true)
    Ciudad darCiudadPorId(@Param("id") Integer id);
    
    //crear Cuenta
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ciudad (id, nombre) VALUES ( id.nextval , :nombre)", nativeQuery = true)
    void insertarCiudad(@Param("nombre") String nombre);

    //--------------------------------------------------------------------------------------------------------
    
}
