package vallegrande.edu.pe.agroPlan.service;

import java.util.List;
import java.util.Optional;

import vallegrande.edu.pe.agroPlan.model.ReporteHistorico;

public interface ReporteHistoricoService {
    Optional<ReporteHistorico> obtenerPorId(Integer id);
    List<ReporteHistorico> obtenerTodas();
    List<ReporteHistorico> obtenerPorCampo(Integer idCampo);
    List<ReporteHistorico> obtenerPorCultivo(Integer idCultivo);
    List<ReporteHistorico> obtenerPorAnio(Integer anio);
    List<ReporteHistorico> obtenerPorMes(Integer mes);
    List<ReporteHistorico> obtenerPorPeriodo(Integer anio, Integer mes);
    ReporteHistorico save(ReporteHistorico reporte);
    ReporteHistorico update(ReporteHistorico reporte);
    ReporteHistorico delete(Integer id);
    ReporteHistorico restore(Integer id);
}
