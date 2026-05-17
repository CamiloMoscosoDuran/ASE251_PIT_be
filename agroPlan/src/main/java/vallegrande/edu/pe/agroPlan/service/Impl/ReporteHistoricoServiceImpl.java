package vallegrande.edu.pe.agroPlan.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vallegrande.edu.pe.agroPlan.model.ReporteHistorico;
import vallegrande.edu.pe.agroPlan.repository.ReporteHistoricoRepository;
import vallegrande.edu.pe.agroPlan.service.ReporteHistoricoService;

@Service
public class ReporteHistoricoServiceImpl implements ReporteHistoricoService {

    @Autowired
    private ReporteHistoricoRepository repository;

    @Override
    public Optional<ReporteHistorico> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<ReporteHistorico> obtenerTodas() {
        return repository.findAll();
    }

    @Override
    public List<ReporteHistorico> obtenerPorCampo(Integer idCampo) {
        return repository.findByIdCampo(idCampo);
    }

    @Override
    public List<ReporteHistorico> obtenerPorCultivo(Integer idCultivo) {
        return repository.findByIdCultivo(idCultivo);
    }

    @Override
    public List<ReporteHistorico> obtenerPorAnio(Integer anio) {
        return repository.findByAnio(anio);
    }

    @Override
    public List<ReporteHistorico> obtenerPorMes(Integer mes) {
        return repository.findByMes(mes);
    }

    @Override
    public List<ReporteHistorico> obtenerPorPeriodo(Integer anio, Integer mes) {
        return repository.findByAnioAndMes(anio, mes);
    }

    @Override
    public ReporteHistorico save(ReporteHistorico reporte) {
        reporte.setCreatedAt(LocalDateTime.now());
        return repository.save(reporte);
    }

    @Override
    public ReporteHistorico update(ReporteHistorico reporte) {
        return repository.findById(reporte.getIdReporte()).map(existente -> {
            existente.setIdCampo(reporte.getIdCampo());
            existente.setIdCultivo(reporte.getIdCultivo());
            existente.setMes(reporte.getMes());
            existente.setAnio(reporte.getAnio());
            existente.setProduccionTon(reporte.getProduccionTon());
            existente.setIngresos(reporte.getIngresos());
            existente.setGastos(reporte.getGastos());
            existente.setUpdatedAt(LocalDateTime.now());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Reporte no encontrado con id: " + reporte.getIdReporte()));
    }

    @Override
    public ReporteHistorico delete(Integer id) {
        return repository.findById(id).map(existente -> {
            existente.setDeletedAt(LocalDateTime.now());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Reporte no encontrado con id: " + id));
    }

    @Override
    public ReporteHistorico restore(Integer id) {
        return repository.findById(id).map(existente -> {
            existente.setRestoredAt(LocalDateTime.now());
            existente.setDeletedAt(null);
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Reporte no encontrado con id: " + id));
    }
}
