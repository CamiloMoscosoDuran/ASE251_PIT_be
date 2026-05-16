package vallegrande.edu.pe.agroPlan.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import vallegrande.edu.pe.agroPlan.model.Calendario;

public interface CalendarioService {
    Optional<Calendario> obtenerPorId(Integer id);
    List<Calendario> obtenerTodas();
    List<Calendario> obtenerPorCampo(Integer idCampo);
    List<Calendario> obtenerPorCultivo(Integer idCultivo);
    List<Calendario> obtenerPorTipoEvento(String tipoEvento);
    List<Calendario> obtenerPorEstado(String estado);
    List<Calendario> obtenerPorFecha(LocalDate fecha);
    Calendario save(Calendario evento);
    Calendario update(Calendario evento);
    void delete(Integer id);
    Calendario restore(Integer id);
}
