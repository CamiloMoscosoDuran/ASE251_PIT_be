package vallegrande.edu.pe.agroPlan.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import vallegrande.edu.pe.agroPlan.model.Alerta;
import vallegrande.edu.pe.agroPlan.service.AlertaService;

@RestController
@RequestMapping("/api/alertas")
@Tag(name = "Clima y Alertas", description = "API para gestionar las alertas climáticas y de cultivo")
public class AlertaController {

    @Autowired
    private AlertaService service;

    @PostMapping
    @Operation(summary = "Crear alerta",
            description = "Registra una nueva alerta climática o de cultivo en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alerta creada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alerta.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Alerta> crear(@Valid @RequestBody Alerta alerta) {
        Alerta nueva = service.save(alerta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener alerta por ID",
            description = "Obtiene una alerta específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alerta encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alerta.class))),
            @ApiResponse(responseCode = "404", description = "Alerta no encontrada")
    })
    public ResponseEntity<Alerta> obtenerPorId(
            @Parameter(description = "ID de la alerta") @PathVariable Integer id) {
        Optional<Alerta> alerta = service.obtenerPorId(id);
        return alerta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    @Operation(summary = "Obtener todas las alertas",
            description = "Obtiene el historial completo de alertas (activas y archivadas)")
    @ApiResponse(responseCode = "200", description = "Lista de alertas",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alerta.class)))
    public ResponseEntity<List<Alerta>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/activas")
    @Operation(summary = "Obtener alertas activas",
            description = "Obtiene únicamente las alertas con estado activo (estado = 1)")
    @ApiResponse(responseCode = "200", description = "Lista de alertas activas",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alerta.class)))
    public ResponseEntity<List<Alerta>> obtenerActivas() {
        return ResponseEntity.ok(service.obtenerActivas());
    }

    @GetMapping("/archivadas")
    @Operation(summary = "Obtener alertas archivadas",
            description = "Obtiene únicamente las alertas archivadas (estado = 0)")
    @ApiResponse(responseCode = "200", description = "Lista de alertas archivadas",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alerta.class)))
    public ResponseEntity<List<Alerta>> obtenerArchivadas() {
        return ResponseEntity.ok(service.obtenerArchivadas());
    }

    @GetMapping("/campo/{idCampo}")
    @Operation(summary = "Obtener alertas por campo",
            description = "Obtiene todas las alertas asociadas a un campo específico")
    @ApiResponse(responseCode = "200", description = "Lista de alertas del campo",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alerta.class)))
    public ResponseEntity<List<Alerta>> obtenerPorCampo(
            @Parameter(description = "ID del campo") @PathVariable Integer idCampo) {
        return ResponseEntity.ok(service.obtenerPorCampo(idCampo));
    }

    @GetMapping("/campo/{idCampo}/activas")
    @Operation(summary = "Obtener alertas activas por campo",
            description = "Obtiene las alertas activas de un campo específico (para mostrar en pantalla)")
    @ApiResponse(responseCode = "200", description = "Lista de alertas activas del campo",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alerta.class)))
    public ResponseEntity<List<Alerta>> obtenerActivasPorCampo(
            @Parameter(description = "ID del campo") @PathVariable Integer idCampo) {
        return ResponseEntity.ok(service.obtenerActivasPorCampo(idCampo));
    }

    @GetMapping("/cultivo/{idCultivo}")
    @Operation(summary = "Obtener alertas por cultivo",
            description = "Obtiene todas las alertas asociadas a un cultivo específico")
    @ApiResponse(responseCode = "200", description = "Lista de alertas del cultivo",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alerta.class)))
    public ResponseEntity<List<Alerta>> obtenerPorCultivo(
            @Parameter(description = "ID del cultivo") @PathVariable Integer idCultivo) {
        return ResponseEntity.ok(service.obtenerPorCultivo(idCultivo));
    }

    @GetMapping("/tipo/{tipoAlerta}")
    @Operation(summary = "Obtener alertas por tipo",
            description = "Filtra alertas por tipo: 'Peligro', 'Información', 'Éxito', 'Advertencia'")
    @ApiResponse(responseCode = "200", description = "Lista de alertas filtradas por tipo",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alerta.class)))
    public ResponseEntity<List<Alerta>> obtenerPorTipo(
            @Parameter(description = "Tipo de alerta") @PathVariable String tipoAlerta) {
        return ResponseEntity.ok(service.obtenerPorTipo(tipoAlerta));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar alerta",
            description = "Actualiza los datos de una alerta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alerta actualizada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alerta.class))),
            @ApiResponse(responseCode = "404", description = "Alerta no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Alerta> actualizar(
            @Parameter(description = "ID de la alerta") @PathVariable Integer id,
            @RequestBody Alerta alerta) {
        try {
            alerta.setIdAlerta(id);
            return ResponseEntity.ok(service.update(alerta));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar alerta (lógico)",
            description = "Marca una alerta como eliminada lógicamente, sin borrar el historial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alerta eliminada lógicamente exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alerta.class))),
            @ApiResponse(responseCode = "404", description = "Alerta no encontrada")
    })
    public ResponseEntity<Alerta> delete(
            @Parameter(description = "ID de la alerta") @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/restore/{id}")
    @Operation(summary = "Restaurar alerta",
            description = "Reactiva una alerta que fue eliminada lógicamente, manteniendo la fecha de eliminación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alerta restaurada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alerta.class))),
            @ApiResponse(responseCode = "404", description = "Alerta no encontrada")
    })
    public ResponseEntity<Alerta> restore(
            @Parameter(description = "ID de la alerta") @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.restore(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
