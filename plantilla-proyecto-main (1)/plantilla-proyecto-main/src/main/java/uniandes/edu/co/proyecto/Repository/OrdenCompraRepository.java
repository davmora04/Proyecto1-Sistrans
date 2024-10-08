package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.model.OrdenCompra;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ordencompra (cantidad, precioAcordado, fechaEspera, estado, id_sucursal, id_producto, id_proveedor) " +
                   "VALUES (:cantidad, :precioAcordado, :fechaEspera, :estado, :id_sucursal, :id_producto, :id_proveedor)", nativeQuery = true)
    void insertarOrdenCompra(
        @Param("cantidad") Integer cantidad,
        @Param("precioAcordado") Double precioAcordado,
        @Param("fechaEspera") String fechaEspera,
        @Param("estado") String estado,
        @Param("id_sucursal") Integer id_sucursal,
        @Param("id_producto") Integer id_producto,
        @Param("id_proveedor") Integer id_proveedor);
}
