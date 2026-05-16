package vallegrande.edu.pe.agroPlan.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Table(name = "planificacion_cosecha")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanificacionCosecha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cosecha")
    private Integer idCosecha;

    @Column(name = "id_campo", nullable = false)
    private Integer idCampo;

    @Column(name = "id_cultivo", nullable = false)
    private Integer idCultivo;

    @Column(name = "fecha_recomendada", nullable = false)
    private LocalDate fechaRecomendada;

    @Column(name = "hectareas", nullable = false, precision = 10, scale = 2)
    private BigDecimal hectareas;

    @Column(name = "produccion_estimada_ton", precision = 10, scale = 2)
    private BigDecimal produccionEstimadaTon;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado = "Proyectando";

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
