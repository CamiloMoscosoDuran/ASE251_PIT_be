package vallegrande.edu.pe.agroPlan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vallegrande.edu.pe.agroPlan.model.PlanificacionSiembra;

@Repository
public interface PlanificacionSiembraRepository extends JpaRepository<PlanificacionSiembra, Integer> {
    List<PlanificacionSiembra> findByIdCampo(Integer idCampo);
    List<PlanificacionSiembra> findByIdCultivo(Integer idCultivo);
    List<PlanificacionSiembra> findByEstado(Boolean estado);
}
