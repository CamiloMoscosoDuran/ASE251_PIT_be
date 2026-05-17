package vallegrande.edu.pe.agroPlan.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alertas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    @JsonProperty("idAlerta")
    private Integer idAlerta;

    @JsonProperty("idCampo")
    @NotNull(message = "idCampo es obligatorio")
    @Column(name = "id_campo", nullable = false)
    private Integer idCampo;

    // Puede ser NULL si es alerta general
    @JsonProperty("idCultivo")
    @Column(name = "id_cultivo")
    private Integer idCultivo;

    // Ej: 'Peligro', 'Información', 'Éxito', 'Advertencia'
    @JsonProperty("tipoAlerta")
    @NotNull(message = "tipoAlerta es obligatorio")
    @Column(name = "tipo_alerta", nullable = false, length = 30)
    private String tipoAlerta;

    // Ej: 'Riesgo de helada leve'
    @JsonProperty("titulo")
    @NotNull(message = "titulo es obligatorio")
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    // Ej: 'Temperatura mínima proyectada 8°C...'
    @JsonProperty("mensaje")
    @NotNull(message = "mensaje es obligatorio")
    @Column(name = "mensaje", nullable = false, length = 500)
    private String mensaje;

    @JsonProperty("fechaAlerta")
    @NotNull(message = "fechaAlerta es obligatoria")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_alerta", nullable = false)
    private LocalDate fechaAlerta;

    // 1 = Activa, 0 = Archivada
    @JsonProperty("estado")
    @Column(name = "estado", columnDefinition = "BIT DEFAULT 1")
    private Boolean estado = true;

    // Auditoría
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "restored_at")
    private LocalDateTime restoredAt;
}
