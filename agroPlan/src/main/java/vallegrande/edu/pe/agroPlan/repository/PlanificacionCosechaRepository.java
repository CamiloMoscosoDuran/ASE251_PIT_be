package vallegrande.edu.pe.agroPlan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vallegrande.edu.pe.agroPlan.model.PlanificacionCosecha;

@Repository
public interface PlanificacionCosechaRepository extends JpaRepository<PlanificacionCosecha, Integer> {
    List<PlanificacionCosecha> findByIdCampo(Integer idCampo);
    List<PlanificacionCosecha> findByIdCultivo(Integer idCultivo);
    List<PlanificacionCosecha> findByEstado(String estado);
}
