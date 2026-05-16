package vallegrande.edu.pe.agroPlan.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vallegrande.edu.pe.agroPlan.model.PlanificacionSiembra;
import vallegrande.edu.pe.agroPlan.repository.PlanificacionSiembraRepository;
import vallegrande.edu.pe.agroPlan.service.PlanificacionSiembraService;

@Service
public class PlanificacionSiembraServiceImpl implements PlanificacionSiembraService {

    @Autowired
    private PlanificacionSiembraRepository repository;

    @Override
    public PlanificacionSiembra save(PlanificacionSiembra planificacion) {
        planificacion.setCreatedAt(LocalDateTime.now());
        return repository.save(planificacion);
    }

    @Override
    public Optional<PlanificacionSiembra> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<PlanificacionSiembra> obtenerTodas() {
        return repository.findAll();
    }

    @Override
    public List<PlanificacionSiembra> obtenerPorCampo(Integer idCampo) {
        return repository.findByIdCampo(idCampo);
    }

    @Override
    public List<PlanificacionSiembra> obtenerPorCultivo(Integer idCultivo) {
        return repository.findByIdCultivo(idCultivo);
    }

    @Override
    public List<PlanificacionSiembra> obtenerPorEstado(String estado) {
        Boolean estadoBoolean = Boolean.parseBoolean(estado);
        return repository.findByEstado(estadoBoolean);
    }

    @Override
    public PlanificacionSiembra update(PlanificacionSiembra planificacion) {
        return repository.findById(planificacion.getIdSiembra()).map(existente -> {
            existente.setIdCampo(planificacion.getIdCampo());
            existente.setIdCultivo(planificacion.getIdCultivo());
            existente.setFechaSiembra(planificacion.getFechaSiembra());
            existente.setHectareas(planificacion.getHectareas());
            existente.setEstado(planificacion.getEstado());
            existente.setUpdatedAt(LocalDateTime.now());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Planificación no encontrada con id: " + planificacion.getIdSiembra()));
    }

    @Override
    public PlanificacionSiembra delete(Integer id) {
        return repository.findById(id).map(existente -> {
            existente.setEstado(false);
            existente.setDeletedAt(LocalDateTime.now());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Planificación no encontrada con id: " + id));
    }

    @Override
    public PlanificacionSiembra restore(Integer id) {
        return repository.findById(id).map(existente -> {
            existente.setEstado(true);
            existente.setRestoredAt(LocalDateTime.now());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Planificación no encontrada con id: " + id));
    }
}
