package vallegrande.edu.pe.agroPlan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vallegrande.edu.pe.agroPlan.model.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Integer> {

    List<Alerta> findByIdCampo(Integer idCampo);

    List<Alerta> findByIdCultivo(Integer idCultivo);

    // Alertas activas (estado = true) o archivadas (estado = false)
    List<Alerta> findByEstado(Boolean estado);

    // Filtrar por tipo: 'Peligro', 'Información', 'Éxito', 'Advertencia'
    List<Alerta> findByTipoAlerta(String tipoAlerta);

    // Alertas activas de un campo específico
    List<Alerta> findByIdCampoAndEstado(Integer idCampo, Boolean estado);
}
