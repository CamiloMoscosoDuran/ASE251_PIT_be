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
import vallegrande.edu.pe.agroPlan.model.ReporteHistorico;
import vallegrande.edu.pe.agroPlan.service.ReporteHistoricoService;

@RestController
@RequestMapping("/api/reportes-historicos")
@Tag(name = "Reportes Históricos", description = "API para gestionar los reportes financieros y de producción")
public class ReporteHistoricoController {

    @Autowired
    private ReporteHistoricoService service;

    @PostMapping
    @Operation(summary = "Crear reporte histórico",
            description = "Crea un nuevo reporte histórico en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reporte creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReporteHistorico.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ReporteHistorico> crear(@RequestBody ReporteHistorico reporte) {
        ReporteHistorico nuevoReporte = service.save(reporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoReporte);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reporte por ID",
            description = "Obtiene un reporte histórico específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReporteHistorico.class))),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<ReporteHistorico> obtenerPorId(
            @Parameter(description = "ID del reporte") @PathVariable Integer id) {
        Optional<ReporteHistorico> reporte = service.obtenerPorId(id);
        return reporte.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    @Operation(summary = "Obtener todos los reportes",
            description = "Obtiene la lista completa de reportes históricos")
    @ApiResponse(responseCode = "200", description = "Lista de reportes",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReporteHistorico.class)))
    public ResponseEntity<List<ReporteHistorico>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/campo/{idCampo}")
    @Operation(summary = "Obtener reportes por campo",
            description = "Obtiene los reportes asociados a un campo específico")
    public ResponseEntity<List<ReporteHistorico>> obtenerPorCampo(
            @Parameter(description = "ID del campo") @PathVariable Integer idCampo) {
        return ResponseEntity.ok(service.obtenerPorCampo(idCampo));
    }

    @GetMapping("/cultivo/{idCultivo}")
    @Operation(summary = "Obtener reportes por cultivo",
            description = "Obtiene los reportes asociados a un cultivo específico")
    public ResponseEntity<List<ReporteHistorico>> obtenerPorCultivo(
            @Parameter(description = "ID del cultivo") @PathVariable Integer idCultivo) {
        return ResponseEntity.ok(service.obtenerPorCultivo(idCultivo));
    }

    @GetMapping("/anio/{anio}")
    @Operation(summary = "Obtener reportes por año",
            description = "Obtiene los reportes correspondientes a un año específico")
    public ResponseEntity<List<ReporteHistorico>> obtenerPorAnio(
            @Parameter(description = "Año del reporte") @PathVariable Integer anio) {
        return ResponseEntity.ok(service.obtenerPorAnio(anio));
    }

    @GetMapping("/mes/{mes}")
    @Operation(summary = "Obtener reportes por mes",
            description = "Obtiene los reportes correspondientes a un mes específico")
    public ResponseEntity<List<ReporteHistorico>> obtenerPorMes(
            @Parameter(description = "Mes del reporte") @PathVariable Integer mes) {
        return ResponseEntity.ok(service.obtenerPorMes(mes));
    }

    @GetMapping("/periodo/{anio}/{mes}")
    @Operation(summary = "Obtener reportes por periodo",
            description = "Obtiene los reportes de un año y mes específicos")
    public ResponseEntity<List<ReporteHistorico>> obtenerPorPeriodo(
            @Parameter(description = "Año del reporte") @PathVariable Integer anio,
            @Parameter(description = "Mes del reporte") @PathVariable Integer mes) {
        return ResponseEntity.ok(service.obtenerPorPeriodo(anio, mes));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar reporte",
            description = "Actualiza un reporte histórico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte actualizado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReporteHistorico.class))),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ReporteHistorico> actualizar(
            @Parameter(description = "ID del reporte") @PathVariable Integer id,
            @RequestBody ReporteHistorico reporte) {
        try {
            reporte.setIdReporte(id);
            ReporteHistorico actualizado = service.update(reporte);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/delete/{id}")
    @Operation(summary = "Eliminar lógicamente un reporte",
            description = "Marca un reporte histórico como eliminado sin borrarlo físicamente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte eliminado lógicamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReporteHistorico.class))),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<ReporteHistorico> eliminar(
            @Parameter(description = "ID del reporte") @PathVariable Integer id) {
        try {
            ReporteHistorico eliminado = service.delete(id);
            return ResponseEntity.ok(eliminado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/restore/{id}")
    @Operation(summary = "Restaurar reporte",
            description = "Restaura un reporte histórico eliminado lógicamente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte restaurado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReporteHistorico.class))),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<ReporteHistorico> restaurar(
            @Parameter(description = "ID del reporte") @PathVariable Integer id) {
        try {
            ReporteHistorico restaurado = service.restore(id);
            return ResponseEntity.ok(restaurado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
