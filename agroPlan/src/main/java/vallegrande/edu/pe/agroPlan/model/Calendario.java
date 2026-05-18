package vallegrande.edu.pe.agroPlan.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "eventos_calendario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    @Schema(description = "ID único del evento del calendario", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idEvento;

    @Column(name = "id_campo", nullable = false)
    @Schema(description = "ID del campo asociado al evento", example = "1")
    private Integer idCampo;

    @Column(name = "id_cultivo", nullable = false)
    @Schema(description = "ID del cultivo del evento", example = "1")
    private Integer idCultivo;

    @Column(name = "fecha", nullable = false)
    @Schema(description = "Fecha del evento", example = "2024-05-17")
    private LocalDate fecha;

    @Column(name = "tipo_evento", nullable = false, length = 30)
    @Schema(description = "Tipo de evento (siembra, riego, cosecha, etc)", example = "SIEMBRA")
    private String tipoEvento;

    @Column(name = "hectareas", precision = 10, scale = 2)
    @Schema(description = "Hectáreas afectadas por el evento", example = "10.50")
    private BigDecimal hectareas;

    @Column(name = "estado", length = 30)
    @Schema(description = "Estado del evento", example = "PENDIENTE")
    private String estado;

    @Column(name = "descripcion", length = 250)
    @Schema(description = "Descripción detallada del evento", example = "Siembra de maíz en campo norte")
    private String descripcion;

    @Column(name = "created_at", updatable = false)
    @Schema(description = "Fecha y hora de creación del evento", 
            example = "2024-05-17T09:00:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Schema(description = "Fecha y hora de la última actualización del evento", 
            example = "2024-05-17T10:00:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    @Schema(description = "Fecha y hora de eliminación lógica del evento", 
            example = "2024-05-17T10:30:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime deletedAt;

    @Column(name = "restored_at")
    @Schema(description = "Fecha y hora de restauración del evento eliminado", 
            example = "2024-05-17T11:00:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime restoredAt;
}
