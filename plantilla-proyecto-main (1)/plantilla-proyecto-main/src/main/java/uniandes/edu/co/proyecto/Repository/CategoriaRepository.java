package uniandes.edu.co.proyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.Model.Categoria;
import java.util.Collection;

public interface CategoriaRepository  extends JpaRepository<Categoria, Integer> {
    //Para seleccionar todas las tuplas de la tabla categoria
    @Query(value = "SELECT * FROM categoria", nativeQuery = true)
    Collection<Categoria> darcategoria();
    //Para seleccionar una tupla de la tabla categoria dado un id
    @Query(value = "SELECT * FROM categoria WHERE id_categoria = :id_categoria", nativeQuery = true)
    Categoria darProveedorPorId(@Param("id_categoria") Integer id_categoria);
    // crear un proveedor 
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categoria (id_categoria, nombre, direccion, nombre_contacto, telefono_contacto) VALUES ( parranderos_sequence.nextval , :nombre, :direccion, :nombre_contacto, :telefono_contacto)", nativeQuery = true)
    void insertarProveedor(@Param("nombre") String nombre, 
    @Param("direccion") String direccion, 
    @Param("nombre_contacto") String nombre_contacto, 
    @Param("telefono_contacto") String telefono_contacto, 
    @Param("id_categoria") Integer id_categoria);

    //Para actualizar un proveedor
    @Modifying
    @Transactional
    @Query(value = "UPDATE categoria SET nombre = :nombre, direccion = :direccion, nombre_contacto = :nombre_contacto, telefono_contacto = :telefono_contacto WHERE id_categoria = :id_categoria", nativeQuery = true)
    void actualizarProveedor(@Param("nombre") String nombre,
    @Param("direccion") String direccion,
    @Param("nombre_contacto") String nombre_contacto,
    @Param("telefono_contacto") String telefono_contacto,
    @Param("id_categoria") Integer id_categoria);

    // Para eliminar un proveedor 
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM categoria WHERE id_categoria = :id_categoria", nativeQuery = true)
    void eliminarProveedor(@Param("id_categoria") Integer id_categoria);

    // Para la lectura, se solicita el código o nombre de la categoría, y como resultado, se obtiene la información de dicha categoría.
    @Query(value = "SELECT * FROM categoria WHERE nombre = :nombre", nativeQuery = true)
    Categoria darCategoriaPorNombre(@Param("nombre") String nombre);
    
}
