package uniandes.edu.co.proyecto.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.CategoriaProducto;


public interface CategoriaProductoRepository extends JpaRepository <CategoriaProducto, Integer>{

    //crear CATEGORIA 
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categoria (id_categoria, codigo, nombre, descripcion, caracteristicas_almacenamiento) VALUES (:id_categoria, :codigo, :nombre, :descripcion, :caracteristicas_almacenamiento)", nativeQuery = true)
    void insertarCategoria(@Param("id_categoria") Integer id_categoria, @Param("codigo") String codigo, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("caracteristicas_almacenamiento") String caracteristicas_almacenamiento);

    // leer CATEGORIA (se hace por el id)
    @Query(value = "SELECT * FROM categoria WHERE id_categoria = :id_categoria", nativeQuery = true) 
    CategoriaProducto leerCategoria(@Param("id_categoria") Integer id_categoria);

    
}
