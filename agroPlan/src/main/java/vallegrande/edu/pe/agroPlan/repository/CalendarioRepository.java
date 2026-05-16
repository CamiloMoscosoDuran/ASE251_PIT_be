package vallegrande.edu.pe.agroPlan.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vallegrande.edu.pe.agroPlan.model.Calendario;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Integer> {
    List<Calendario> findByIdCampo(Integer idCampo);
    List<Calendario> findByIdCultivo(Integer idCultivo);
    List<Calendario> findByTipoEvento(String tipoEvento);
    List<Calendario> findByEstado(String estado);
    List<Calendario> findByFecha(LocalDate fecha);
}
