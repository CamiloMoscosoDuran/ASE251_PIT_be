package vallegrande.edu.pe.agroPlan.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import vallegrande.edu.pe.agroPlan.model.Calendario;
import vallegrande.edu.pe.agroPlan.service.CalendarioService;

@RestController
@RequestMapping("/api/calendario")
@Tag(name = "Calendario de Eventos", description = "API para gestionar eventos del calendario agrícola")
public class CalendarioController {

    @Autowired
    private CalendarioService service;

    @PostMapping
    @Operation(summary = "Crear evento de calendario",
            description = "Crea un nuevo evento en el calendario agrícola")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evento creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Calendario.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Calendario> crear(@RequestBody Calendario evento) {
        Calendario nuevoEvento = service.save(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEvento);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener evento por ID",
            description = "Obtiene un evento específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Calendario.class))),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    public ResponseEntity<Calendario> obtenerPorId(
            @Parameter(description = "ID del evento") @PathVariable Integer id) {
        Optional<Calendario> evento = service.obtenerPorId(id);
        return evento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    @Operation(summary = "Obtener todos los eventos",
            description = "Obtiene la lista completa de eventos del calendario")
    @ApiResponse(responseCode = "200", description = "Lista de eventos",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Calendario.class)))
    public ResponseEntity<List<Calendario>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/campo/{idCampo}")
    @Operation(summary = "Obtener eventos por campo",
            description = "Obtiene eventos asociados a un campo específico")
    public ResponseEntity<List<Calendario>> obtenerPorCampo(
            @Parameter(description = "ID del campo") @PathVariable Integer idCampo) {
        return ResponseEntity.ok(service.obtenerPorCampo(idCampo));
    }

    @GetMapping("/cultivo/{idCultivo}")
    @Operation(summary = "Obtener eventos por cultivo",
            description = "Obtiene eventos asociados a un cultivo específico")
    public ResponseEntity<List<Calendario>> obtenerPorCultivo(
            @Parameter(description = "ID del cultivo") @PathVariable Integer idCultivo) {
        return ResponseEntity.ok(service.obtenerPorCultivo(idCultivo));
    }

    @GetMapping("/tipo/{tipoEvento}")
    @Operation(summary = "Obtener eventos por tipo",
            description = "Obtiene eventos filtrados por tipo de evento")
    public ResponseEntity<List<Calendario>> obtenerPorTipoEvento(
            @Parameter(description = "Tipo de evento") @PathVariable String tipoEvento) {
        return ResponseEntity.ok(service.obtenerPorTipoEvento(tipoEvento));
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Obtener eventos por estado",
            description = "Obtiene eventos filtrados por estado")
    public ResponseEntity<List<Calendario>> obtenerPorEstado(
            @Parameter(description = "Estado del evento") @PathVariable String estado) {
        return ResponseEntity.ok(service.obtenerPorEstado(estado));
    }

    @GetMapping("/fecha/{fecha}")
    @Operation(summary = "Obtener eventos por fecha",
            description = "Obtiene eventos que suceden en una fecha específica")
    public ResponseEntity<List<Calendario>> obtenerPorFecha(
            @Parameter(description = "Fecha del evento") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(service.obtenerPorFecha(fecha));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar evento",
            description = "Actualiza los datos de un evento existente")
    public ResponseEntity<Calendario> actualizar(
            @Parameter(description = "ID del evento") @PathVariable Integer id,
            @RequestBody Calendario evento) {
        try {
            evento.setIdEvento(id);
            return ResponseEntity.ok(service.update(evento));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/delete/{id}")
    @Operation(summary = "Eliminar lógicamente un evento",
            description = "Marca un evento como eliminado sin borrarlo físicamente")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del evento") @PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/restore/{id}")
    @Operation(summary = "Restaurar evento",
            description = "Restaura un evento eliminado lógicamente")
    public ResponseEntity<Calendario> restaurar(
            @Parameter(description = "ID del evento") @PathVariable Integer id) {
        try {
            Calendario restaurado = service.restore(id);
            return ResponseEntity.ok(restaurado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
