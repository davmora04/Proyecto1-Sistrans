package uniandes.edu.co.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import uniandes.edu.co.proyecto.repository.RecepcionProductoRepository;

@Service
public class RecepcionProductoService {

    @Autowired
    private RecepcionProductoRepository recepcionProductoRepository;

    @Transactional (isolation = Isolation.SERIALIZABLE, readOnly = true)
    public List<Map<String, Object>> obtenerDocumentosIngreso(Integer idSucursal, Integer idBodega) {
        
        try {
            // Temporizador de 30 segundos para simular la espera de concurrencia
            Thread.sleep(30000);

            // Llamada al método del repositorio
            return recepcionProductoRepository.buscarDocumentosIngreso(idSucursal, idBodega);

        } catch (InterruptedException e) {
            System.out.println("El temporizador fue interrumpido: " + e.getMessage());
            throw new RuntimeException("El temporizador fue interrumpido.", e);
        } 
    }
}
