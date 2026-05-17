package vallegrande.edu.pe.agroPlan.service;

import java.util.List;
import java.util.Optional;

import vallegrande.edu.pe.agroPlan.model.Alerta;

public interface AlertaService {

    // ⚙️🔍 Consultas
    Optional<Alerta> obtenerPorId(Integer id);
    List<Alerta> obtenerTodas();
    List<Alerta> obtenerPorCampo(Integer idCampo);
    List<Alerta> obtenerPorCultivo(Integer idCultivo);
    List<Alerta> obtenerPorTipo(String tipoAlerta);
    List<Alerta> obtenerActivas();
    List<Alerta> obtenerArchivadas();
    List<Alerta> obtenerActivasPorCampo(Integer idCampo);

    // ⚙️➕ Crear
    Alerta save(Alerta alerta);

    // ⚙️✏️ Actualizar
    Alerta update(Alerta alerta);

    // ⚙️📦 Eliminar lógicamente
    Alerta delete(Integer id);

    // ⚙️♻️ Restaurar alerta
    Alerta restore(Integer id);
}
