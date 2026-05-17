package vallegrande.edu.pe.agroPlan.model;

import java.math.BigDecimal;
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
@Table(name = "reportes_historicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Integer idReporte;

    @Column(name = "id_campo", nullable = false)
    private Integer idCampo;

    @Column(name = "id_cultivo", nullable = false)
    private Integer idCultivo;

    @Column(name = "mes", nullable = false)
    private Integer mes;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Column(name = "produccion_ton", nullable = false, precision = 10, scale = 2)
    private BigDecimal produccionTon;

    @Column(name = "ingresos", nullable = false, precision = 12, scale = 2)
    private BigDecimal ingresos;

    @Column(name = "gastos", nullable = false, precision = 12, scale = 2)
    private BigDecimal gastos;

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
