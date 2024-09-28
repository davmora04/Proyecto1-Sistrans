package uniandes.edu.co.proyecto.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Proveedores;

public interface ProveedoresRepository extends JpaRepository <Proveedores, Integer>{

    //crear PROVEEDOR
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO proveedores (id_proveedor, nit, nombre, direccion, nombre_contacto, telefono_contacto) VALUES (:id_proveedor,:nit, :nombre, :direccion, :nombre_contacto, :telefono_contacto)", nativeQuery = true)
        void insertarProveedor(@Param("id_proveedor") Integer id_proveedor, @Param("nit") Integer nit, @Param("nombre") String nombre, @Param("direccion") String direccion, @Param("nombre_contacto") String nombre_contacto, @Param("telefono_contacto") String telefono_contacto);

    //actualizar PROVEEDOR
    @Modifying
    @Transactional
    @Query(value = "UPDATE proveedores SET nit = :nit, nombre = :nombre, direccion = :direccion, nombre_contacto = :nombre_contacto, telefono_contacto = :telefono_contacto WHERE id_proveedor = :id_proveedor", nativeQuery = true)
        void actualizarProveedor(@Param("id_proveedor") Integer id_proveedor, @Param("nit") Integer nit, @Param("nombre") String nombre, @Param("direccion") String direccion, @Param("nombre_contacto") String nombre_contacto, @Param("telefono_contacto") String telefono_contacto);


}
