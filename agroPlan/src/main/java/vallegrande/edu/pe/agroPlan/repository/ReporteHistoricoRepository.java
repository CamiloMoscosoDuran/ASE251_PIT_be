package vallegrande.edu.pe.agroPlan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vallegrande.edu.pe.agroPlan.model.ReporteHistorico;

@Repository
public interface ReporteHistoricoRepository extends JpaRepository<ReporteHistorico, Integer> {
    List<ReporteHistorico> findByIdCampo(Integer idCampo);
    List<ReporteHistorico> findByIdCultivo(Integer idCultivo);
    List<ReporteHistorico> findByAnio(Integer anio);
    List<ReporteHistorico> findByMes(Integer mes);
    List<ReporteHistorico> findByAnioAndMes(Integer anio, Integer mes);
}
