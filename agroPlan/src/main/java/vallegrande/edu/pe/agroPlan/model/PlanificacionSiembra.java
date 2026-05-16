package vallegrande.edu.pe.agroPlan.model;

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
@Table(name = "planificacion_siembra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanificacionSiembra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_siembra")
    private Integer idSiembra;

    @Column(name = "id_campo", nullable = false)
    private Integer idCampo;

    @Column(name = "id_cultivo", nullable = false)
    private Integer idCultivo;

    @Column(name = "fecha_siembra", nullable = false)
    private LocalDate fechaSiembra;

    @Column(name = "hectareas", nullable = false)
    private Integer hectareas;

    @Column(name = "estado", nullable = false, columnDefinition = "BIT DEFAULT 1")
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
