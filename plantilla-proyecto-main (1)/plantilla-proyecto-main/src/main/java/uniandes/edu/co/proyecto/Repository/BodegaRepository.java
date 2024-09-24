package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Bodega;


public interface BodegaRepository extends JpaRepository <Bodega, Integer> {
    @Query(value = "SELECT * FROM bodega", nativeQuery = true)
    Collection<Bodega> darBodega();
    
    //Para selecionar una cuenta en base al id
    @Query(value = "SELECT * FROM bodega WHERE id = :id", nativeQuery = true)
    Bodega darBodegaPorId(@Param("id") Integer id);
    
    //crear Cuenta
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ciudad (id, nombre) VALUES ( id.nextval , :nombre)", nativeQuery = true)
    void insertarBodega(@Param("nombre") String nombre,@Param("tamano") String tamano,
    @Param("cantiaProd") String cantiaProd);
    
}