package com.eafit.nutrition.controller;

import com.eafit.nutrition.model.Paciente;
import com.eafit.nutrition.repository.PacienteRepository;
import com.eafit.nutrition.repository.NutricionistaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;
    private final NutricionistaRepository nutricionistaRepository;

    public PacienteController(PacienteRepository pacienteRepository,
                              NutricionistaRepository nutricionistaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.nutricionistaRepository = nutricionistaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        return ResponseEntity.ok(pacienteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        return paciente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Paciente>> getPacientesActivos() {
        return ResponseEntity.ok(pacienteRepository.findByActivoTrue());
    }

    @PostMapping("/nutricionista/{nutricionistaId}")
    public ResponseEntity<Paciente> createPaciente(
            @PathVariable Long nutricionistaId,
            @RequestBody Paciente paciente) {
        
        return nutricionistaRepository.findById(nutricionistaId)
                .map(nutricionista -> {
                    paciente.setNutricionista(nutricionista);
                    Paciente created = pacienteRepository.save(paciente);
                    return new ResponseEntity<>(created, HttpStatus.CREATED);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(
            @PathVariable Long id,
            @RequestBody Paciente pacienteActualizado) {

        return pacienteRepository.findById(id)
                .map(paciente -> {
                    paciente.setNombre(pacienteActualizado.getNombre());
                    paciente.setApellido(pacienteActualizado.getApellido());
                    paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
                    paciente.setEmail(pacienteActualizado.getEmail());
                    paciente.setTelefono(pacienteActualizado.getTelefono());
                    paciente.setActivo(pacienteActualizado.isActivo());
                    return ResponseEntity.ok(pacienteRepository.save(paciente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
