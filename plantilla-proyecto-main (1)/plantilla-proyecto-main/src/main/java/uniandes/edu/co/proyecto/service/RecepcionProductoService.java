package uniandes.edu.co.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.model.RecepcionProducto;
import uniandes.edu.co.proyecto.repository.BodegaRepository;

import java.util.List;

@Service
public class RecepcionProductoService {

    @Autowired
    private BodegaRepository bodegaRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<RecepcionProducto> obtenerDocumentosIngreso(Integer idSucursal, Integer idBodega) {
        try {
            // Temporizador de 30 segundos para simular la espera de concurrencia
            Thread.sleep(30000);

            // Llamada al método del repositorio
            return bodegaRepository.findRecepcionProductosBySucursalAndBodega(idSucursal, idBodega);

        } catch (InterruptedException e) {
            System.out.println("El temporizador fue interrumpido: " + e.getMessage());
            throw new RuntimeException("El temporizador fue interrumpido.", e);
        }
    }
}
