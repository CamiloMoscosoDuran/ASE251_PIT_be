package vallegrande.edu.pe.agroPlan.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vallegrande.edu.pe.agroPlan.model.Alerta;
import vallegrande.edu.pe.agroPlan.repository.AlertaRepository;
import vallegrande.edu.pe.agroPlan.service.AlertaService;

@Service
public class AlertaServiceImpl implements AlertaService {

    @Autowired
    private AlertaRepository repository;

    @Override
    public Alerta save(Alerta alerta) {
        // Si no se especifica fecha, se asigna la fecha actual
        if (alerta.getFechaAlerta() == null) {
            alerta.setFechaAlerta(LocalDate.now());
        }
        // Por defecto la alerta se crea activa
        if (alerta.getEstado() == null) {
            alerta.setEstado(true);
        }
        if (alerta.getCreatedAt() == null) {
            alerta.setCreatedAt(LocalDateTime.now());
        }
        return repository.save(alerta);
    }

    @Override
    public Optional<Alerta> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Alerta> obtenerTodas() {
        return repository.findAll();
    }

    @Override
    public List<Alerta> obtenerPorCampo(Integer idCampo) {
        return repository.findByIdCampo(idCampo);
    }

    @Override
    public List<Alerta> obtenerPorCultivo(Integer idCultivo) {
        return repository.findByIdCultivo(idCultivo);
    }

    @Override
    public List<Alerta> obtenerPorTipo(String tipoAlerta) {
        return repository.findByTipoAlerta(tipoAlerta);
    }

    @Override
    public List<Alerta> obtenerActivas() {
        return repository.findByEstado(true);
    }

    @Override
    public List<Alerta> obtenerArchivadas() {
        return repository.findByEstado(false);
    }

    @Override
    public List<Alerta> obtenerActivasPorCampo(Integer idCampo) {
        return repository.findByIdCampoAndEstado(idCampo, true);
    }

    @Override
    public Alerta update(Alerta alerta) {
        return repository.findById(alerta.getIdAlerta()).map(existente -> {
            if (alerta.getIdCampo() != null) {
                existente.setIdCampo(alerta.getIdCampo());
            }
            existente.setIdCultivo(alerta.getIdCultivo());
            if (alerta.getTipoAlerta() != null) {
                existente.setTipoAlerta(alerta.getTipoAlerta());
            }
            if (alerta.getTitulo() != null) {
                existente.setTitulo(alerta.getTitulo());
            }
            if (alerta.getMensaje() != null) {
                existente.setMensaje(alerta.getMensaje());
            }
            if (alerta.getFechaAlerta() != null) {
                existente.setFechaAlerta(alerta.getFechaAlerta());
            }
            if (alerta.getEstado() != null) {
                existente.setEstado(alerta.getEstado());
            }
            existente.setUpdatedAt(LocalDateTime.now());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Alerta no encontrada con id: " + alerta.getIdAlerta()));
    }

    @Override
    public Alerta delete(Integer id) {
        return repository.findById(id).map(existente -> {
            existente.setEstado(false);
            existente.setDeletedAt(LocalDateTime.now());
            existente.setUpdatedAt(LocalDateTime.now());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Alerta no encontrada con id: " + id));
    }

    @Override
    public Alerta restore(Integer id) {
        return repository.findById(id).map(existente -> {
            existente.setEstado(true);
            existente.setRestoredAt(LocalDateTime.now());
            existente.setUpdatedAt(LocalDateTime.now());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Alerta no encontrada con id: " + id));
    }
}
