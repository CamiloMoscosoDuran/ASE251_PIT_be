package vallegrande.edu.pe.agroPlan.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vallegrande.edu.pe.agroPlan.model.Calendario;
import vallegrande.edu.pe.agroPlan.repository.CalendarioRepository;
import vallegrande.edu.pe.agroPlan.service.CalendarioService;

@Service
public class CalendarioServiceImpl implements CalendarioService {

    @Autowired
    private CalendarioRepository repository;

    @Override
    public Optional<Calendario> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Calendario> obtenerTodas() {
        return repository.findAll();
    }

    @Override
    public List<Calendario> obtenerPorCampo(Integer idCampo) {
        return repository.findByIdCampo(idCampo);
    }

    @Override
    public List<Calendario> obtenerPorCultivo(Integer idCultivo) {
        return repository.findByIdCultivo(idCultivo);
    }

    @Override
    public List<Calendario> obtenerPorTipoEvento(String tipoEvento) {
        return repository.findByTipoEvento(tipoEvento);
    }

    @Override
    public List<Calendario> obtenerPorEstado(String estado) {
        return repository.findByEstado(estado);
    }

    @Override
    public List<Calendario> obtenerPorFecha(LocalDate fecha) {
        return repository.findByFecha(fecha);
    }

    @Override
    public Calendario save(Calendario evento) {
        LocalDateTime now = LocalDateTime.now();
        evento.setCreatedAt(now);
        evento.setUpdatedAt(now);
        return repository.save(evento);
    }

    @Override
    public Calendario update(Calendario evento) {
        return repository.findById(evento.getIdEvento()).map(existente -> {
            existente.setIdCampo(evento.getIdCampo());
            existente.setIdCultivo(evento.getIdCultivo());
            existente.setFecha(evento.getFecha());
            existente.setTipoEvento(evento.getTipoEvento());
            existente.setHectareas(evento.getHectareas());
            existente.setEstado(evento.getEstado());
            existente.setDescripcion(evento.getDescripcion());
            existente.setUpdatedAt(LocalDateTime.now());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + evento.getIdEvento()));
    }

    @Override
    public void delete(Integer id) {
        Calendario existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));
        existente.setDeletedAt(LocalDateTime.now());
        existente.setEstado("INACTIVO");
        existente.setUpdatedAt(LocalDateTime.now());
        repository.save(existente);
    }

    @Override
    public Calendario restore(Integer id) {
        Calendario existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));
        existente.setRestoredAt(LocalDateTime.now());
        existente.setEstado("PENDIENTE");
        existente.setUpdatedAt(LocalDateTime.now());
        return repository.save(existente);
    }
}
