package uniandes.edu.co.proyecto.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.Model.Proveedores;
import java.util.Collection;

public interface ProveedoresRepository extends JpaRepository<Proveedores, Integer> {
    //Para seleccionar todas las tuplas de la tabla Proveedores
    @Query(value = "SELECT * FROM proveedores", nativeQuery = true)
    Collection<Proveedores> darProveedores();
    //Para seleccionar una tupla de la tabla Proveedores dado un id
    @Query(value = "SELECT * FROM proveedores WHERE id_proveedores = :id_proveedores", nativeQuery = true)
    Proveedores darProveedorPorId(@Param("id_proveedores") Integer id_proveedores);
    // crear un proveedor 
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO proveedores (id_proveedores, nombre, direccion, nombre_contacto, telefono_contacto) VALUES ( parranderos_sequence.nextval , :nombre, :direccion, :nombre_contacto, :telefono_contacto)", nativeQuery = true)
    void insertarProveedor(@Param("nombre") String nombre, 
    @Param("direccion") String direccion, 
    @Param("nombre_contacto") String nombre_contacto, 
    @Param("telefono_contacto") String telefono_contacto, 
    @Param("id_proveedores") Integer id_proveedores);

    //Para actualizar un proveedor
    @Modifying
    @Transactional
    @Query(value = "UPDATE proveedores SET nombre = :nombre, direccion = :direccion, nombre_contacto = :nombre_contacto, telefono_contacto = :telefono_contacto WHERE id_proveedores = :id_proveedores", nativeQuery = true)
    void actualizarProveedor(@Param("nombre") String nombre,
    @Param("direccion") String direccion,
    @Param("nombre_contacto") String nombre_contacto,
    @Param("telefono_contacto") String telefono_contacto,
    @Param("id_proveedores") Integer id_proveedores);

    // Para eliminar un proveedor 
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM proveedores WHERE id_proveedores = :id_proveedores", nativeQuery = true)
    void eliminarProveedor(@Param("id_proveedores") Integer id_proveedores);


    
}