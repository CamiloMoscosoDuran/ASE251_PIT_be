package vallegrande.edu.pe.agroPlan.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import vallegrande.edu.pe.agroPlan.model.PlanificacionSiembra;
import vallegrande.edu.pe.agroPlan.service.PlanificacionSiembraService;

@RestController
@RequestMapping("/api/planificacion-siembra")
@Tag(name = "Planificación de Siembra", description = "API para gestionar las planificaciones de siembra")
public class PlanificacionSiembraController {

    @Autowired
    private PlanificacionSiembraService service;

    @PostMapping
    @Operation(summary = "Crear planificación de siembra",
            description = "Crea una nueva planificación de siembra en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Planificación creada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanificacionSiembra.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<PlanificacionSiembra> crear(@RequestBody PlanificacionSiembra planificacion) {
        PlanificacionSiembra nuevaPlanificacion = service.save(planificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPlanificacion);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener planificación por ID",
            description = "Obtiene una planificación específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planificación encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanificacionSiembra.class))),
            @ApiResponse(responseCode = "404", description = "Planificación no encontrada")
    })
    public ResponseEntity<PlanificacionSiembra> obtenerPorId(
            @Parameter(description = "ID de la planificación") @PathVariable Integer id) {
        Optional<PlanificacionSiembra> planificacion = service.obtenerPorId(id);
        return planificacion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    @Operation(summary = "Obtener todas las planificaciones",
            description = "Obtiene la lista completa de todas las planificaciones de siembra")
    @ApiResponse(responseCode = "200", description = "Lista de planificaciones",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanificacionSiembra.class)))
    public ResponseEntity<List<PlanificacionSiembra>> obtenerTodas() {
        List<PlanificacionSiembra> planificaciones = service.obtenerTodas();
        return ResponseEntity.ok(planificaciones);
    }

    @GetMapping("/campo/{idCampo}")
    @Operation(summary = "Obtener planificaciones por campo",
            description = "Obtiene todas las planificaciones asociadas a un campo específico")
    @ApiResponse(responseCode = "200", description = "Lista de planificaciones del campo",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanificacionSiembra.class)))
    public ResponseEntity<List<PlanificacionSiembra>> obtenerPorCampo(
            @Parameter(description = "ID del campo") @PathVariable Integer idCampo) {
        List<PlanificacionSiembra> planificaciones = service.obtenerPorCampo(idCampo);
        return ResponseEntity.ok(planificaciones);
    }

    @GetMapping("/cultivo/{idCultivo}")
    @Operation(summary = "Obtener planificaciones por cultivo",
            description = "Obtiene todas las planificaciones asociadas a un cultivo específico")
    @ApiResponse(responseCode = "200", description = "Lista de planificaciones del cultivo",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanificacionSiembra.class)))
    public ResponseEntity<List<PlanificacionSiembra>> obtenerPorCultivo(
            @Parameter(description = "ID del cultivo") @PathVariable Integer idCultivo) {
        List<PlanificacionSiembra> planificaciones = service.obtenerPorCultivo(idCultivo);
        return ResponseEntity.ok(planificaciones);
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Obtener planificaciones por estado",
            description = "Obtiene todas las planificaciones con un estado específico")
    @ApiResponse(responseCode = "200", description = "Lista de planificaciones",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanificacionSiembra.class)))
    public ResponseEntity<List<PlanificacionSiembra>> obtenerPorEstado(
            @Parameter(description = "Estado de la planificación") @PathVariable String estado) {
        List<PlanificacionSiembra> planificaciones = service.obtenerPorEstado(estado);
        return ResponseEntity.ok(planificaciones);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar planificación",
            description = "Actualiza los datos de una planificación existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planificación actualizada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanificacionSiembra.class))),
            @ApiResponse(responseCode = "404", description = "Planificación no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<PlanificacionSiembra> actualizar(
            @Parameter(description = "ID de la planificación") @PathVariable Integer id,
            @RequestBody PlanificacionSiembra planificacion) {
        try {
            planificacion.setIdSiembra(id);
            PlanificacionSiembra actualizada = service.update(planificacion);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/delete/{id}")
    @Operation(summary = "Eliminar lógicamente una planificación",
            description = "Marca una planificación como eliminada sin borrar los datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planificación eliminada lógicamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanificacionSiembra.class))),
            @ApiResponse(responseCode = "404", description = "Planificación no encontrada")
    })
    public ResponseEntity<PlanificacionSiembra> eliminar(
            @Parameter(description = "ID de la planificación") @PathVariable Integer id) {
        try {
            PlanificacionSiembra eliminada = service.delete(id);
            return ResponseEntity.ok(eliminada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/restore/{id}")
    @Operation(summary = "Restaurar una planificación",
            description = "Restaura una planificación que fue eliminada lógicamente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planificación restaurada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanificacionSiembra.class))),
            @ApiResponse(responseCode = "404", description = "Planificación no encontrada")
    })
    public ResponseEntity<PlanificacionSiembra> restaurar(
            @Parameter(description = "ID de la planificación") @PathVariable Integer id) {
        try {
            PlanificacionSiembra restaurada = service.restore(id);
            return ResponseEntity.ok(restaurada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
