package vallegrande.edu.pe.agroPlan.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vallegrande.edu.pe.agroPlan.model.PlanificacionCosecha;
import vallegrande.edu.pe.agroPlan.repository.PlanificacionCosechaRepository;
import vallegrande.edu.pe.agroPlan.service.PlanificacionCosechaService;

@Service
public class PlanificacionCosechaServiceImpl implements PlanificacionCosechaService {

    @Autowired
    private PlanificacionCosechaRepository repository;

    @Override
    public PlanificacionCosecha save(PlanificacionCosecha planificacion) {
        planificacion.setCreatedAt(LocalDateTime.now());
        if (planificacion.getEstado() == null) {
            planificacion.setEstado("Proyectando");
        }
        return repository.save(planificacion);
    }

    @Override
    public Optional<PlanificacionCosecha> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<PlanificacionCosecha> obtenerTodas() {
        return repository.findAll();
    }

    @Override
    public List<PlanificacionCosecha> obtenerPorCampo(Integer idCampo) {
        return repository.findByIdCampo(idCampo);
    }

    @Override
    public List<PlanificacionCosecha> obtenerPorCultivo(Integer idCultivo) {
        return repository.findByIdCultivo(idCultivo);
    }

    @Override
    public List<PlanificacionCosecha> obtenerPorEstado(String estado) {
        return repository.findByEstado(estado);
    }

    @Override
    public PlanificacionCosecha update(PlanificacionCosecha planificacion) {
        return repository.findById(planificacion.getIdCosecha()).map(existente -> {
            existente.setIdCampo(planificacion.getIdCampo());
            existente.setIdCultivo(planificacion.getIdCultivo());
            existente.setFechaRecomendada(planificacion.getFechaRecomendada());
            existente.setHectareas(planificacion.getHectareas());
            existente.setProduccionEstimadaTon(planificacion.getProduccionEstimadaTon());
            existente.setEstado(planificacion.getEstado());
            existente.setUpdatedAt(LocalDateTime.now());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Planificación de cosecha no encontrada con id: " + planificacion.getIdCosecha()));
    }

    @Override
    public PlanificacionCosecha delete(Integer id) {
        return repository.findById(id).map(existente -> {
            existente.setEstado("Inactivo");
            existente.setDeletedAt(LocalDateTime.now());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Planificación de cosecha no encontrada con id: " + id));
    }

    @Override
    public PlanificacionCosecha restore(Integer id) {
        return repository.findById(id).map(existente -> {
            existente.setEstado("Proyectando");
            existente.setRestoredAt(LocalDateTime.now());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Planificación de cosecha no encontrada con id: " + id));
    }
}
