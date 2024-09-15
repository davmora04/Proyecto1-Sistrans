package uniandes.edu.co.proyecto.Repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import uniandes.edu.co.proyecto.Model.Ciudad;
import uniandes.edu.co.proyecto.Model.Sucursal;

public interface SucursalRepository extends JpaRepository <Sucursal, Integer> {
    //Para seleccionar todas las cuentas
    @Query(value = "SELECT * FROM sucursal", nativeQuery = true)
    Collection<Ciudad> darSucursal();
    
    //Para selecionar una cuenta en base al id
    @Query(value = "SELECT * FROM sucursal WHERE id = :id", nativeQuery = true)
    Ciudad darSucursalPorId(@Param("id") Integer id);
    
    //crear Cuenta
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ciudad (id, nombre,ciudad,tamano,telefono) VALUES ( id.nextval , :nombre,:ciudad,:tamano,:telefono)", nativeQuery = true)
    void insertarSucursal(@Param("nombre") String nombre,
    @Param("nombre") String ciudad,
    @Param("nombre") Integer tamano,
    @Param("nombre") String telefono);
    
    
    
}
