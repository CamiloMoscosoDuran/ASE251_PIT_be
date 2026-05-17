package vallegrande.edu.pe.agroPlan.service;

import java.util.List;
import java.util.Optional;

import vallegrande.edu.pe.agroPlan.model.PlanificacionCosecha;

public interface PlanificacionCosechaService {
    Optional<PlanificacionCosecha> obtenerPorId(Integer id);
    List<PlanificacionCosecha> obtenerTodas();
    List<PlanificacionCosecha> obtenerPorCampo(Integer idCampo);
    List<PlanificacionCosecha> obtenerPorCultivo(Integer idCultivo);
    List<PlanificacionCosecha> obtenerPorEstado(String estado);
    PlanificacionCosecha save(PlanificacionCosecha planificacion);
    PlanificacionCosecha update(PlanificacionCosecha planificacion);
    PlanificacionCosecha delete(Integer id);
    PlanificacionCosecha restore(Integer id);
}
