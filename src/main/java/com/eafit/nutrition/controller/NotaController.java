package com.eafit.nutrition.controller;

import com.eafit.nutrition.model.Nota;
import com.eafit.nutrition.repository.NotaRepository;
import com.eafit.nutrition.repository.PacienteRepository;
import com.eafit.nutrition.repository.NutricionistaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    private final NotaRepository notaRepository;
    private final PacienteRepository pacienteRepository;
    private final NutricionistaRepository nutricionistaRepository;

    public NotaController(NotaRepository notaRepository,
                          PacienteRepository pacienteRepository,
                          NutricionistaRepository nutricionistaRepository) {
        this.notaRepository = notaRepository;
        this.pacienteRepository = pacienteRepository;
        this.nutricionistaRepository = nutricionistaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Nota>> getAllNotas() {
        return ResponseEntity.ok(notaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> getNotaById(@PathVariable Long id) {
        Optional<Nota> nota = notaRepository.findById(id);
        return nota.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Nota>> getNotasByPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(notaRepository.findByPacienteIdOrderByFechaCreacionDesc(pacienteId));
    }

    @GetMapping("/tipo/{tipoNota}")
    public ResponseEntity<List<Nota>> getNotasByTipo(@PathVariable String tipoNota) {
        return ResponseEntity.ok(notaRepository.findByTipoNota(tipoNota));
    }

    @PostMapping("/paciente/{pacienteId}/nutricionista/{nutricionistaId}")
    public ResponseEntity<Nota> createNota(
            @PathVariable Long pacienteId,
            @PathVariable Long nutricionistaId,
            @RequestBody Nota nota) {

        return pacienteRepository.findById(pacienteId)
                .flatMap(paciente -> nutricionistaRepository.findById(nutricionistaId)
                        .map(nutricionista -> {
                            nota.setPaciente(paciente);
                            nota.setNutricionista(nutricionista);
                            Nota created = notaRepository.save(nota);
                            return new ResponseEntity<>(created, HttpStatus.CREATED);
                        }))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> updateNota(
            @PathVariable Long id,
            @RequestBody Nota notaActualizada) {

        return notaRepository.findById(id)
                .map(nota -> {
                    nota.setTitulo(notaActualizada.getTitulo());
                    nota.setContenido(notaActualizada.getContenido());
                    nota.setTipoNota(notaActualizada.getTipoNota());
                    return ResponseEntity.ok(notaRepository.save(nota));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Long id) {
        notaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
