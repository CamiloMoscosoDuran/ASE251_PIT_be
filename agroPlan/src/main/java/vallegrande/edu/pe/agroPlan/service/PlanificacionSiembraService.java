package vallegrande.edu.pe.agroPlan.service;

import java.util.List;
import java.util.Optional;

import vallegrande.edu.pe.agroPlan.model.PlanificacionSiembra;

public interface PlanificacionSiembraService {
    Optional<PlanificacionSiembra> obtenerPorId(Integer id);
    List<PlanificacionSiembra> obtenerTodas();
    List<PlanificacionSiembra> obtenerPorCampo(Integer idCampo);
    List<PlanificacionSiembra> obtenerPorCultivo(Integer idCultivo);
    List<PlanificacionSiembra> obtenerPorEstado(String estado);
    PlanificacionSiembra save(PlanificacionSiembra planificacion);
    // ⚙️✏️ Actualizar
    PlanificacionSiembra update(PlanificacionSiembra planificacion);
    // ⚙️❌ Eliminar lógico
    PlanificacionSiembra delete(Integer id);
    // ⚙️♻️ Restaurar lógico
    PlanificacionSiembra restore(Integer id);

}
